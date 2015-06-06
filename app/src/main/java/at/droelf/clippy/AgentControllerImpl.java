package at.droelf.clippy;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.gui.UiAnimation;
import at.droelf.clippy.utils.AnimationUtil;
import at.droelf.clippy.utils.O;
import at.droelf.clippy.view.CustomAnimationDrawableNew;
import at.droelf.clippy.view.FloatingView;
import timber.log.Timber;

public class AgentControllerImpl implements AgentController{

    private final AgentType agentType;
    private final AgentService agentService;

    private final Context context;
    private final FloatingView floatingView;

    private FrameLayout frameLayout;
    private List<ImageView> imageLayer;
    private ProgressBar progressBar;

    private final Handler handler;
    private AnimationRunnable animationRunnable;

    private final AtomicBoolean animationIsRunning = new AtomicBoolean(true);
    private final AtomicBoolean isMute = new AtomicBoolean(false);
    private boolean killed = false;
    private boolean initialized = false;
    private WeakReference<AgentControllerListener> agentControllerListener;


    public AgentControllerImpl(AgentType agentType, Context context, AgentService agentService){
        this.agentType = agentType;
        this.context = context;
        this.agentService = agentService;
        this.floatingView = new FloatingView(context);
        this.handler = new Handler();

        initView();
        loadAgentData.execute(agentType);
    }

    @Override
    public void kill(){
        isAlive();
        stop(false);
        this.killed = true;
        this.floatingView.kill();
        Global.INSTANCE.getAgentStorage().setAgentStop(false);
    }

    @Override
    public boolean isKilled() {
        return killed;
    }

    @Override
    public void stop(boolean user){
        isAlive();

        // if agent stopped by user .. stop agent and save state
        if(user){
            Global.INSTANCE.getAgentStorage().setAgentStop(true);
        }

        animationIsRunning.set(false);
        handler.removeCallbacks(animationRunnable);

        for(ImageView imageView : imageLayer){
            if(imageView != null && imageView.getBackground() != null && imageView.getBackground() instanceof  AnimationDrawable){
                ((AnimationDrawable)imageView.getBackground()).stop();
            }
        }

        resetImages();

        if(agentControllerListener != null && agentControllerListener.get() != null){
            agentControllerListener.get().stateChanged(false);
        }
    }

    @Override
    public void start(boolean user){
        isAlive();

        final boolean stoppedByUser = Global.INSTANCE.getAgentStorage().isAgentStop();
        // user -> true ... always start
        // user -> false || stoppedByUser -> false

        Timber.d(
                "Start agent: user: %s, stoppedByUser: %s",
                user, stoppedByUser
        );

        if(user || (!user && !stoppedByUser)){
            Timber.d("Starting agent");
            Global.INSTANCE.getAgentStorage().setAgentStop(false);

            if(animationIsRunning.compareAndSet(false, true)){
                handler.post(animationRunnable);
                if(agentControllerListener != null && agentControllerListener.get() != null){
                    agentControllerListener.get().stateChanged(true);
                }
            }
        }else{
            Timber.d("Not starting agent");
        }
    }

    @Override
    public boolean isRunning(){
        isAlive();
        return animationIsRunning.get();
    }

    @Override
    public void mute() {
        isMute.set(true);
        if(agentControllerListener != null && agentControllerListener.get() != null){
            agentControllerListener.get().volumeChanged(true);
        }
    }

    @Override
    public void unMute() {
        isMute.set(false);
        if(agentControllerListener != null && agentControllerListener.get() != null){
            agentControllerListener.get().volumeChanged(false);
        }
    }

    @Override
    public boolean isMute() {
        return isMute.get();
    }

    @Override
    public AgentType getAgentType() {
        return agentType;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void setAgentControllerListener(AgentControllerListener agentControllerListener) {
        this.agentControllerListener = new WeakReference<>(agentControllerListener);
    }


    private void initView(){
        isAlive();
        Timber.d("Init view");

        frameLayout = new FrameLayout(context);
        progressBar = new ProgressBar(context);

        frameLayout.addView(progressBar);
        floatingView.addView(frameLayout);
    }

    private void displayAgent(O<UiAgent> agentOption){
        isAlive();
        if(agentOption.isSuccess()){
            Timber.d("Display agent: %s", agentType);

            final UiAgent agent = agentOption.getData();
            imageLayer = new ArrayList<>(agent.getOverlayCount());

            for(int i = 0; i < agent.getOverlayCount(); i++){
                final ImageView imageView = new ImageView(context);
                imageLayer.add(imageView);
                frameLayout.addView(imageView);
            }

            progressBar.setVisibility(View.GONE);

            imageLayer.get(0).setBackgroundDrawable(ContextCompat.getDrawable(context, agent.getFirstImage()));
            initialized = true;

            Timber.d("Initial start animation");
            startAnimation(agent);

            if(agentControllerListener != null && agentControllerListener.get() != null){
                agentControllerListener.get().stateChanged(true);
            }

        }else{
            Timber.e("Failure during loading agent data :(");
            //TODO
            throw new RuntimeException(agentOption.getError());
        }
    }

    private void startAnimation(final UiAgent agent){
        isAlive();
        resetImages();

        final long animationDelay = getAnimationDelay();
        Timber.d("Start animation in %s ms", animationDelay);

        this.animationRunnable = new AnimationRunnable(agent);
        handler.postDelayed(this.animationRunnable, animationDelay);
    }

    private void startSoundHandler(final List<AnimationUtil.SoundMapping> soundMap){
        isAlive();
        Timber.d("Start sound handler");
        for(AnimationUtil.SoundMapping soundMapping : soundMap){
            new Handler().postDelayed(new SoundRunnable(soundMapping.getSoundId()), soundMapping.getTime());
        }
    }

    private UiAnimation getRandomAnimation(UiAgent uiAgent){
        isAlive();
        final ArrayList<String> keys = new ArrayList<>(uiAgent.getAnimations().keySet());
        final int i = new Random().nextInt(keys.size() - 1) + 1;

        Timber.d("Random animation: %s", keys.get(i));

        return uiAgent.getAnimations().get(keys.get(i));
    }

    private long getAnimationDelay(){
        final AnimationPause animationPause = Global.INSTANCE.getSettingsStorage().getAnimationPause();
        return animationPause.getRandomPause();
    }

    private void isAlive(){
        if(killed){
            Timber.e("Agent is dead, long live the agent ... but this one is really dead");
            throw new RuntimeException("FloatingView is dead x.x");
        }
    }

    private void resetImages(){
        isAlive();
        imageLayer.get(0).setBackgroundDrawable(ContextCompat.getDrawable(context, getAgentType().getAgentMapping().getFirstFrameId()));

        for(int i = 1; i < imageLayer.size(); i++){
            imageLayer.get(i).setBackgroundDrawable(ContextCompat.getDrawable(context, getAgentType().getAgentMapping().getEmptyFrameId()));
        }
    }

    private final AsyncTask<AgentType, Void, O<UiAgent>> loadAgentData = new AsyncTask<AgentType, Void, O<UiAgent>>(){
        @Override
        protected O<UiAgent> doInBackground(AgentType... agentTypes) {
            return agentService.getUiAgent(context, agentTypes[0]);
        }

        @Override
        protected void onPostExecute(O<UiAgent> uiAgentO) {
            Timber.d("Agent data successfully loaded");
            displayAgent(uiAgentO);
        }
    };

    private class SoundRunnable implements Runnable{

        private final int sound;

        SoundRunnable(int sound) {
            this.sound = sound;
        }

        @Override
        public void run() {
            Timber.d(
                "Execute sound runnable: Id: %s, isAnimationRunning: %s, isKilled: %s, isMute: %s",
                sound, animationIsRunning.get(), killed, isMute.get()
            );

            if(animationIsRunning.get() && !killed && !isMute.get()){
                final MediaPlayer mediaPlayer = MediaPlayer.create(AgentControllerImpl.this.context, sound);
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
            }
        }
    }


    private class AnimationRunnable implements Runnable {

        private final UiAgent agent;

        public AnimationRunnable(UiAgent agent){
            this.agent = agent;
        }

        @Override
        public void run() {
            Timber.d(
                "Execute animationRunnable: image layer null: %s, #imagelayer: %s, killed: %s, animationRunning: %s",
                imageLayer == null, (imageLayer != null) ? imageLayer.size() : 0, killed, animationIsRunning.get()
            );

            if(imageLayer != null && imageLayer.size() > 0 && !killed && animationIsRunning.get()){
                final UiAnimation uiAnimation = getRandomAnimation(agent);
                final AnimationUtil.AnimationDrawableResult animationDrawable = AnimationUtil.getAnimationDrawable(AgentControllerImpl.this.context, uiAnimation, agent.getOverlayCount());
                final List<AnimationDrawable> animationDrawables = animationDrawable.getAnimationDrawables();

                for(ImageView imageView : imageLayer){
                    imageView.setBackgroundDrawable(ContextCompat.getDrawable(context, agentType.getAgentMapping().getEmptyFrameId()));
                }

                final CustomAnimationDrawableNew firstLayer = new CustomAnimationDrawableNew(animationDrawable.getAnimationDrawables().get(0)){
                    @Override
                    public void onAnimationFinish() {
                        if(animationIsRunning.get() && !killed){
                            startAnimation(agent);
                        }
                    }
                };

                imageLayer.get(0).setBackgroundDrawable(firstLayer);

                for(int i = 1; i < animationDrawables.size(); i++){
                    imageLayer.get(i).setBackgroundDrawable(animationDrawables.get(i));
                }

                for(ImageView imageView : imageLayer){
                    ((AnimationDrawable)imageView.getBackground()).start();
                }

                startSoundHandler(animationDrawable.getSoundMappings());
            }
        }
    }

}



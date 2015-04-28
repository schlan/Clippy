package at.droelf.clippy;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

    private Handler handler;
    private AnimationRunnable animationRunnable;

    private AtomicBoolean animationIsRunning = new AtomicBoolean(true);
    private AtomicBoolean isMute = new AtomicBoolean(false);
    private boolean killed = false;


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
        stop();
        this.killed = true;
        this.floatingView.kill();
    }

    @Override
    public void stop(){
        isAlive();
        animationIsRunning.set(false);
        handler.removeCallbacks(animationRunnable);

        for(ImageView imageView : imageLayer){
            if(imageView != null && imageView.getBackground() != null && imageView.getBackground() instanceof  AnimationDrawable){
                ((AnimationDrawable)imageView.getBackground()).stop();
            }
        }
    }

    @Override
    public void start(){
        isAlive();
        if(animationIsRunning.compareAndSet(false, true)){
            handler.post(animationRunnable);
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
    }

    @Override
    public void unMute() {
        isMute.set(false);
    }

    @Override
    public boolean isMute() {
        return isMute.get();
    }

    @Override
    public AgentType getAgentType() {
        return agentType;
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
            imageLayer.get(0).setBackgroundDrawable(context.getResources().getDrawable(agent.getFirstImage()));

            Timber.d("Initial start animation");
            startAnimation(agent);

        }else{
            Timber.e("Failure during loading agent data :(");
            //TODO
            throw new RuntimeException(agentOption.getError());
        }
    }

    private void startAnimation(final UiAgent agent){
        isAlive();

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
        return uiAgent.getAnimations().get(keys.get(i));
    }

    private long getAnimationDelay(){
        Random random = new Random();
        return random.nextInt(5000) + 2000;
    }

    private void isAlive(){
        if(killed){
            Timber.e("Agent is dead, long live the agent ... but this one is really dead");
            throw new RuntimeException("FloatingView is dead x.x");
        }
    }

    private AsyncTask<AgentType, Void, O<UiAgent>> loadAgentData = new AsyncTask<AgentType, Void, O<UiAgent>>(){
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
                MediaPlayer.create(AgentControllerImpl.this.context, sound).start();
            }
        }
    }


    private class AnimationRunnable implements Runnable {

        private UiAgent agent;

        public AnimationRunnable(UiAgent agent){
            this.agent = agent;
        }

        @Override
        public void run() {
            Timber.d(
                "Execute animationRunnable: imagelayer null: %s, #imagelayer: %s, killed: %s, animationRunning: %s",
                imageLayer == null, imageLayer.size(), killed, animationIsRunning.get()
            );

            if(imageLayer != null && imageLayer.size() > 0 && !killed && animationIsRunning.get()){
                final UiAnimation uiAnimation = getRandomAnimation(agent);
                final AnimationUtil.AnimationDrawableResult animationDrawable = AnimationUtil.getAnimationDrawable(AgentControllerImpl.this.context, uiAnimation, agent.getOverlayCount());
                final List<AnimationDrawable> animationDrawables = animationDrawable.getAnimationDrawables();

                for(ImageView imageView : imageLayer){
                    imageView.setBackgroundDrawable(context.getResources().getDrawable(agentType.getAgentMapping().getEmptyFrameId()));
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



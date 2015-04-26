package at.droelf.clippy;


import android.content.Context;
import android.content.Intent;
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

public class AgentController {

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


    public AgentController(AgentType agentType, Context context, AgentService agentService){
        this.agentType = agentType;
        this.context = context;
        this.agentService = agentService;
        this.floatingView = new FloatingView(context);
        this.handler = new Handler();

        initView();

        loadAgentData.execute(agentType);
    }

    public void stop(){
        if(animationIsRunning.get()){
            animationIsRunning.set(false);
            if(animationRunnable != null){
                handler.removeCallbacks(animationRunnable);
            }
        }
    }

    public void start(){
        if(!animationIsRunning.get()){
            if(animationRunnable != null){
                animationIsRunning.set(true);
                handler.post(animationRunnable);
            }
        }
    }

    public boolean isRunning(){
        return animationIsRunning.get();
    }

    private void initView(){
        frameLayout = new FrameLayout(context);
        progressBar = new ProgressBar(context);

        frameLayout.addView(progressBar);
        floatingView.addView(frameLayout);

    }

    private void displayAgent(O<UiAgent> agentOption){
        if(agentOption.isSuccess()){

            final UiAgent agent = agentOption.getData();
            imageLayer = new ArrayList<>(agent.getOverlayCount());

            for(int i = 0; i < agent.getOverlayCount(); i++){
                final ImageView imageView = new ImageView(context);
                imageLayer.add(imageView);
                frameLayout.addView(imageView);
            }

            progressBar.setVisibility(View.GONE);
            imageLayer.get(0).setBackground(context.getResources().getDrawable(agent.getFirstImage()));

            startAnimation(agent);

        }else{
            //TODO
            System.out.println("----- error: " + agentOption.getError());
            throw new RuntimeException(agentOption.getError());
        }
    }

    private void startAnimation(final UiAgent agent){
        //TODO time
        final long animationDelay = getAnimationDelay();
        System.out.println("---- start handler: " + animationDelay);
        this.animationRunnable = new AnimationRunnable(agent);

        handler.postDelayed(this.animationRunnable, animationDelay);
    }

    private void startSoundHandler(final List<AnimationUtil.SoundMapping> soundMap){
        for(AnimationUtil.SoundMapping soundMapping : soundMap){
            new Handler().postDelayed(new SoundRunnable(soundMapping.getSoundId()), soundMapping.getTime());
        }
    }

    private UiAnimation getRandomAnimation(UiAgent uiAgent){
        final ArrayList<String> keys = new ArrayList<>(uiAgent.getAnimations().keySet());
        int i = new Random().nextInt(keys.size() - 1) + 1;
        return uiAgent.getAnimations().get(keys.get(i));
    }

    private long getAnimationDelay(){
        Random random = new Random();
        return random.nextInt(5000) + 2000;
    }


    private AsyncTask<AgentType, Void, O<UiAgent>> loadAgentData = new AsyncTask<AgentType, Void, O<UiAgent>>(){
        @Override
        protected O<UiAgent> doInBackground(AgentType... agentTypes) {
            return agentService.getUiAgent(agentTypes[0]);
        }

        @Override
        protected void onPostExecute(O<UiAgent> uiAgentO) {
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
            MediaPlayer.create(AgentController.this.context, sound).start();
        }
    }


    private class AnimationRunnable implements Runnable {

        private UiAgent agent;

        public AnimationRunnable(UiAgent agent){
            this.agent = agent;
        }

        @Override
        public void run() {
            System.out.println("----- handler starts");

            if(imageLayer != null && imageLayer.size() > 0){
                final UiAnimation uiAnimation = getRandomAnimation(agent);
                final AnimationUtil.AnimationDrawableResult animationDrawable = AnimationUtil.getAnimationDrawable(AgentController.this.context, uiAnimation, agent.getOverlayCount());
                final List<AnimationDrawable> animationDrawables = animationDrawable.getAnimationDrawables();

                for(ImageView imageView : imageLayer){
                    imageView.setBackground(context.getResources().getDrawable(agentType.getAgentMapping().getEmptyFrameId()));
                }

                final CustomAnimationDrawableNew firstLayer = new CustomAnimationDrawableNew(animationDrawable.getAnimationDrawables().get(0)){
                    @Override
                    public void onAnimationFinish() {
                        if(animationIsRunning.get()){
                            startAnimation(agent);
                        }
                    }
                };

                imageLayer.get(0).setBackground(firstLayer);

                for(int i = 1; i < animationDrawables.size(); i++){
                    imageLayer.get(i).setBackground(animationDrawables.get(i));
                }

                for(ImageView imageView : imageLayer){
                    ((AnimationDrawable)imageView.getBackground()).start();
                }

                startSoundHandler(animationDrawable.getSoundMappings());
            }
        }
    }

}



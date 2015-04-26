package at.droelf.clippy;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.gui.UiAnimation;
import at.droelf.clippy.model.gui.UiFrame;
import at.droelf.clippy.utils.AnimationUtil;
import at.droelf.clippy.utils.O;
import at.droelf.clippy.view.CustomAnimationDrawableNew;
import at.droelf.clippy.view.FloatingView;

public class FloatingService extends Service {

    private ImageView imageView;
    private Handler handler;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        FloatingService getService() {
            return FloatingService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(imageView == null){
            handler = new Handler();
            showWidget();
            Notification clippy = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle("Clippy")
                    .build();
            startForeground(1, clippy);
        }

        return START_STICKY;
    }

    private void showWidget() {
        final FloatingView floatingView = new FloatingView(getApplicationContext());

        this.imageView = new ImageView(getApplicationContext());
        imageView.setBackground(getResources().getDrawable(R.drawable.clippy_0000));

        floatingView.addView(imageView);
        postHandler();
    }

    private void postHandler(){
        Random random = new Random();
        int i = random.nextInt(5000) + 2000;
        System.out.println("---- start handler: " + i);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("----- handler starts");

                if(imageView != null){
                    final UiAnimation uiAnimation = getAnimationDrawable(AgentType.CLIPPY);
                    final AnimationUtil.AnimationDrawableResult animationDrawable = AnimationUtil.getAnimationDrawable(FloatingService.this.getApplicationContext(), uiAnimation, 1);//TODO

                    final CustomAnimationDrawableNew c = new CustomAnimationDrawableNew(animationDrawable.getAnimationDrawables().get(0)){
                        @Override
                        public void onAnimationFinish() {
                            postHandler();
                        }
                    };
                    imageView.setBackground(c);
                    snd(animationDrawable.getSoundMappings());
                    c.start();
                }
            }
        }, i);
    }

    private void snd(final List<AnimationUtil.SoundMapping> soundMap){
        for(AnimationUtil.SoundMapping soundMapping : soundMap){
            new Handler().postDelayed(new MediaRunnable(soundMapping.getSoundId()), soundMapping.getTime());
        }
    }

    class MediaRunnable implements Runnable{

        private final int sound;

        MediaRunnable(int sound) {
            this.sound = sound;
        }

        @Override
        public void run() {
            MediaPlayer.create(FloatingService.this.getApplicationContext(), sound).start();
        }
    }

    private UiAnimation getAnimationDrawable(AgentType agentType){
        final AgentService agentService = new AgentService(new AgentSourceImpl(getApplicationContext()));
        final O<UiAgent> uiAgent = agentService.getUiAgent(agentType);

        if(uiAgent.isSuccess()){
            final ArrayList<String> keys = new ArrayList<>(uiAgent.getData().getAnimations().keySet());
            int i = new Random().nextInt(keys.size() - 1) + 1;
            return uiAgent.getData().getAnimations().get(keys.get(i));
        }
        return null;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}

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
import at.droelf.clippy.model.gui.UiFrame;
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
        int i = random.nextInt(10000) + 5000;
        System.out.println("---- start handler: " + i);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("----- handler starts");

                if(imageView != null){
                    Return animationDrawable = getAnimationDrawable();
                    final CustomAnimationDrawableNew c = new CustomAnimationDrawableNew(animationDrawable.animationDrawable){
                        @Override
                        public void onAnimationFinish() {
                            postHandler();
                        }
                    };
                    imageView.setBackground(c);
                    snd(animationDrawable.soundMap);
                    c.start();
                }
            }
        }, i);
    }

    private void snd(final Map<Long, Integer> soundMap){
        for(final Long time : soundMap.keySet()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MediaPlayer.create(FloatingService.this.getApplicationContext(), soundMap.get(time)).start();
                }
            }, time);
        }
    }

    private Return getAnimationDrawable(){
        final AgentService agentService = new AgentService(new AgentSourceImpl(getApplicationContext()));
        final O<UiAgent> uiAgent = agentService.getUiAgent(AgentType.CLIPPY);

        final AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(true);

        final Map<Long, Integer> soundMap = new HashMap<>();
        long time = 0;

        if(uiAgent.isSuccess()){


            final UiAgent data = uiAgent.getData();

            final ArrayList<String> keys = new ArrayList<>(data.getAnimations().keySet());
            int i = new Random().nextInt(keys.size() - 1) + 1;

            final List<UiFrame> sendMail = data.getAnimations().get(keys.get(i)).getUiFrames();
            for(UiFrame frame : sendMail){
                if(frame.getSoundId() != null){
                    soundMap.put(time, frame.getSoundId());
                }
                final Drawable drawable =getApplicationContext().getResources().getDrawable(frame.getImageIds().get(0));
                animationDrawable.addFrame(drawable, frame.getDuration());

                time =+ frame.getDuration();
            }

        }
        return new Return(animationDrawable, soundMap);
    }


    class Return {
        AnimationDrawable animationDrawable;
        Map<Long, Integer> soundMap;

        Return(AnimationDrawable animationDrawable, Map<Long, Integer> soundMap) {
            this.animationDrawable = animationDrawable;
            this.soundMap = soundMap;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}

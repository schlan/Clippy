package at.droelf.clippy;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ImageView;

import java.util.List;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.gui.UiAnimation;
import at.droelf.clippy.model.gui.UiFrame;
import at.droelf.clippy.utils.O;
import at.droelf.clippy.view.FloatingView;

public class FloatingService extends Service {



    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        FloatingService getService() {
            return FloatingService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showWidget();

        return START_STICKY;
    }

    private void showWidget() {
        final FloatingView floatingView = new FloatingView(getApplicationContext());



        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackground(getAnimationDrawable());

        floatingView.addView(imageView);
    }


    private AnimationDrawable getAnimationDrawable(){
        final AgentService agentService = new AgentService(new AgentSourceImpl(getApplicationContext()));
        final O<UiAgent> uiAgent = agentService.getUiAgent(AgentType.CLIPPY);

        final AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(true);

        if(uiAgent.isSuccess()){
            final UiAgent data = uiAgent.getData();

            for(String key : data.getAnimations().keySet()){
                final List<UiFrame> sendMail = data.getAnimations().get(key).getUiFrames();
                for(UiFrame frame : sendMail){
                    final Drawable drawable =getApplicationContext().getResources().getDrawable(frame.getImageIds().get(0));
                    animationDrawable.addFrame(drawable, frame.getDuration());
                }
            }
            animationDrawable.addFrame(getDrawable(R.drawable.clippy_0917), 1000);
        }
        return animationDrawable;
    }


    class Return {
        AnimationDrawable animationDrawable;
        int w;
        int h;

        Return(AnimationDrawable animationDrawable, int w, int h) {
            this.animationDrawable = animationDrawable;
            this.w = w;
            this.h = h;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}

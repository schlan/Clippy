package at.droelf.clippy.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import timber.log.Timber;

public class FloatingView extends FrameLayout implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final WindowManager.LayoutParams layoutParams;
    private final WindowManager windowManager;

    private FloatingViewCLickListener floatingViewCLickListener;

    private View childView = null;

    private int xDelta;
    private int yDelta;

    private boolean killed = false;

    public FloatingView(Context context) {
        super(context);

        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        this.layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        this.layoutParams.gravity = Gravity.CENTER;

        this.setOnTouchListener(this);

        this.windowManager.addView(this, layoutParams);
    }

    public void kill(){
        isAlive();
        this.killed = true;
        this.windowManager.removeViewImmediate(this);
        this.floatingViewCLickListener = null;
        Timber.d("Somebody killed me");
    }

    @Override
    public void addView(@NonNull View v){
        isAlive();
        Timber.d("Add View: %s", v);
        this.removeAllViews();
        this.childView = v;

        super.addView(v);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final boolean gestureDetected = gestureDetector.onTouchEvent(motionEvent);

        final int x = (int) motionEvent.getRawX();
        final int y = (int) motionEvent.getRawY();

        boolean motionDetected = false;
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                xDelta = x - layoutParams.x;
                yDelta = y - layoutParams.y;
                motionDetected = true;
                break;
            case MotionEvent.ACTION_MOVE:
                updateLocation((x - xDelta), (y - yDelta));
                motionDetected = true;
                break;
        }

        return gestureDetected || motionDetected;
    }

    public void setFloatingViewCLickListener(FloatingViewCLickListener floatingViewCLickListener){
        isAlive();
        this.floatingViewCLickListener = floatingViewCLickListener;
    }

    private void updateLocation(int x, int y){
        layoutParams.x = x;
        layoutParams.y = y;
        windowManager.updateViewLayout(this, layoutParams);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Timber.d("Single tap detected");
            if(FloatingView.this.floatingViewCLickListener != null){
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        FloatingView.this.floatingViewCLickListener.onSingleTap(FloatingView.this);
                    }
                });
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Timber.d("Long press detected");
            if(FloatingView.this.floatingViewCLickListener != null){
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        FloatingView.this.floatingViewCLickListener.onLongPress(FloatingView.this);
                    }
                });
            }
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Timber.d("Double tap detected");
            if(FloatingView.this.floatingViewCLickListener != null){
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        FloatingView.this.floatingViewCLickListener.onDoubleTap(FloatingView.this);
                    }
                });
            }
            return super.onDoubleTap(e);
        }
    }

    private void isAlive(){
        if(killed){
            Timber.e("View is dead, leave it alone!");
            throw new RuntimeException("Floatingview is dead x.x");
        }
    }


    public interface FloatingViewCLickListener {
        void onSingleTap(View v);
        void onDoubleTap(View v);
        void onLongPress(View v);
    }

}

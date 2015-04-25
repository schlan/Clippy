package at.droelf.clippy.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class FloatingView extends FrameLayout implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final WindowManager.LayoutParams layoutParams;
    private final WindowManager windowManager;

    private View childView = null;

    private int xDelta;
    private int yDelta;

    public FloatingView(Context context) {
        super(context);
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        this.layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        this.layoutParams.gravity = Gravity.TOP | Gravity.LEFT;

        this.setOnTouchListener(this);

        this.windowManager.addView(this, layoutParams);
    }

    @Override
    public void addView(View v){
        if(v == null){
            throw new IllegalArgumentException("Provided view is null :(");
        }
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

    private void updateLocation(int x, int y){
        layoutParams.x = x;
        layoutParams.y = y;
        windowManager.updateViewLayout(this, layoutParams);
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(FloatingView.this.getContext(), "click", Toast.LENGTH_LONG).show();

            if(childView != null && childView instanceof ImageView){
                Drawable background = childView.getBackground();
                if(background != null && background instanceof AnimationDrawable){
                    ((AnimationDrawable)background).stop();
                    ((AnimationDrawable)background).start();
                }
            }

            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(FloatingView.this.getContext(), "long click", Toast.LENGTH_LONG).show();
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(FloatingView.this.getContext(), "double click", Toast.LENGTH_LONG).show();
            return super.onDoubleTap(e);
        }
    }

}

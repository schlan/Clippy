package at.droelf.clippy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class FloatingActivity extends Service {

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        FloatingActivity getService() {
            return FloatingActivity.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showWidget();
        return START_STICKY;
    }

    private void showWidget() {
        final WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);

        final TextView textView = new TextView(this);
        textView.setText("Clippy rulez");
        textView.setBackgroundColor(Color.BLUE);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(200, 200,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        windowManager.addView(textView, params);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 10;
        params.y = 10;

        windowManager.updateViewLayout(textView, params);

        textView.setOnTouchListener(new ClippyTouchListener(windowManager, params));

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private class ClippyTouchListener implements View.OnTouchListener {

        private final WindowManager windowManager;
        private final WindowManager.LayoutParams params;


        public ClippyTouchListener(WindowManager windowManager, WindowManager.LayoutParams params) {
            this.windowManager = windowManager;
            this.params = params;
        }

        private int xDelta;
        private int yDelta;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            final int x = (int) motionEvent.getRawX();
            final int y = (int) motionEvent.getRawY();

            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                    xDelta = x - layoutParams.x;
                    yDelta = y - layoutParams.y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    params.x = x - xDelta;
                    params.y = y - yDelta;
                    windowManager.updateViewLayout(view, params);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    }


}

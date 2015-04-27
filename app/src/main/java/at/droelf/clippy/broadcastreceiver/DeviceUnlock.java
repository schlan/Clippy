package at.droelf.clippy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import at.droelf.clippy.FloatingService;


public class DeviceUnlock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Intent intent1 = new Intent(context, FloatingService.class);
            intent1.putExtra(FloatingService.Command.KEY, FloatingService.Command.Start);
            context.startService(intent1);

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent intent1 = new Intent(context, FloatingService.class);
            intent1.putExtra(FloatingService.Command.KEY, FloatingService.Command.Stop);
            context.startService(intent1);
        }
    }
}

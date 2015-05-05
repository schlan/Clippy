package at.droelf.clippy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import at.droelf.clippy.FloatingService;
import at.droelf.clippy.Global;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.utils.IntentHelper;
import timber.log.Timber;


public class DeviceUnlock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Timber.d("Device unlocked - Start");
            context.startService(IntentHelper.getStartStopIntent(context, FloatingService.Command.Start, false));

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Timber.d("Device locked - Stop");
            context.startService(IntentHelper.getStartStopIntent(context, FloatingService.Command.Stop, false));

        } else if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            final AgentType lastUsedAgent = Global.INSTANCE.getAgentStorage().getLastUsedAgent();
            final boolean startOnBoot = Global.INSTANCE.getSettingsStorage().isSettingsStartOnBoot();
            Timber.d("Device boot completed - Starting agent - Agent: %s, startOnBoot: %s", lastUsedAgent, startOnBoot);

            if(startOnBoot){
                context.startService(IntentHelper.getShowIntent(context, lastUsedAgent));
            }
        }

    }
}

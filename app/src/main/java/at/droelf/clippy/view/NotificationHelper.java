package at.droelf.clippy.view;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import at.droelf.clippy.FloatingService;
import at.droelf.clippy.utils.IntentHelper;
import at.droelf.clippy.MainActivity;
import at.droelf.clippy.model.AgentType;

public class NotificationHelper {


    public static Notification getNotification(Context context, AgentType agentType, boolean isRunning, boolean isMute){

        final PendingIntent startMainActivity = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        final FloatingService.Command startStopCommand = (isRunning) ? FloatingService.Command.Stop : FloatingService.Command.Start;
        final int startStopDrawable = (isRunning) ? android.R.drawable.ic_media_pause : android.R.drawable.ic_media_play;
        final String startStopString = (isRunning) ? "Stop" : "Start";
        final PendingIntent startStopPending = PendingIntent.getService(context, 110, IntentHelper.getCommandIntent(context, startStopCommand), PendingIntent.FLAG_CANCEL_CURRENT);

        final FloatingService.Command muteUnmuteCommand = (isMute) ? FloatingService.Command.UnMute : FloatingService.Command.Mute;
        final int muteUnmuteDrawable = (isMute) ? android.R.drawable.ic_lock_silent_mode_off : android.R.drawable.ic_lock_silent_mode;
        final String muteUnmuteString = (isMute) ? "Unmute" : "Mute";
        final PendingIntent muteUnmutePending = PendingIntent.getService(context, 120, IntentHelper.getCommandIntent(context, muteUnmuteCommand), PendingIntent.FLAG_CANCEL_CURRENT);

        final PendingIntent killPending = PendingIntent.getService(context, 130, IntentHelper.getCommandIntent(context, FloatingService.Command.Kill), PendingIntent.FLAG_CANCEL_CURRENT);

        return new NotificationCompat.Builder(context)
                .setSmallIcon(agentType.getAgentMapping().getFirstFrameId())
                .setContentTitle(agentType.name())
                .setContentText(agentType.toString())
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), agentType.getAgentMapping().getFirstFrameId()))
                .setContentIntent(startMainActivity)

                .setStyle(new NotificationCompat.BigTextStyle())

                .addAction(startStopDrawable, startStopString, startStopPending)
                .addAction(muteUnmuteDrawable, muteUnmuteString, muteUnmutePending)
                .addAction(android.R.drawable.ic_delete, "Kill", killPending)

                .build();
    }

}

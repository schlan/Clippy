package at.droelf.clippy;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.view.NotificationHelper;
import timber.log.Timber;

public class FloatingService extends Service {

    private final static int NOTIFICATION_ID = 14232;

    private final IBinder mBinder = new LocalBinder();
    private AgentController agentController;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent == null){
            return START_STICKY;
        }

        if(intent.hasExtra(Command.KEY)){

            final Command command = (Command) intent.getSerializableExtra(Command.KEY);
            Timber.d("onStartCommand with command: %s", command);

            switch (command){
                case Show:
                    if(agentController == null){
                        final AgentType agentType = (AgentType) intent.getSerializableExtra(AgentType.KEY);
                        this.agentController = new AgentControllerImpl(agentType, getApplicationContext(), Global.INSTANCE.getAgentService());
                        startForeground(NOTIFICATION_ID, NotificationHelper.getNotification(this, agentController.getAgentType(), agentController.isRunning(), agentController.isMute()));
                    }
                    break;

                case Start:
                    if(agentController != null && !agentController.isRunning()){
                        agentController.start();
                    }
                    break;

                case Stop:
                    if(agentController != null && agentController.isRunning()){
                        agentController.stop();
                    }
                    break;

                case Kill:
                    if(agentController != null){
                        agentController.kill();
                        this.agentController = null;
                    }
                    stopSelf();
                    break;

                case Mute:
                    if(agentController != null && !agentController.isMute()){
                        agentController.mute();
                    }
                    break;

                case UnMute:
                    if(agentController != null && agentController.isMute()){
                        agentController.unMute();
                    }
                    break;
            }

            if(agentController != null){
                final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //mNotificationManager.cancel(123);
                mNotificationManager.notify(NOTIFICATION_ID, NotificationHelper.getNotification(this, agentController.getAgentType(), agentController.isRunning(), agentController.isMute()));
            }
        }

        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class LocalBinder extends Binder {
        FloatingService getService() {
            return FloatingService.this;
        }
    }

    public enum Command{
        Show, Start, Stop, Kill, Mute, UnMute;

        public static String KEY = "COMMAND";
    }

}

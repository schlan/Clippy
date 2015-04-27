package at.droelf.clippy;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import at.droelf.clippy.model.AgentType;

public class FloatingService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private AgentController agentController;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final Command command = (Command) intent.getSerializableExtra(Command.KEY);

        switch (command){
            case Show:
                if(agentController == null){
                    final AgentType agentType = (AgentType) intent.getSerializableExtra(AgentType.KEY);

                    final Notification clippy = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.clippy_0000)
                            .setContentTitle("Clippy")
                            .setContentText(agentType.toString())
                            .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), 0))
                            .build();
                    startForeground(1, clippy);
                    this.agentController = new AgentControllerImpl(agentType, getApplicationContext(), Global.INSTANCE.getAgentService());
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
        }

        return START_STICKY;
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
        Show, Start, Stop, Kill;

        public static String KEY = "COMMAND";
    }

}

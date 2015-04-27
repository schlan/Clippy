package at.droelf.clippy;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import at.droelf.clippy.model.AgentType;

public class FloatingService extends Service {

    public enum Command{
        Show, Start, Stop, Kill;

        public static String KEY = "COMMAND";
    }

    private AgentService agentService;

    private final IBinder mBinder = new LocalBinder();

    private AgentController agentController;

    public class LocalBinder extends Binder {
        FloatingService getService() {
            return FloatingService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.agentService = new AgentServiceImpl(new AgentSourceImpl(this));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final AgentType agentType = AgentType.ROCKY;
        final Command command = (Command) intent.getSerializableExtra(Command.KEY);

        switch (command){
            case Show:
                if(agentController == null){
                    final Notification clippy = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.clippy_0000)
                            .setContentTitle("Clippy")
                            .setContentText(agentType.toString())
                            .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), 0))
                            .build();
                    startForeground(1, clippy);
                    this.agentController = new AgentControllerImpl(agentType, getApplicationContext(), agentService);
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

}

package at.droelf.clippy.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.RemoteViews;

import at.droelf.clippy.AgentController;
import at.droelf.clippy.FloatingService;
import at.droelf.clippy.R;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.utils.IntentHelper;

public class AgentWidgetProvider extends AppWidgetProvider {

    private FloatingService floatingService;
    private boolean serviceBound = false;

    @Override
    public void onReceive(Context context, Intent i) {
        super.onReceive(context, i);
        final Intent intent = new Intent(context, FloatingService.class);
        context.getApplicationContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        for(int i : appWidgetIds){
            if(serviceBound){
                final AgentController agentController = floatingService.getAgentController();

                if(agentController != null){
                    final AgentType agentType = agentController.getAgentType();


                    final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

                    views.setImageViewResource(R.id.widget_image, agentType.getAgentMapping().getFirstFrameId());
                    views.setOnClickPendingIntent(R.id.widget_b_kill,
                            PendingIntent.getActivity(context, 0, IntentHelper.getCommandIntent(context, FloatingService.Command.Kill), 0));

                    appWidgetManager.updateAppWidget(i, views);
                }
            }

        }


    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FloatingService.LocalBinder binder = (FloatingService.LocalBinder) service;
            floatingService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };
}

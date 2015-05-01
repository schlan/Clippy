package at.droelf.clippy.utils;


import android.content.Context;
import android.content.Intent;

import at.droelf.clippy.FloatingService;
import at.droelf.clippy.model.AgentType;

public class IntentHelper {


    public static Intent getCommandIntent(Context context, FloatingService.Command command){
        final Intent intent = new Intent(context, FloatingService.class);
        intent.putExtra(FloatingService.Command.KEY, command);
        return intent;
    }

    public static Intent getShowIntent(Context context, AgentType agentType){
        final Intent commandIntent = getCommandIntent(context, FloatingService.Command.Show);
        commandIntent.putExtra(AgentType.KEY, agentType);
        return commandIntent;
    }

}

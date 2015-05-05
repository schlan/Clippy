package at.droelf.clippy.utils;


import android.content.Context;
import android.content.Intent;

import com.getbase.floatingactionbutton.FloatingActionButton;

import at.droelf.clippy.FloatingService;
import at.droelf.clippy.model.AgentType;

public class IntentHelper {


    public static Intent getStartStopIntent(Context context, FloatingService.Command command, boolean user){
        if(command == FloatingService.Command.Stop || command == FloatingService.Command.Start){
            final Intent commandIntent = getCommandIntent(context, command);
            commandIntent.putExtra(FloatingService.AGENT_ACTION_USER, user);
            return commandIntent;
        }else{
            throw new IllegalArgumentException("Invalid command");
        }
    }

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

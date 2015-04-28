package at.droelf.clippy.utils;


import android.content.Context;
import android.content.Intent;

import at.droelf.clippy.FloatingService;

public class IntentHelper {


    public static Intent getCommandIntent(Context context, FloatingService.Command command){
        final Intent intent = new Intent(context, FloatingService.class);
        intent.putExtra(FloatingService.Command.KEY, command);
        return intent;
    }

}

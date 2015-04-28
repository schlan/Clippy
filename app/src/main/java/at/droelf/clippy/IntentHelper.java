package at.droelf.clippy;


import android.content.Context;
import android.content.Intent;

public class IntentHelper {


    public static Intent getCommandIntent(Context context, FloatingService.Command command){
        final Intent intent = new Intent(context, FloatingService.class);
        intent.putExtra(FloatingService.Command.KEY, command);
        return intent;
    }

}

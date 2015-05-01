package at.droelf.clippy.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class ClippyStorage {

    private final static String NAME = "agent_storage";

    private final static String AGENT_MUTE = "agent_mute";
    private final static boolean AGENT_MUTE_DEFAULT = true;


    private final SharedPreferences sharedPreferences;

    public ClippyStorage(Context context){
        this.sharedPreferences =  context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public boolean isMute(){
        return sharedPreferences.getBoolean(AGENT_MUTE, AGENT_MUTE_DEFAULT);
    }

    public void setMute(boolean mute){
        sharedPreferences.edit()
                .putBoolean(AGENT_MUTE, mute)
                .apply();
    }

}

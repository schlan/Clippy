package at.droelf.clippy.storage;

import android.content.Context;
import android.content.SharedPreferences;

import at.droelf.clippy.model.AgentType;

public class ClippyStorage {

    private final static String NAME = "agent_storage";

    private final static String AGENT_MUTE = "agent_mute";
    private final static boolean AGENT_MUTE_DEFAULT = true;

    private final static String AGENT_LAST_USED = "agent_lastused";
    private final static AgentType AGENT_LAST_USED_DEFAUlT = AgentType.CLIPPY;

    private final SharedPreferences sharedPreferences;

    public ClippyStorage(Context context){
        this.sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public boolean isMute(){
        return sharedPreferences.getBoolean(AGENT_MUTE, AGENT_MUTE_DEFAULT);
    }

    public void setMute(boolean mute){
        sharedPreferences.edit()
                .putBoolean(AGENT_MUTE, mute)
                .apply();
    }

    public AgentType getLastUsedAgent(){
        final String agentString = sharedPreferences.getString(AGENT_LAST_USED, AGENT_LAST_USED_DEFAUlT.name());
        return AgentType.valueOf(agentString);
    }

    public void setAgentLastUsed(AgentType agentLastUsed){
        sharedPreferences.edit()
                .putString(AGENT_LAST_USED, agentLastUsed.name())
                .apply();
    }

}

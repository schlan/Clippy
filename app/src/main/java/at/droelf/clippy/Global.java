package at.droelf.clippy;



import android.content.Context;

import com.zendesk.sdk.logger.Logger;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import at.droelf.clippy.storage.ClippyStorage;
import at.droelf.clippy.storage.SettingsStorage;
import timber.log.Timber;


public enum Global {
    INSTANCE;

    private AgentSource agentSource;
    private AgentService agentService;
    private ClippyStorage clippyStorage;
    private SettingsStorage settingsStorage;
    private Timber.Tree logTree;
    private Context context;
    private boolean init = false;

    Global(){}

    public void init(Context context){
        this.context = context;
        this.agentSource = new AgentSourceImpl();
        this.agentService = new AgentServiceImpl(agentSource);
        this.clippyStorage = new ClippyStorage(context);
        this.settingsStorage = new SettingsStorage(context);

        this.logTree = new Timber.DebugTree();
        Logger.setLoggable(true);

        init = true;
    }

    public AgentService getAgentService() {
        checkInit();
        return agentService;
    }

    public Timber.Tree getLogTree() {
        checkInit();
        return logTree;
    }

    public ClippyStorage getClippyStorage(){
        checkInit();
        return clippyStorage;
    }

    public SettingsStorage getSettingsStorage(){
        checkInit();
        return settingsStorage;
    }

    private void checkInit(){
        if(!init){
            throw new RuntimeException("Global context not initialized");
        }
    }


}

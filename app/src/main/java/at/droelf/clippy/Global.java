package at.droelf.clippy;

import android.content.Context;

import com.zendesk.sdk.logger.Logger;
import com.zendesk.sdk.network.impl.ZendeskConfig;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import at.droelf.clippy.storage.AgentStorage;
import at.droelf.clippy.storage.SettingsStorage;
import timber.log.Timber;


public enum Global {
    INSTANCE;

    private AgentSource agentSource;
    private AgentService agentService;
    private AgentStorage agentStorage;
    private SettingsStorage settingsStorage;
    private Timber.Tree logTree;
    private Context context;
    private boolean init = false;

    Global(){}

    public void init(Context context){
        this.context = context;
        this.agentSource = new AgentSourceImpl();
        this.agentService = new AgentServiceImpl(agentSource);
        this.agentStorage = new AgentStorage(context);
        this.settingsStorage = new SettingsStorage(context);

        this.logTree = new Timber.DebugTree();
        Logger.setLoggable(true);

        initZendesk(context);
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

    public AgentStorage getAgentStorage(){
        checkInit();
        return agentStorage;
    }

    public SettingsStorage getSettingsStorage(){
        checkInit();
        return settingsStorage;
    }

    public Context getContext(){
        checkInit();
        return context;
    }

    private void checkInit(){
        if(!init){
            throw new RuntimeException("Global context not initialized");
        }
    }

    private void initZendesk(Context context){
        ZendeskConfig.INSTANCE.init(context, "https://clippy.zendesk.com", "c4c73bee174db74b69c8057010b859141b6093eb439182ea", "mobile_sdk_client_0ad88c1b1ed31aadc633");
    }





}

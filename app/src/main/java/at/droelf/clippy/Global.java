package at.droelf.clippy;



import com.zendesk.sdk.logger.Logger;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import timber.log.Timber;


public enum Global {
    INSTANCE;

    private final AgentSource agentSource;
    private final AgentService agentService;
    private final Timber.Tree logTree;

    Global(){
        this.agentSource = new AgentSourceImpl();
        this.agentService = new AgentServiceImpl(agentSource);

        this.logTree = new Timber.DebugTree();
        Logger.setLoggable(true);
    }

    public AgentService getAgentService() {
        return agentService;
    }

    public Timber.Tree getLogTree() {
        return logTree;
    }


}

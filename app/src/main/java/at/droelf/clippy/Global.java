package at.droelf.clippy;

import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.backend.source.AgentSourceImpl;


public enum Global {
    INSTANCE;

    private final AgentSource agentSource;
    private final AgentService agentService;

    private Global(){
        this.agentSource = new AgentSourceImpl();
        this.agentService = new AgentServiceImpl(agentSource);
    }

    public AgentService getAgentService() {
        return agentService;
    }
}

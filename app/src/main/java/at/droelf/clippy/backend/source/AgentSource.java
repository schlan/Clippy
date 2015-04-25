package at.droelf.clippy.backend.source;

import at.droelf.clippy.utils.O;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.raw.Agent;

public interface AgentSource {
    public O<Agent> getAgent(AgentType agentType);
}

package at.droelf.clippy.backend;

import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.utils.O;

public interface AgentService {
    public O<UiAgent> getUiAgent(AgentType agentType);
}

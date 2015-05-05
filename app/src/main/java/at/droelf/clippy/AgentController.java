package at.droelf.clippy;

import at.droelf.clippy.model.AgentType;

interface AgentController {
    void start();
    void stop();
    void kill();
    boolean isKilled();
    boolean isRunning();
    void mute();
    void unMute();
    boolean isMute();
    AgentType getAgentType();
    boolean isInitialized();
    void setAgentControllerListener(AgentControllerListener agentControllerListener);
}

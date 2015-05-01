package at.droelf.clippy;

import at.droelf.clippy.model.AgentType;

public interface AgentController {
    public void start();
    public void stop();
    public void kill();
    public boolean isRunning();
    public void mute();
    public void unMute();
    public boolean isMute();
    public AgentType getAgentType();
    void setAgentControllerListener(AgentControllerListener agentControllerListener);
}

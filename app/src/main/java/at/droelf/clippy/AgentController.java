package at.droelf.clippy;

public interface AgentController {
    public void start();
    public void stop();
    public void kill();
    public boolean isRunning();
}

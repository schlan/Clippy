package at.droelf.clippy;

public interface AgentControllerListener {
    void volumeChanged(boolean mute);
    void stateChanged(boolean started);
}

package at.droelf.clippy;

interface AgentControllerListener {
    void volumeChanged(boolean mute);
    void stateChanged(boolean started);
}

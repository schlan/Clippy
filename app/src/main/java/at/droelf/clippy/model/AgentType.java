package at.droelf.clippy.model;

public enum AgentType {

    CLIPPY("agent_clippy.json");


    private String assetName;

    AgentType(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetName() {
        return assetName;
    }
}

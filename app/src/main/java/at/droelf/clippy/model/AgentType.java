package at.droelf.clippy.model;

import at.droelf.clippy.backend.converter.mapping.*;

public enum AgentType {

    CLIPPY("agent_clippy.json", new ClippyMapping()),
    LINKS("agent_links.json", new LinksMapping()),
    GENIUS("agent_genius.json", new GeniusMapping()),
    ROCKY("agent_rocky.json", new RockyMapping()),
    F1("agent_f1.json", new F1Mapping())
    ;

    public static String KEY = "extra_agenttype";

    private String assetName;
    private AgentMapping agentMapping;

    AgentType(String assetNamem, AgentMapping agentMapping) {
        this.assetName = assetNamem;
        this.agentMapping = agentMapping;
    }

    public String getAssetName() {
        return assetName;
    }

    public AgentMapping getAgentMapping() {
        return agentMapping;
    }
}

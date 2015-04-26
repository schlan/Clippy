package at.droelf.clippy.model;

import at.droelf.clippy.backend.converter.mapping.AgentMapping;
import at.droelf.clippy.backend.converter.mapping.ClippyMapping;
import at.droelf.clippy.backend.converter.mapping.GeniusMapping;
import at.droelf.clippy.backend.converter.mapping.LinksMapping;

public enum AgentType {

    CLIPPY("agent_clippy.json", new ClippyMapping()),
    LINKS("agent_links.json", new LinksMapping()),
    GENIUS("agent_genius.json", new GeniusMapping());

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

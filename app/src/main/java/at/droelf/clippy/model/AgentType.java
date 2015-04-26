package at.droelf.clippy.model;

import at.droelf.clippy.backend.converter.mapping.AgentMapping;
import at.droelf.clippy.backend.converter.mapping.ClippyMapping;
import at.droelf.clippy.backend.converter.mapping.LinksMapping;

public enum AgentType {

    CLIPPY("agent_clippy.json", new ClippyMapping()),
    LINKS("agent_links.json", new LinksMapping());

    private String assetName;
    private AgentMapping agentMapping;

    AgentType(String assetNamem, AgentMapping agentMapping) {
        this.assetName = assetName;
        this.agentMapping = agentMapping;
    }

    public String getAssetName() {
        return assetName;
    }

    public AgentMapping getAgentMapping() {
        return agentMapping;
    }
}

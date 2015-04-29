package at.droelf.clippy.model;

import at.droelf.clippy.backend.converter.mapping.*;

public enum AgentType {

    CLIPPY("agent_clippy.json", new ClippyMapping(), false),
    LINKS("agent_links.json", new LinksMapping(), false),
    GENIUS("agent_genius.json", new GeniusMapping(), false),
    ROCKY("agent_rocky.json", new RockyMapping(), false),
    F1("agent_f1.json", new F1Mapping(), false),
    ROVER("agent_rover.json", new RoverMapping(), false),
    GENIE("agent_genie.json", new GenieMapping(), false),
    MERLIN("agent_merlin.json", new MerlinMapping(), false),
    PEEDY("agent_peedy.json", new PeedyMapping(), false),
    BONZI("agent_bonzi.json", new BonziMapping(), true)
    ;

    public static String KEY = "extra_agenttype";

    private String assetName;
    private AgentMapping agentMapping;
    private boolean broken;

    AgentType(String assetNamem, AgentMapping agentMapping, boolean broken) {
        this.assetName = assetNamem;
        this.agentMapping = agentMapping;
        this.broken = broken;
    }

    public String getAssetName() {
        return assetName;
    }

    public AgentMapping getAgentMapping() {
        return agentMapping;
    }

    public boolean isBroken() {
        return broken;
    }
}

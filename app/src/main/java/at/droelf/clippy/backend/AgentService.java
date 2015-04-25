package at.droelf.clippy.backend;

import at.droelf.clippy.backend.converter.AgentConverter;
import at.droelf.clippy.backend.converter.AgentConverterImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.raw.Agent;
import at.droelf.clippy.utils.O;

public class AgentService {

    private final AgentSource agentSource;

    public AgentService(AgentSource agentSource){
        this.agentSource = agentSource;
    }

    public O<UiAgent> getUiAgent(AgentType agentType){
        final O<Agent> agent = agentSource.getAgent(agentType);
        final AgentConverter agentConverter = new AgentConverterImpl(agentType);

        if(agent.isSuccess()){
            return new O<>(agentConverter.agentToUiAgent(agent.getData()));
        }else{
            return new O<>(agent.getError());
        }
    }


}

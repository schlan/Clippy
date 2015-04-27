package at.droelf.clippy.backend;

import android.content.Context;

import at.droelf.clippy.backend.converter.AgentConverter;
import at.droelf.clippy.backend.converter.AgentConverterImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.raw.Agent;
import at.droelf.clippy.utils.O;

public class AgentServiceImpl implements AgentService {

    private final AgentSource agentSource;

    public AgentServiceImpl(AgentSource agentSource){
        this.agentSource = agentSource;
    }

    public O<UiAgent> getUiAgent(Context context, AgentType agentType){
        final O<Agent> agent = agentSource.getAgent(context, agentType);
        final AgentConverter agentConverter = new AgentConverterImpl(agentType);

        if(agent.isSuccess()){
            return new O<>(agentConverter.agentToUiAgent(agent.getData()));
        }else{
            return new O<>(agent.getError());
        }
    }


}

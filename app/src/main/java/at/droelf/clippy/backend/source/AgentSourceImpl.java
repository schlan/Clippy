package at.droelf.clippy.backend.source;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import at.droelf.clippy.utils.O;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.raw.Agent;

public class AgentSourceImpl implements AgentSource {

    private final ObjectMapper objectMapper;

    public AgentSourceImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public O<Agent> getAgent(Context context, AgentType agentType) {
        try {
            final InputStream open = context.getAssets().open(agentType.getAssetName());
            return new O<>(objectMapper.readValue(open, Agent.class));
        } catch (IOException e) {
            return new O<>(e.getLocalizedMessage());
        }
    }
}

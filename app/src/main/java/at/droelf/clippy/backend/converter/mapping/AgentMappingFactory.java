package at.droelf.clippy.backend.converter.mapping;


import at.droelf.clippy.model.AgentType;

public class AgentMappingFactory {

    private final static AgentMapping NULL_MAPPING = new AgentMapping() {
        @Override
        public int[] getMapping() {
            return new int[0];
        }

        @Override
        public int getNumberRows() {
            return 0;
        }

        @Override
        public int getNumberColumns() {
            return 0;
        }

        @Override
        public int getEmptyFrameId() {
            return 0;
        }
    };

    public static AgentMapping getMapping(AgentType agentType){
        switch (agentType){
            case CLIPPY:
                return new ClippyMapping();
        }

        return NULL_MAPPING;
    }
}

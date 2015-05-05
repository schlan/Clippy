package at.droelf.clippy.backend.converter.mapping;

public interface AgentMapping {

    int[] getMapping();
    int[] getSoundMapping();
    int getNumberRows();
    int getNumberColumns();
    int getEmptyFrameId();
    int getFirstFrameId();

}

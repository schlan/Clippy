package at.droelf.clippy.backend.converter.mapping;

public interface AgentMapping {

    public int[] getMapping();
    public int[] getSOUND_MAPPING();
    public int getNumberRows();
    public int getNUMBER_COLUMNS();
    public int getEmptyFrameId();
    public int getFirstFrameId();

}

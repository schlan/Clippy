package at.droelf.clippy.backend.converter.mapping;

public interface AgentMapping {

    public int[] getMapping();
    public int[] getSoundMapping();
    public int getNumberRows();
    public int getNumberColumns();
    public int getEmptyFrameId();

}

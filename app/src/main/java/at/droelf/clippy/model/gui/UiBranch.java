package at.droelf.clippy.model.gui;

public class UiBranch {

    private final int frameIndex;
    private final int weight;

    public UiBranch(int frameIndex, int weight) {
        this.frameIndex = frameIndex;
        this.weight = weight;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public int getWeight() {
        return weight;
    }
}

package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {
    private final int frameIndex;
    private final int weight;

    @JsonCreator
    public Branch(
            @JsonProperty("frameIndex") int frameIndex,
            @JsonProperty("weight") int weight) {
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

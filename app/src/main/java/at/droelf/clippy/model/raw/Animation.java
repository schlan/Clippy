package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Animation {

    private final List<Frame> frames;
    private final Boolean useExitBranching;

    @JsonCreator
    public Animation(
            @JsonProperty("frames") List<Frame> frames,
            @JsonProperty("useExitBranching") Boolean useExitBranching) {
        this.frames = frames;
        this.useExitBranching = useExitBranching;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Boolean getUseExitBranching() {
        return useExitBranching;
    }
}

package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Animation {
    private final List<Frame> frames;

    @JsonCreator
    public Animation(
            @JsonProperty("frames") List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}

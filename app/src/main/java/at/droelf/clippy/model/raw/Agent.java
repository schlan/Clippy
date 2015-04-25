package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Agent {

    private final int overlayCount;
    private final List<Integer> sounds;
    private final List<Integer> frameSize;
    private final Map<String, Animation> animations;

    @JsonCreator
    public Agent(
            @JsonProperty("overlayCount") int overlayCount,
            @JsonProperty("sounds") List<Integer> sounds,
            @JsonProperty("framesize") List<Integer> frameSize,
            @JsonProperty("animations") Map<String, Animation> animations) {
        this.overlayCount = overlayCount;
        this.sounds = sounds;
        this.frameSize = frameSize;
        this.animations = animations;
    }

    public int getOverlayCount() {
        return overlayCount;
    }

    public List<Integer> getSounds() {
        return sounds;
    }

    public List<Integer>
    getFrameSize() {
        return frameSize;
    }

    public Map<String, Animation> getAnimations() {
        return animations;
    }
}

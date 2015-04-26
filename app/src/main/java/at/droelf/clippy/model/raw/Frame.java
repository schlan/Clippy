package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Frame {
    private final int duration;
    private final List<List<Integer>> images;
    private final Integer sound;
    private final int exitBranch;
    private final Branching branching;

    @JsonCreator
    public Frame(
            @JsonProperty("duration") int duration,
            @JsonProperty("images") List<List<Integer>> images,
            @JsonProperty("sound") Integer sound,
            @JsonProperty("exitBranch") int exitBranch,
            @JsonProperty("branching") Branching branching) {
        this.duration = duration;
        this.images = images;
        this.sound = sound;
        this.exitBranch = exitBranch;
        this.branching = branching;
    }

    public int getDuration() {
        return duration;
    }

    public List<List<Integer>> getImages() {
        return images;
    }

    public Integer getSound() {
        return sound;
    }

    public int getExitBranch() {
        return exitBranch;
    }

    public Branching getBranching() {
        return branching;
    }
}

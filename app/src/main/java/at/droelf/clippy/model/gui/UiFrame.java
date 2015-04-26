package at.droelf.clippy.model.gui;

import java.util.List;

public class UiFrame {

    private final int duration;
    private final List<Integer> imageIds;

    private final Integer exitBranch;
    private final List<UiBranch> branches;

    private final Integer soundId;

    public UiFrame(int duration, List<Integer> imageIds, Integer exitBranch, List<UiBranch> branches, Integer soundId) {
        this.duration = duration;
        this.imageIds = imageIds;
        this.exitBranch = exitBranch;
        this.branches = branches;
        this.soundId = soundId;
    }

    public int getDuration() {
        return duration;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }

    public Integer getExitBranch() {
        return exitBranch;
    }

    public List<UiBranch> getBranches() {
        return branches;
    }

    public Integer getSoundId() {
        return soundId;
    }
}

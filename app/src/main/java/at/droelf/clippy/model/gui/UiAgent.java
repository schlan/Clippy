package at.droelf.clippy.model.gui;

import java.util.Map;



public class UiAgent {

    private final int overlayCount;

    private final int frameWidth;
    private final int frameHeight;

    private final Map<String, UiAnimation> animations;

    public UiAgent(int overlayCount, int frameWidth, int frameHeight, Map<String, UiAnimation> animations) {
        this.overlayCount = overlayCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.animations = animations;
    }

    public int getOverlayCount() {
        return overlayCount;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public Map<String, UiAnimation> getAnimations() {
        return animations;
    }
}

package at.droelf.clippy.model.gui;

import java.util.List;

public class UiAnimation {

    private final List<UiFrame> uiFrames;

    public UiAnimation(List<UiFrame> uiFrames) {
        this.uiFrames = uiFrames;
    }

    public List<UiFrame> getUiFrames() {
        return uiFrames;
    }
}



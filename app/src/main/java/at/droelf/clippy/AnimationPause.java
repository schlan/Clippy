package at.droelf.clippy;

import java.util.Random;

public enum AnimationPause {

    Seldom(Global.INSTANCE.getContext().getString(R.string.settings_animation_seldom_id), 30000),
    Normal(Global.INSTANCE.getContext().getString(R.string.settings_animation_normal_id), 5000),
    Often(Global.INSTANCE.getContext().getString(R.string.settings_animation_often_id), 1000);

    private final String settingsValue;
    private final int basePause;
    private final Random random;


    AnimationPause(String settingsValue, int basePause){
        this.settingsValue = settingsValue;
        this.basePause = basePause;
        this.random = new Random();
    }

    public String getSettingsValue() {
        return settingsValue;
    }

    public int getBasePause() {
        return basePause;
    }

    public int getRandomPause(){
        return random.nextInt(getBasePause()) + getBasePause();
    }

    public static AnimationPause getAnimationPauseFromSettings(String settingsValue){
        for(AnimationPause animationPause : AnimationPause.values()){
            if(animationPause.getSettingsValue().equals(settingsValue)){
                return animationPause;
            }
        }
        return Normal;
    }
}

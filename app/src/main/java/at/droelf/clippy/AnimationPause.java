package at.droelf.clippy;

import java.util.Random;

public enum AnimationPause {

    Seldom("s", 30000),
    Normal("n", 5000),
    Often("o", 1000);

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

package at.droelf.clippy.storage;

import android.content.Context;
import android.content.SharedPreferences;

import at.droelf.clippy.AnimationPause;

public class SettingsStorage {

    public final static String NAME = "settings_storage";

    private final static String SETTINGS_START_ON_BOOT = "settings_start_boot";
    private final static boolean SETTINGS_START_ON_BOOT_DEFAULT = false;

    public final static String SETTINGS_ANIMATION_PAUSE = "settings_animation_pause";
    public final static String SETTINGS_ANIMATION_PAUSE_DEFAULT = AnimationPause.Normal.getSettingsValue();

    private final SharedPreferences sharedPreferences;

    public SettingsStorage(Context context){
        this.sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public boolean isSettingsStartOnBoot(){
        return sharedPreferences.getBoolean(SETTINGS_START_ON_BOOT, SETTINGS_START_ON_BOOT_DEFAULT);
    }

    public void setStartOnBoot(boolean activate){
        sharedPreferences.edit()
                .putBoolean(SETTINGS_START_ON_BOOT, activate)
                .apply();
    }

    public AnimationPause getAnimationPause(){
        return AnimationPause.getAnimationPauseFromSettings(sharedPreferences.getString(SETTINGS_ANIMATION_PAUSE,SETTINGS_ANIMATION_PAUSE_DEFAULT));
    }

    public void setAnimationPause(AnimationPause animationPause){
        sharedPreferences.edit()
                .putString(SETTINGS_ANIMATION_PAUSE, animationPause.getSettingsValue())
                .apply();
    }


}

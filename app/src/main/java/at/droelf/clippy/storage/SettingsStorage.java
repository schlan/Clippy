package at.droelf.clippy.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsStorage {

    public final static String NAME = "settings_storage";

    private final static String SETTINGS_START_ON_BOOT = "settings_start_boot";
    private final static boolean SETTINGS_START_ON_BOOT_DEFUALT = false;

    private final SharedPreferences sharedPreferences;

    public SettingsStorage(Context context){
        this.sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public boolean isSettingsStartOnBoot(){
        return sharedPreferences.getBoolean(SETTINGS_START_ON_BOOT, SETTINGS_START_ON_BOOT_DEFUALT);
    }

    public void setStartOnBoot(boolean activate){
        sharedPreferences.edit()
                .putBoolean(SETTINGS_START_ON_BOOT, activate)
                .apply();
    }


}

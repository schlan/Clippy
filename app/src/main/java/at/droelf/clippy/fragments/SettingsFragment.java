package at.droelf.clippy.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import at.droelf.clippy.R;
import at.droelf.clippy.storage.SettingsStorage;


public class SettingsFragment extends PreferenceFragment {

    public static String KEY = "settings_fragment_key";

    public static SettingsFragment newInstance(){
        final SettingsFragment settingsFragment = new SettingsFragment();
        settingsFragment.setRetainInstance(true);
        return settingsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(SettingsStorage.NAME);
        addPreferencesFromResource(R.layout.fragment_settings);
    }
}
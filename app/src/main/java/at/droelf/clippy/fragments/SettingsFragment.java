package at.droelf.clippy.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import at.droelf.clippy.R;
import at.droelf.clippy.storage.SettingsStorage;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

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
        addPreferencesFromResource(R.xml.fragment_settings);
        initPreferences();
    }

    private void initPreferences(){
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        final Preference preference = findPreference(SettingsStorage.SETTINGS_ANIMATION_PAUSE);
        preference.setSummary(((ListPreference)preference).getEntry());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);

        if (pref instanceof ListPreference) {
            ListPreference listPref = (ListPreference) pref;
            pref.setSummary(listPref.getEntry());
        }
    }
}
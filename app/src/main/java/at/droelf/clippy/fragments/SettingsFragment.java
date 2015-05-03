package at.droelf.clippy.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import at.droelf.clippy.Global;
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
        addPreferencesFromResource(R.layout.fragment_settings);
        initPrefernces();
    }

    private void initPrefernces(){
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
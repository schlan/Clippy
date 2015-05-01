package at.droelf.clippy;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import at.droelf.clippy.fragments.SettingsFragment;

public class SettingsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        final SettingsFragment mPrefsFragment = SettingsFragment.newInstance();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();

    }
}

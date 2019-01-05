package prokedex.com.xtreme.prokedex.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import prokedex.com.xtreme.prokedex.MainActivity;
import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.SettingsActivity;

public class SettingsFragment extends PreferenceFragment {

    final String TAG = "SettingsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PreferenceManager.setDefaultValues(getContext(), R.xml.pref_main, false);
        }
        SwitchPreference darkMode = (SwitchPreference) findPreference("dark_mode");
        darkMode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Log.d(TAG, "onPreferenceChange: Toggle Dark Mode");
                if(o instanceof Boolean){
                    boolean isChecked = (boolean) o;
                    if(isChecked){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        preference.setSummary("Dark mode enabled.");
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        preference.setSummary("Dark mode disabled.");
                    }
                }
                
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Snackbar.make(getView(), "Press back button to apply change.", Snackbar.LENGTH_LONG).show();
                }

                return true;
            }
        });
    }
}

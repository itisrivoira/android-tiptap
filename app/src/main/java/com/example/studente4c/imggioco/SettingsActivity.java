package com.example.studente4c.imggioco;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.prefs.PreferencesFactory;

public class SettingsActivity extends PreferenceActivity {

    private static SwitchPreference vite;
    //private static Preference seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new MainSettingsFragment()).commit();
    }

    public static class MainSettingsFragment extends PreferenceFragment{

        private SettingsActivity settingsActivity;
        private ListPreference listPreference;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            settingsActivity = new SettingsActivity();

            addPreferencesFromResource(R.xml.preferences);
            vite = (SwitchPreference) findPreference("vite");
            listPreference = (ListPreference) findPreference("nVite");
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Toast.makeText(getContext(),newValue.toString(), Toast.LENGTH_SHORT).show();
                    //settingsActivity.cambiaNVite(newValue.toString());
                    return true;
                }
            });
            vite.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(vite.isEnabled()){
                        //listPreference
                    }
                    return true;
                }
            });
        }
    }

  @Override
    protected void onResume() {
        super.onResume();

        /*vite.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if( newValue.equals(true)){

                }else{

                }

                return true;
            }
        });*/
    }
}

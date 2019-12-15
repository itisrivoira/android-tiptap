package com.example.studente4c.imggioco;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
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
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //abilito pulsante indietro nell'actionBar
    }

    public static class MainSettingsFragment extends PreferenceFragment{

        private ListPreference listPreference;
        private PreferenceScreen preferenceScreen;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            PreferenceManager preferenceManager = getPreferenceManager();

            preferenceScreen = getPreferenceScreen();

            vite = (SwitchPreference) findPreference("vite");
            listPreference = (ListPreference) findPreference("nVite");
            if (!preferenceManager.getSharedPreferences().getBoolean("vite", true)){ //controllo iniziale al valore dello switchPreference
                preferenceScreen.removePreference(listPreference);
            }

            vite.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(newValue.equals(false)){ //se lo switchPreference 'vite' è settato a false la listPreference scompare
                        preferenceScreen.removePreference(listPreference); //rimuovo la listPreference

                    }else{
                       preferenceScreen.addPreference(listPreference); //aggiungo la listPreference
                    }
                    return true;
                }
            });
        }
    }
}

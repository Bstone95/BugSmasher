package com.example.bugsmasher;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class prefsFragmentSettings extends PreferenceFragment {
    public prefsFragmentSettings () {
    }
    /** @noinspection deprecation*/
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preference from an XML resource
        addPreferencesFromResource(R.xml.pref_fragment_settings);
    }
    /** @noinspection deprecation*/
    @Override
    public void onResume(){
        super.onResume();

        Preference pref;

        pref = getPreferenceScreen().findPreference("key_highscore");
        String s = " " + Assets.high_score;
        pref.setSummary(s);

    }


}

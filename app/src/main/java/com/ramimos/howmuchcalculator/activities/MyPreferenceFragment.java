package com.ramimos.howmuchcalculator.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.ramimos.howmuchcalculator.R;
import com.ramimos.howmuchcalculator.logic.CurrencyType;

/**
 * Created by ramimoshe on 4/12/15.
 */
public class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
        ListPreference lp = (ListPreference)findPreference("key_preference");
        lp.setTitle(lp.getEntry());
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // just update all
        ListPreference lp = (ListPreference)findPreference("key_preference");
        lp.setTitle(lp.getEntry());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sourceCurrency", CurrencyType.valueOf(lp.getValue()).ordinal()); // value to store
        editor.commit();
    }
}

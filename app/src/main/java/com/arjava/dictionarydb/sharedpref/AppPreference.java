package com.arjava.dictionarydb.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arjava.dictionarydb.R;

/**
 * Created by arjava on 11/29/17.
 */

public class AppPreference {
    SharedPreferences preferences;
    Context context;

    public AppPreference(SharedPreferences preferences, Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = preferences.edit();
        String key = context.getResources().getString(R.string.app_name);
        editor.putBoolean(key, true);
        editor.apply();
//        editor.commit();
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.app_name);
        return preferences.getBoolean(key, true);
    }
}

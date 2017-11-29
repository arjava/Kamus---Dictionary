package com.arjava.dictionarydb.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arjava on 11/28/17.
 */

public class PrefManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    // mode shared pref
    int MODE_PRIVATE = 0;

    //nama file sharedpreference
    private static final String PREF_NAME = "kamus-dicoding";

    private static final String LAUNCH_PERTAMA_KALI = "PertamaDijalankan";

    public PrefManager (Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setPertamaKaliJalan(boolean yangPertama) {
        editor.putBoolean(LAUNCH_PERTAMA_KALI, yangPertama);
        editor.commit();
    }

    public boolean waktuPertamaLaunch() {
        return preferences.getBoolean(LAUNCH_PERTAMA_KALI, true);
    }
}

package com.arjava.dictionarydb.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.arjava.dictionarydb.MainActivity;
import com.arjava.dictionarydb.R;
import com.arjava.dictionarydb.database.DictionaryHelper;
import com.arjava.dictionarydb.sharedpref.PrefManager;

public class Preload extends AppCompatActivity {

    private static final String TAG = "Preload";
    private PrefManager prefManager;

    //panjang waktu splash screen
    private static int SPLASH_TIME_OUT = 5000;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //validasi pertama kali sebelum memanggil setContentView()
        Log.d(TAG, "onCreate: "+prefManager);
        prefManager = new PrefManager(this);
        if (!prefManager.waktuPertamaLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_preload);

        progressBar = findViewById(R.id.pgbarrPreload);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;

                intent = new Intent(Preload.this, MainActivity.class);

                Preload.this.startActivity(intent);
                Preload.this.finish();
            }
        }, SPLASH_TIME_OUT);
//        new LoadData().execute();
    }

    private void launchHomeScreen() {
        prefManager.setPertamaKaliJalan(false);
        startActivity(new Intent(Preload.this, MainActivity.class));
        finish();
    }

    private class LoadData {
        DictionaryHelper dictionaryHelper;
    }
}

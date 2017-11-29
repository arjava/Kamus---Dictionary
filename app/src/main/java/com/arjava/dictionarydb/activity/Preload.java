package com.arjava.dictionarydb.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
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
import com.arjava.dictionarydb.database.KamusHelper;
import com.arjava.dictionarydb.model.Model;
import com.arjava.dictionarydb.sharedpref.AppPreference;
import com.arjava.dictionarydb.sharedpref.PrefManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        DictionaryHelper dictionaryHelper;
        KamusHelper kamusHelper;
        AppPreference appPreference;

        double progress;
        double max_progress = 100;
        //sebelum menjalankan kode secara terpisah


        @Override
        protected void onPreExecute() {
            dictionaryHelper = new DictionaryHelper(Preload.this);
            kamusHelper = new KamusHelper(Preload.this);
            appPreference = new AppPreference(Preload.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();
            Log.d(TAG, "First RUN : "+firstRun);
            progress = 20;

            if (firstRun){
                ArrayList<Model> indModels = preLoadRawEnglish();
                ArrayList<Model> engModels = preLoadRawIndonesian();
            }
        }
    }

    private ArrayList<Model> preLoadRawEnglish() {
        ArrayList<Model> dictionaryModel = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources resources = getResources();
            InputStream raw_dict = resources.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] split = line.split("\t");
                Model dictionaryModels;
                dictionaryModels = new Model(split[0], split[1]);
                dictionaryModel.add(dictionaryModels);
                count++;
            }while (line != null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dictionaryModel;
    }

    private ArrayList<Model> preLoadRawIndonesian() {
        ArrayList<Model> kamusModel = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources resources = getResources();
            InputStream raw_kamus = resources.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_kamus));
            int count = 0;
            do {
                line = reader.readLine();
                String[] split = line.split("\t");
                Model kamusModels;
                kamusModels = new Model(split[0], split[1]);
                kamusModel.add(kamusModels);
                count++;
            }while (line != null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return kamusModel;
    }

}

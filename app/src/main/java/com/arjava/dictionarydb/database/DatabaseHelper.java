package com.arjava.dictionarydb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arjava on 11/28/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //nama database
    public static String DB_KAMUS_DICTIONARY = "db_kamus_dictionary";

    //nama tabel
    public static String TABLE_DICTIONARY = "table_dictionary";
    public static String TABLE_KAMUS = "table_kamus";

    //field (isi item tabel)
    public static String FIELD_ID = "id";
    public static String FIELD_WORD = "word";
    public static String FIELD_DESCRIPTION = "description";

    //versi database
    private static final int DATABASE_VERSION = 3;

    //query membuat tabel dictionary
    public static String CREATE_TABLE_DICTIONARY = "create table " +TABLE_DICTIONARY+ " (" +FIELD_ID +" integer primary key autoincrement, " +
            FIELD_WORD + " text not null, " +
            FIELD_DESCRIPTION + "text not null);";

    //query membuat tabel kamus
    public static String CREATE_TABLE_KAMUS = "create table " +TABLE_KAMUS+ " (" +FIELD_ID +" integer primary key autoincrement, " +
            FIELD_WORD + " text not null, " +
            FIELD_DESCRIPTION + "text not null);";

    public DatabaseHelper(Context context) {
        super(context, DB_KAMUS_DICTIONARY, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_DICTIONARY);
        sqLiteDatabase.execSQL(CREATE_TABLE_KAMUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+TABLE_DICTIONARY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+TABLE_KAMUS);
        onCreate(sqLiteDatabase);
    }
}

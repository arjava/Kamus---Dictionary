package com.arjava.dictionarydb.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by arjava on 11/28/17.
 */

public class DictionaryHelper {

    private static String TABLE_DICTIONARY = DatabaseHelper.TABLE_DICTIONARY;
    private static Cursor insertTransaction;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase sqLiteDatabase;

    public DictionaryHelper(Context context) {
        this.context = context;
    }

    public DictionaryHelper open() throws SQLiteException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }
}

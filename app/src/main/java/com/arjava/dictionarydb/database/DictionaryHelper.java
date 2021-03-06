package com.arjava.dictionarydb.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.arjava.dictionarydb.model.Model;

import java.util.ArrayList;

/**
 * Created by arjava on 11/28/17.
 */

public class DictionaryHelper {

    private static String TABLE_DICTIONARY = DatabaseHelper.TABLE_DICTIONARY;
//    private static Cursor insertTransaction;
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

    public Cursor searchQueryByName(String query) {
        return sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_DICTIONARY + " WHERE " + DatabaseHelper.FIELD_WORD +
        " LIKE '%" + query + "%' ", null);
    }

    public String getData(String word) {
        String result = "";
        Cursor cursor = searchQueryByName(word);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            result = cursor.getString(2);
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                result = cursor.getString(2);
            }
        }
        cursor.close();
        return result;
    }

    public ArrayList<Model> getSearchData(String queries){
        ArrayList<Model> arrayList = new ArrayList<>();
        Cursor cursor = searchQueryByName(queries);
        cursor.moveToFirst();
        Model model;
        if (cursor.getCount()>0) {
            do {
                model = new Model();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                model.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_WORD)));
                model.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_DESCRIPTION)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryAllData(){
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_DICTIONARY + " ORDER BY " + DatabaseHelper.FIELD_ID/*+" ASC limit 20"*/, null);
    }

    public ArrayList<Model> getAllData(){
        ArrayList<Model> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData();
        cursor.moveToFirst();
        Model model;
        if (cursor.getCount()>0){
            do {
                model = new Model();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                model.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_WORD)));
                model.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_DESCRIPTION)));

                arrayList.add(model);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public void close() {
        databaseHelper.close();
    }
}

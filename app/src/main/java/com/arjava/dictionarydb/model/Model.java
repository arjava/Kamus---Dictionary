package com.arjava.dictionarydb.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arjava on 11/29/17.
 */

public class Model implements Parcelable {

    private int id;
    private String word;
    private String translate;

    public Model(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.translate);
    }

    protected Model(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}

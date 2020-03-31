package io.github.sumeta.android.sqlite;

import android.provider.BaseColumns;

public class DataModel {

    public static final String TABLE = "history";

    public DataModel(){ }

    public DataModel(int id, String text){
        this.id = id;
        this.text = text;
    }

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String TEXT = "text";

    }

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

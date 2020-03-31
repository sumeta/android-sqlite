package io.github.sumeta.android.sqlite;

import android.provider.BaseColumns;

public class DataModel {

    public static final String TABLE = "history";

    public DataModel(){ }

    public DataModel(int id, String content){
        this.id = id;
        this.text = text;
    }

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String CONTENT = "text";

    }

    private int id;
    private String text;

}

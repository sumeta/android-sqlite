package io.github.sumeta.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataDao extends DBHelper {

    private SQLiteDatabase sqLiteDatabase;
    Context context;

    public DataDao(Context context) {
        super(context);
        sqLiteDatabase = this.getWritableDatabase();
    }

    public DataModel get(String id) {
        DataModel model = new DataModel();
        String whereClause = BaseColumns._ID+" =?";
        String[] whereArgs = new String[]{id};
        Cursor cursor = sqLiteDatabase.query
                (DataModel.TABLE, null, whereClause, whereArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Integer idKey = Integer.parseInt(cursor.getString(0));
            model.setId(idKey);
            model.setText(cursor.getString(1));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        cursor.close();

        return model;
    }

    public List<DataModel> getList() {
        List<DataModel> models = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query
                (DataModel.TABLE, null, null, null, null, null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            DataModel model = new DataModel();
            Integer idKey = Integer.parseInt(cursor.getString(0));
            model.setId(idKey);
            model.setText(cursor.getString(1));
            models.add(model);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        cursor.close();

        return models;
    }

    public long insert(DataModel model) {
        Log.i("DB","Start Insert");
        ContentValues values = new ContentValues();
        values.put(DataModel.Column.TEXT, model.getText());

        long id = sqLiteDatabase.insert(DataModel.TABLE, null, values);
        sqLiteDatabase.close();

        Log.i("DB","Inserted");

        return id;
    }

    public boolean update(DataModel model){
        ContentValues values = new ContentValues();
        values.put(DataModel.Column.ID, model.getId());
        values.put(DataModel.Column.TEXT, model.getText());
        sqLiteDatabase.update(DataModel.TABLE,values,
                DataModel.Column.ID + " = ? ",
                new String[] { String.valueOf(model.getId()) });
        sqLiteDatabase.close();

        return true;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM `history` WHERE " + BaseColumns._ID + " = " + id;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
        return true;
    }

    public void clear() {
        String sql = "DELETE FROM `history`";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }

}

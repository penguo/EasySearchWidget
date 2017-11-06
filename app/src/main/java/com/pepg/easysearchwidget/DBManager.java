package com.pepg.easysearchwidget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pengu on 2017-11-06.
 */

public class DBManager extends SQLiteOpenHelper {

    Cursor cursor;
    SQLiteDatabase db;

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE LIST ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT," +
                " LINK TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE LIST ");
        onCreate(db);
    }

    public int getSize() {
        db = getReadableDatabase();
        cursor = db.rawQuery("SELECT COUNT(_id) FROM LIST;", null);
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }

    public void insert(String name, String link) {
        db = getWritableDatabase();
        db.execSQL(" INSERT INTO LIST VALUES (" +
                "null," +
                "'" + name + "'" +
                "'" + link + "');");
    }

    public void delete(int id){
        db = getWritableDatabase();
        db.execSQL("DELETE FROM LIST WHERE _id = '" + id + "';");
    }
}

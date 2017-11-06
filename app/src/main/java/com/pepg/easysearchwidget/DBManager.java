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
    public static String DATA_NAME, DATA_LINK;
    public static int DATA_LINKNUM, DATA_WIDGETID, DATA_ID;


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE LIST ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT," +
                " LINK TEXT);");
        db.execSQL(" CREATE TABLE WIDGET ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " WIDGETID INTEGER , " +
                " LINKNUM INTEGER);");
        addDefaultValue(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        reset();
    }

    public void reset() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE LIST;");
        db.execSQL("DROP TABLE WIDGET;");
        onCreate(db);
        db.close();
    }

    public int getSize() {
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT _id FROM LIST;", null);
        int result = cursor.getCount();
        cursor.close();
        db.close();
        return result;
    }

    public void insert(String name, String link) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" INSERT INTO LIST VALUES (" +
                "null," +
                "'" + name + "'," +
                "'" + link + "');");
        db.close();
    }

    public void getValue(int id) {
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM LIST WHERE _id = '" + id + "';", null);
        while (cursor.moveToNext()) {
            DATA_NAME = cursor.getString(1);
            DATA_LINK = cursor.getString(2);
        }
        cursor.close();
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM LIST WHERE _id = '" + id + "';");
        db.close();
    }

    public void addDefaultValue(SQLiteDatabase db) {
        db.execSQL(" INSERT INTO LIST VALUES ( 1, '네이버 영어사전', 'http://endic.naver.com/' );");
        db.execSQL(" INSERT INTO LIST VALUES ( 2, '나무위키', 'http://namu.wiki' );");

    }

    public void insertWidget(int widgetId, int linkNum) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" INSERT INTO WIDGET VALUES (" +
                "null, " +
                widgetId + ", " +
                linkNum + ");");
        db.close();
    }

    public void getWidgetValue(int widgetId) {
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM WIDGET WHERE _id = '" + widgetId + "';", null);
        while (cursor.moveToNext()) {
            DATA_ID = cursor.getInt(0);
            DATA_WIDGETID = cursor.getInt(1);
            DATA_LINKNUM = cursor.getInt(2);
        }
        cursor = db.rawQuery("SELECT * FROM LIST WHERE _id = '" + DATA_LINKNUM + "';", null);
        while (cursor.moveToNext()) {
            DATA_NAME = cursor.getString(1);
            DATA_LINK = cursor.getString(2);
        }
        cursor.close();
        db.close();
    }

    public void deleteWidget(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM WIDGET WHERE _id = " + id + ";");
        db.close();
    }
}

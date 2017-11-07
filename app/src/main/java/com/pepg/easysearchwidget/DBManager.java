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
    public static int DATA_WIDGETLINKNUM, DATA_WIDGETID, DATA_position;


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE LIST ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " _position INTEGER, " +
                " NAME TEXT," +
                " LINK TEXT);");
        db.execSQL(" CREATE TABLE WIDGET ( " +
                " _widgetID INTEGER PRIMARY KEY, " +
                " _position INTEGER, " +
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
        db.execSQL(" INSERT INTO LIST VALUES ( null, null, '네이버 영어사전', 'http://endic.naver.com/' );");
        db.execSQL(" INSERT INTO LIST VALUES ( null, null, '나무위키', 'http://namu.wiki' );");

    }

    public void insertWidget(int widgetId, int linkNum) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" INSERT INTO WIDGET VALUES (" +
                "null, " +
                widgetId + ", " +
                linkNum + ");");
        db.close();
    }

    public void setWidgetPosition() {
        SQLiteDatabase dbR = getReadableDatabase();
        SQLiteDatabase dbW = getWritableDatabase();
        dbW.execSQL("UPDATE WIDGET SET _position = -1;");
        int i = 0;
        cursor = dbR.rawQuery("SELECT _widgetID FROM WIDGET;", null);
        while(cursor.moveToNext()){
            dbW.execSQL("UPDATE TODOLIST SET _position = " + i + " WHERE _widgetID = " + cursor.getInt(0) + ";");
            i++;
        }
    }

    public void getWidgetValue(int position) {
        DATA_WIDGETID = 0;
        DATA_WIDGETLINKNUM = 0;
        DATA_NAME = "";
        DATA_LINK = "";
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM WIDGET WHERE _position = '" + position + "';", null);
        while (cursor.moveToNext()) {
            DATA_WIDGETID = cursor.getInt(0);
            DATA_WIDGETLINKNUM = cursor.getInt(1);
        }
        if (DATA_WIDGETID != 0) {
            cursor = db.rawQuery("SELECT * FROM LIST WHERE _id = '" + DATA_WIDGETLINKNUM + "';", null);
            while (cursor.moveToNext()) {
                DATA_NAME = cursor.getString(1);
                DATA_LINK = cursor.getString(2);
            }
        } else {
            DATA_NAME = "NEW WIDGET";
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

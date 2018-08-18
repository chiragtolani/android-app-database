package com.firstapp.tolani.myfirstapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class RegisterDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "registerDB.db";
    public static final String TABLE_INFO = "register";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_PASSWORD = "_password";

    public RegisterDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_INFO + "(" +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        onCreate(db);

    }

    public void addInfo(RegisterDB rdb){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, rdb.get_username());
        values.put(COLUMN_PASSWORD, rdb.get_password());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INFO, null, values);
        db.close();
    }

    public String getSingleEntry(String userName)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor= db.query(TABLE_INFO, null, COLUMN_USERNAME + "=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
        cursor.close();
        return password;
    }
}

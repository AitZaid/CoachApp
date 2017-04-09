package com.example.cyk.coachingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 09/04/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Matchs.db";

    public static final String SQL_DELETE = "DRP TABLE IF EXISTS "+DATABASE_NAME;

    public static final String SQL_Create = "CREATE TABLE matchs (_id INTEGER PRIMARY KEY AUTOINCREMENT, score1 INTEGER, score2 INTEGER, name TEXT, time TEXT,shots INTEGER, ShotsOn INTEGER, Fouls INTEGER, Offsides INTEGER, Yellows INTEGER, Reds INTEGER, X DOUBLE, Y DOUBLE, uri TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}

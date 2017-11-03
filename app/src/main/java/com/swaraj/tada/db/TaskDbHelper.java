package com.swaraj.tada.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by swaraj on 01/11/17
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "tasks.db";
    private static final int DB_VERSION = 1;



    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    public TaskDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TASKS_TABLE = "CREATE TABLE " + TaskContract.AllTasks.TABLE_NAME +
                "(" +
                TaskContract.AllTasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.AllTasks.CONTENT + " TEXT NOT NULL, " +
                TaskContract.AllTasks.ENTRY_TIME + " TEXT DEFAULT NULL, " +
                TaskContract.AllTasks.REMINDER_TIME + " TEXT DEFAULT NULL, " +
                TaskContract.AllTasks.PRIORITY + " TEXT DEFAULT NULL" +
                ");";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String DROP_TABLE = "(DROP TABLE IF EXISTS " + TaskContract.AllTasks.TABLE_NAME + ")";
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}

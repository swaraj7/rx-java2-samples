package com.swaraj.tada.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by swaraj on 01/11/17
 */

public class TaskProvider extends ContentProvider{

    private static TaskDbHelper taskDbHelper;

    public static final UriMatcher uriMatcher = buildUriMatcher();

    public static SQLiteQueryBuilder sTaskFromAllTableQueryBuilder;

    private final static int TASK = 1;

    static {
        sTaskFromAllTableQueryBuilder = new SQLiteQueryBuilder();
        sTaskFromAllTableQueryBuilder.setTables(TaskContract.AllTasks.TABLE_NAME);
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(TaskContract.CONTENT_AUTHORITY, TaskContract.PATH_TASKS, TASK);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        taskDbHelper = new TaskDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = taskDbHelper.getReadableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri = null;
        switch (match) {
            case TASK:
                 return db.query(TaskContract.AllTasks.TABLE_NAME, projection, null, null, null, null, null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TASK:
                return TaskContract.AllTasks.CONTENT_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = taskDbHelper.getReadableDatabase();

        final int match = uriMatcher.match(uri);
        Uri returnUri = null;
        switch (match) {
            case TASK:
                long id = db.insert(TaskContract.AllTasks.TABLE_NAME, null, values);
                if (id > 0)
                    returnUri = TaskContract.AllTasks.getTaskUriWithId(values.getAsString(TaskContract.AllTasks._ID));
                else
                    throw new SQLException("Unable to insert row into " + uri);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

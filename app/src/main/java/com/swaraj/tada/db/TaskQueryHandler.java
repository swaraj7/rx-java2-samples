package com.swaraj.tada.db;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by swaraj on 02/11/17
 */

public class TaskQueryHandler extends AsyncQueryHandler {

    QueryCompleteListener queryCompleteListener;

    public interface QueryCompleteListener {
        void onQueryComplete(int token, Object cookie, Cursor cursor);
    }

    public TaskQueryHandler(ContentResolver cr) {
        super(cr);
    }

    @Override
    protected void onDeleteComplete(int token, Object cookie, int result) {
        super.onDeleteComplete(token, cookie, result);
    }

    @Override
    protected void onInsertComplete(int token, Object cookie, Uri uri) {
        super.onInsertComplete(token, cookie, uri);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
        queryCompleteListener.onQueryComplete(token, cookie, cursor);
    }

    @Override
    protected void onUpdateComplete(int token, Object cookie, int result) {
        super.onUpdateComplete(token, cookie, result);
    }


    public void startQuery(Uri uri, String[] projection, String selection, String[] selectionArgs,
                           String orderBy, QueryCompleteListener queryListener){
        queryCompleteListener = queryListener;
        startQuery(0, null, uri, projection, selection, selectionArgs, orderBy);
    }
}

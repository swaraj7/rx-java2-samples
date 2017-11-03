package com.swaraj.tada.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by swaraj on 01/11/17
 */

public class TaskContract {

    public static String CONTENT_AUTHORITY = "com.swaraj.tada.contentprovider";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static String PATH_TASKS = "tasks";

    public static class AllTasks implements BaseColumns {

        public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_TASKS).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_TASKS;
        public static final String  CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY;

        public static String TABLE_NAME = "all_task_db";
        public static String ENTRY_TIME = "entry_time";
        public static String REMINDER_TIME = "reminder_time";
        public static String CONTENT = "content";
        public static String PRIORITY = "priority";

        public static Uri getTaskUriWithId(String id) {
            return BASE_CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }
}

package sonyama.todolist.data;

import android.provider.BaseColumns;

/**
 * Created by sonyama on 1/11/16.
 */
public class TaskConTract {

    //Each of xxxEntry corresponds to a table in the database
    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_TASK = "task";
    }
}

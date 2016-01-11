package sonyama.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonyama on 1/11/16.
 */
public class TaskDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASKS_TABLE =
                "CREATE TABLE " + TaskConTract.TaskEntry.TABLE_NAME + " (" +
                        TaskConTract.TaskEntry._ID + " INTEGER PRIMARY KEY," +
                        TaskConTract.TaskEntry.COLUMN_TASK + " TEXT NOT NULL" +
                        " );";
        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskConTract.TaskEntry.TABLE_NAME);
        onCreate(db);
    }
}

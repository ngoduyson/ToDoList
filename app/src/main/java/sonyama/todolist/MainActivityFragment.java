package sonyama.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import sonyama.todolist.data.TaskConTract;
import sonyama.todolist.data.TaskDBHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    TaskAdapter mTaskAdapter;

    // These indices are tied to TASKS_COLUMNS. If TASKS_COLUMNS changes, these must change.
    static final int COLTASK_ID = 0;
    static final int COL_TASK_NAME = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Usual inflating of the fragment layout file
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Find the listView
        ListView listView = (listView) rootView.findViewById(R.id.listview_tasks);

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //Query databases to get any existing data
        Cursor cursor = sqlDB.query(TaskConTract.TaskEntry.TABLE_NAME,
                new String[]{TaskConTract.TaskEntry._ID, TaskConTract.TaskEntry.COLUMN_TASK},
                null, null, null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        mTaskAdapter = new TaskAdapter(getActivity(), cursor);
        listView.setAdapter(mTaskAdapter);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add a task");
                builder.setMessage("What do you want to do");
                final EditText inputField = new EditText(getActivity());
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Get user input
                        String inputTask = inputField.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

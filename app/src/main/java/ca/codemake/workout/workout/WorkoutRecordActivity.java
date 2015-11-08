package ca.codemake.workout.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.SelectableAdapter;
import ca.codemake.workout.adapters.WorkoutRecordAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class WorkoutRecordActivity extends AppCompatActivity {

    private WorkoutDbHelper db;
    //    private WorkoutRecordListAdapter adapter;
    private final static String TAG = "WorkoutRecordActivity";
    private ArrayList<Item> items;

    private ExerciseEntry exerciseEntry;
    private Workout workout;

    private RecyclerView recyclerView;
    private WorkoutRecordAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView empty;

    private Toolbar toolbar;
    private final int RESULT_CODE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);
//        setContentView(R.layout.activity_workout_record);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FrameLayout v = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_workout_record, v, true);

        TextView workout_name = (TextView) findViewById(R.id.workout_name);

        Intent intent = getIntent();
        workout_name.setText(intent.getStringExtra("exercise_name"));

//        db = WorkoutDbHelper.getInstance(getApplicationContext());
        items = new ArrayList<>();

        for (int i = 0; i < intent.getIntExtra("sets", 0); i++) {
            items.add(new ExerciseEntry("Bench Press", 155, 5, 5));
        }

//        items.add(new ExerciseEntry("Bench Press", 155, 5, 5));
//        items.add(new ExerciseEntry("Leg Press", 155, 5, 5));
//        items.add(new ExerciseEntry("Shoulder Press", 155, 5, 5));

/*        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);

        if(cursor.moveToFirst()) {
            do {
                if(cursor.isFirst()) {
                    initWorkout(cursor);
                }
                initExerciseEntry(cursor);
            } while (cursor.moveToNext());
        }*/

        loadRecyclerView();
    }

    private void loadRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new WorkoutRecordAdapter(this, items);
        adapter.setClickListener(new SelectableAdapter.ClickListener() {
            public void onClick(int position) {
//                if (actionMode == null) {
//                    Intent intent = new Intent(getActivity(), WorkoutRecordActivity.class);
//                    startActivityForResult(intent, REQUEST_CODE);
//                } else {
//                    adapter.toggleSelection(position);
//
//                    if (adapter.getSelectedItems().size() > 0) {
//                        actionMode.setTitle(adapter.getSelectedItems().size() + " item(s) Selected");
//                    } else {
//                        actionMode.finish();
//                    }
//                }
            }

            public void onLongClick(int position) {
//                if (actionMode == null) {
//                    // Start the contextual action mode using the ActionMode.Callback
//                    actionMode = getActivity().startActionMode(actionModeCallback);
//                }
//
//                adapter.toggleSelection(position);
//
//                if (adapter.getSelectedItems().size() > 0) {
//                    actionMode.setTitle(adapter.getSelectedItems().size() + " item(s) Selected");
//                } else {
//                    actionMode.finish();
//                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_routine_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(getApplicationContext(), "Back home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_new_routine:
//                Toast.makeText(getApplicationContext(), "New set", Toast.LENGTH_SHORT).show();
                adapter.addNewSet(new ExerciseEntry("Shoulder Press", 155, 5, 5));
                return true;
            case R.id.action_save:
                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void finish() {
        // Prepare intent
        Intent intent = new Intent();
        intent.putExtra("selected_fragment", WorkoutRecordActivity.this.getClass().getSimpleName());
        // Activity finished ok, return the data
        setResult(RESULT_CODE, intent);
        super.finish();
    }

    protected void onPause() {
        super.onPause();
//        Log.d(TAG, "onPause");
//        db.close("WorkoutRecordActivity");
    }

    protected void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume");
//        db.open("WorkoutRecordActivity");
//        db = WorkoutDbHelper.getInstance(getApplicationContext());
    }
}
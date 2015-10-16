package ca.codemake.workout.workout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.RoutineCreateAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Routine;
import ca.codemake.workout.models.Workout;

public class CreateRoutineActivity extends AppCompatActivity {

    private static final String TAG = CreateRoutineActivity.class.getName();
    private RoutineCreateAdapter adapter;
    private WorkoutDbHelper db;
    private ArrayList<Item> items;
    private boolean newRoutine;

    private TextView routineName;
    private Routine routine;
    private long routine_id;

    private Workout workout;
    private ExerciseEntry exerciseEntry;

    private int numWorkouts = 0;

    private Toolbar toolbar;

    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

//        db = new WorkoutDbHelper(getApplicationContext());
        db = WorkoutDbHelper.getInstance(getApplicationContext());
        adapter = new RoutineCreateAdapter(this);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.routine_list);

        routineName = (TextView) findViewById(R.id.routine_name);
        newRoutine = false;

        View empty = findViewById(R.id.emptyView);
        listView.setEmptyView(empty);

//        loadData();
        items = new ArrayList<>();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_routine_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_new_routine:
//                createNewRoutineDialog();
                createNewWorkout();
                return true;
            case R.id.action_edit:
                return true;
            case R.id.action_save:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createNewWorkout() {
        workout = new Workout("Sample Workout " + (++numWorkouts), false);
        items.add(workout);

        setAdapter();
    }

    private void setAdapter() {
        adapter.setItems(items);
        listView.setAdapter(adapter);
    }

    private void createNewRoutineDialog() {
        /* Create a dialog with a calendar view */
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateRoutineActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = CreateRoutineActivity.this.getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_create_routine, null);
        builder.setView(view)
                .setTitle("Create Routine")
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText routineName = (EditText) view.findViewById(R.id.routine_name_in_dialog);
                        createNewRoutine(routineName.getText().toString());
                        Toast.makeText(getApplicationContext(), "Save " + routineName.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });



//                View view = inflater.inflate(R.layout.calendar, null);

//                CalendarView calendar = (CalendarView) view.findViewById(R.id.calendar);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going to the dialog layout
//                builder.setView(view);

//                builder.setTitle("Title");
//                builder.setMessage("Hello World!");

        AlertDialog dialog = builder.create();
        dialog.show();
//                Intent i = new Intent(getApplicationContext(), AddFoodActivity.class);
//                startActivity(i);
    }

    private void createNewRoutine(String route_name) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        routine_id = db.newRoutine(route_name, simpleDateFormat.format(calendar.getTime()));

        newRoutine = true;
        loadData();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    private void loadData() {
        items = new ArrayList<>();
        Cursor cursor;

        if(newRoutine){
            cursor = db.getRoutineById(routine_id);

            if(cursor.moveToFirst()) {
                initRoutine(cursor);
            }

            workout = new Workout("Sample Workout", false);
//            items.add(workout);

            newRoutine = false;
        } else {
            // Get Exercise Entries
            cursor = db.getExerciseEntries();

            // Get routine name and id
//            TextView routineName = (TextView) findViewById(R.id.routine_name);
//            Routine routine;

            // Get workout name and id
//            Workout workout;
//            String workout_name = null;

            // Get exercise entry id, name, sets
//            ExerciseEntry exerciseEntry;

            if(cursor.moveToFirst()) {
                do {
                    if(cursor.isFirst()) {
                        initRoutine(cursor);
                        initWorkout(cursor);
                    }

                    if(!workout.getName().equals(cursor.getString(cursor.getColumnIndex("workout_name")))) {
                        initWorkout(cursor);
                    }

                    initExerciseEntry(cursor);
                } while (cursor.moveToNext());
            }
        }

        adapter.setItems(items);

        listView.setAdapter(adapter);
    }

    private void initExerciseEntry(Cursor cursor) {
        exerciseEntry = new ExerciseEntry(
                cursor.getLong(cursor.getColumnIndex("exercise_entry_id")),
                cursor.getString(cursor.getColumnIndex("exercise_name")),
                cursor.getLong(cursor.getColumnIndex("sets")));

        items.add(exerciseEntry);
    }

    private void initWorkout(Cursor cursor) {
        workout = new Workout(cursor.getString(cursor.getColumnIndex("workout_name")));
        items.add(workout);
    }

    private void initRoutine(Cursor cursor) {
        routine_id = cursor.getInt(cursor.getColumnIndex("routine_id"));
        routine = new Routine(cursor.getString(cursor.getColumnIndex("routine_name")));
        routine.setId((int)routine_id);
        routineName.setText(routine.getName());
    }

    protected void onResume() {
        super.onResume();
        db = WorkoutDbHelper.getInstance(getApplicationContext());
//        db.open("CreateRoutineActivity");
    }

    protected void onPause() {
        super.onPause();
//        db.close("CreateRoutineActivity");
    }
}
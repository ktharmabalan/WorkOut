package ca.codemake.workout;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.codemake.workout.adapters.RoutineCreateAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class WorkoutInputActivity extends Activity {

    private WorkoutDbHelper db;
    private RoutineCreateAdapter adapter;
    private final static String TAG = "WorkoutInputActivity";
    private ArrayList<Item> items;

    private ExerciseEntry exerciseEntry;
    private Workout workout;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_input);

        listView = (ListView) findViewById(R.id.list_workout_tracker);

        db = new WorkoutDbHelper(getApplicationContext());
        items = new ArrayList<>();

        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);

        if(cursor.moveToFirst()) {
            do {
                if(cursor.isFirst()) {
                    initWorkout(cursor);
                }
                initExerciseEntry(cursor);
            } while (cursor.moveToNext());
        }

        adapter = new RoutineCreateAdapter(this);
        adapter.setItems(items);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

            }
        };

        listView.setOnItemClickListener(onItemClickListener);
        listView.setItemsCanFocus(true);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        db.close();
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        db.open();
    }
}

package ca.codemake.workout.workout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.WorkoutRecordAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class WorkoutRecordActivity extends AppCompatActivity {

    private WorkoutDbHelper db;
    private WorkoutRecordAdapter adapter;
    private final static String TAG = "WorkoutRecordActivity";
    private ArrayList<Item> items;

    private ExerciseEntry exerciseEntry;
    private Workout workout;
    private ListView listView;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_record);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.workout_record);

        db = new WorkoutDbHelper(getApplicationContext());
        items = new ArrayList<>();

        items.add(new ExerciseEntry("Bench Press", 155, 5, 5));
        items.add(new ExerciseEntry("Leg Press", 155, 5, 5));
        items.add(new ExerciseEntry("Shoulder Press", 155, 5, 5));

/*        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);

        if(cursor.moveToFirst()) {
            do {
                if(cursor.isFirst()) {
                    initWorkout(cursor);
                }
                initExerciseEntry(cursor);
            } while (cursor.moveToNext());
        }*/

        adapter = new WorkoutRecordAdapter(this);
        adapter.setItems(items);
        listView.setAdapter(adapter);
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

package ca.codemake.workout;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ca.codemake.workout.adapters.WorkoutCreateAdapter;
import ca.codemake.workout.models.WorkoutHeading;
import ca.codemake.workout.models.WorkoutRow;


public class CreateWorkoutActivity extends ListActivity {

    private WorkoutCreateAdapter mAdapter;

    String[] workdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] exercises = {"Bench Press", "Squat", "Rows", "Shoulder Press"};
    String[] bodyGroup = {"Chest", "Back", "Shoulders", "Arms", "Legs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_workout_pattern);


        ArrayList<WorkoutHeading> workoutHeadings = new ArrayList<>();
        workoutHeadings.add(new WorkoutHeading(workdays[0], "Chest"));
        workoutHeadings.add(new WorkoutHeading(workdays[1], "Back"));
        workoutHeadings.add(new WorkoutHeading(workdays[2], "Shoulders"));
        workoutHeadings.add(new WorkoutHeading(workdays[3], "Arms"));
        workoutHeadings.add(new WorkoutHeading(workdays[4], "Legs"));
        workoutHeadings.add(new WorkoutHeading(workdays[5], "Chest"));
        workoutHeadings.add(new WorkoutHeading(workdays[6], "Shoulders"));

        ArrayList<WorkoutRow> workoutRows = new ArrayList<>();
        workoutRows.add(new WorkoutRow("Bench Press", 5));
        workoutRows.add(new WorkoutRow("Shoulder Press", 5));
        workoutRows.add(new WorkoutRow("Leg Press", 5));
        workoutRows.add(new WorkoutRow("Curls", 5));
        workoutRows.add(new WorkoutRow("Rows", 5));


        mAdapter = new WorkoutCreateAdapter(this);
        int exercise = 1;
        for (int i = 0; i < workoutHeadings.size(); i++) {
            mAdapter.addHeading(workoutHeadings.get(i));
//            if(i == 1) mAdapter.addHeading(workdays[]);
            for (int j = 0; j < workoutRows.size(); j++) {
                mAdapter.addRow(workoutRows.get(j));
                exercise++;
            }
            exercise=1;
        }
        setListAdapter(mAdapter);
        Log.v("COUNT: ", String.valueOf(mAdapter.getCount()));

    }
}

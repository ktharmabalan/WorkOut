package ca.codemake.workout;

import android.app.ListActivity;
import android.os.Bundle;

import ca.codemake.workout.adapters.ChooseExerciseAdapter;

public class ChooseExerciseActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercises);

        ChooseExerciseAdapter mAdapter = new ChooseExerciseAdapter(this);

        mAdapter.addExercise("Squats");
        mAdapter.addExercise("Bench Press");
        mAdapter.addExercise("Shoulder Press");
        mAdapter.addExercise("Leg Press");
        mAdapter.addExercise("Curls");
        mAdapter.addExercise("Rows");
        mAdapter.addExercise("Calve Raises");

        setListAdapter(mAdapter);
    }

}

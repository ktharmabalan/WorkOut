package ca.codemake.workout.workout;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.ChooseExerciseListAdapter;
import ca.codemake.workout.models.Exercise;
import ca.codemake.workout.models.Item;

public class ChooseExerciseActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercises);

        ChooseExerciseListAdapter mAdapter = new ChooseExerciseListAdapter(this);

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Exercise("Squats"));
        items.add(new Exercise("Bench Press"));
        items.add(new Exercise("Shoulder Press"));
        items.add(new Exercise("Leg Press"));
        items.add(new Exercise("Curls"));
        items.add(new Exercise("Rows"));
        items.add(new Exercise("Calve Raises"));

        mAdapter.setItems(items);
        setListAdapter(mAdapter);
    }
}

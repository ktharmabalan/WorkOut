package ca.codemake.workout.nutrition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.Item;

public class MealFragment extends Fragment {
    private static final String TAG = "MealFragment";

    private WorkoutDbHelper db;
    private ArrayList<Item> items;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meal_fragment, container, false);

        return rootView;
    }
}

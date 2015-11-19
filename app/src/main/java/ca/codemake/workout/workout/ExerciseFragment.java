package ca.codemake.workout.workout;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.ExerciseAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.Exercise;
import ca.codemake.workout.models.Item;

public class ExerciseFragment extends Fragment {
    private static final String TAG = "ExerciseFragment";

    private TextView date;
    private SimpleDateFormat simpleDateFormat;
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView empty;

    private WorkoutDbHelper db;
    private ArrayList<Item> items;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categories, container, false);

        getData();
        initViews(rootView);
        return rootView;
    }

    private void getData() {
        items = new ArrayList<>();

        db = WorkoutDbHelper.getInstance(getActivity().getApplicationContext());

        Cursor cursor = db.getExercises();

        if (cursor.moveToFirst()) {
            /* Load data into the items list */
            do {
                initExercise(cursor);
            } while (cursor.moveToNext());
        }
    }

    private void initExercise(Cursor cursor) {
        items.add(new Exercise(cursor.getString(cursor.getColumnIndex("exercise_name"))));
//        Log.d("Tag", cursor.getString(cursor.getColumnIndex("routine_name")));
    }

    private void initViews(View rootView) {
        /* Initialize the date */
        simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date = (TextView) rootView.findViewById(R.id.date);
        date.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));

        /* Initialize recycler view */
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        /* Initialize recycler view adapter */
        exerciseAdapter = new ExerciseAdapter(getContext(), items);

        recyclerView.setAdapter(exerciseAdapter);

//        if (routineAdapter.getItemCount() == 1) {
//            Snackbar.make(getView(), "Add items by selecting the '+' button", Snackbar.LENGTH_LONG).show();
//        }

        /* Initialize floating action button */
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fabClicked();
            }
        });

        if (items.size() == 0) {
            date.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            empty = (TextView) rootView.findViewById(R.id.emptyView);
            empty.setText("Press the '+' icon to add a \nnew Exercise");
            empty.setVisibility(View.VISIBLE);
        }
    }

    /* Floating action button click event */
    public void fabClicked() {
    }
}

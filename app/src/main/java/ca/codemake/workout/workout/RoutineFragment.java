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
import ca.codemake.workout.adapters.RoutineAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Routine;

public class RoutineFragment extends Fragment {
    private static final String TAG = "RoutineFragment";

    private TextView date;
    private SimpleDateFormat simpleDateFormat;
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    private RoutineAdapter routineAdapter;
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

        Cursor cursor = db.getRoutines();

        if (cursor.moveToFirst()) {
            /* Load data into the items list */
            do {
                initRoutine(cursor);
            } while (cursor.moveToNext());
        }
    }

    private void initRoutine(Cursor cursor) {
        items.add(new Routine(cursor.getString(cursor.getColumnIndex("routine_name")), cursor.getInt(cursor.getColumnIndex("active")) == 1));
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
        routineAdapter = new RoutineAdapter(getContext(), items);
//        workoutInputAdapter.setClickListener(new SelectableAdapter.ClickListener() {
//            public void onClick(int position) {
//                if (actionMode == null) {
//                    Intent intent = new Intent(getActivity(), WorkoutRecordActivity.class);
//                    ExerciseEntry exercise = (ExerciseEntry) items.get(position);
//                    intent.putExtra("exercise_name", exercise.getName());
//                    intent.putExtra("sets", (int) exercise.getSets());
//                    startActivityForResult(intent, REQUEST_CODE);
//                } else {
//                    workoutInputAdapter.toggleSelection(position);
//
//                    if (workoutInputAdapter.getSelectedItems().size() > 0) {
//                        actionMode.setTitle(workoutInputAdapter.getSelectedItems().size() + " item(s) Selected");
//                    } else {
//                        actionMode.finish();
//                    }
//                }
//            }
//
//            public void onLongClick(int position) {
//                if (actionMode == null) {
//                    // Start the contextual action mode using the ActionMode.Callback
//                    actionMode = getActivity().startActionMode(actionModeCallback);
//                }
//
//                workoutInputAdapter.toggleSelection(position);
//
//                if (workoutInputAdapter.getSelectedItems().size() > 0) {
//                    actionMode.setTitle(workoutInputAdapter.getSelectedItems().size() + " item(s) Selected");
//                } else {
//                    actionMode.finish();
//                }
//            }
//        });

        recyclerView.setAdapter(routineAdapter);

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
            empty.setText("Press the '+' icon to add a \nnew Routine");
            empty.setVisibility(View.VISIBLE);
        }
    }

    /* Floating action button click event */
    public void fabClicked() {
//        if (items.size() == 0) {
//            Intent intent = new Intent(getActivity(), AddNutritionEntryActivity.class);
//            startActivityForResult(intent, REQUEST_CODE);
//        } else {
//            workoutInputAdapter.addRow(new ExerciseEntry(1, "Sample " + (items.size() - 1), 20));
//        }
    }
}

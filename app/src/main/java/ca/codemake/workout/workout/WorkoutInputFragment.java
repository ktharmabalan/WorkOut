package ca.codemake.workout.workout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.SelectableAdapter;
import ca.codemake.workout.adapters.WorkoutInputAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;
import ca.codemake.workout.nutrition.AddNutritionEntryActivity;

public class WorkoutInputFragment extends Fragment {

    private final static String TAG = "WorkoutInput";

    private WorkoutDbHelper db;
    private ArrayList<Item> items;

    private TextView date;
    private SimpleDateFormat simpleDateFormat;
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    private WorkoutInputAdapter workoutInputAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView empty;

    private final int RESULT_CODE = 1;
    private final int REQUEST_CODE = 1;

    public static ActionMode actionMode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_workout_input, container, false);

        getData();
        initViews(rootView);

        return rootView;
    }

    /* Get exercise entry data for the workout */
    private void getData() {
        items = new ArrayList<>();

        db = WorkoutDbHelper.getInstance(getActivity().getApplicationContext());

        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);

        if (cursor.moveToFirst()) {
            /* Load data into the items list */
            do {
                if (cursor.isFirst()) {
                    initWorkout(cursor);
                }
                initExerciseEntry(cursor);
            } while (cursor.moveToNext());
        }
    }

    private void initViews(View rootView) {
        /* Initialize the date */
        simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date = (TextView) rootView.findViewById(R.id.workout_date);
        date.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));

        /* Initialize recycler view */
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        /* Initialize recycler view adapter */
        workoutInputAdapter = new WorkoutInputAdapter(getContext(), items);
        workoutInputAdapter.setClickListener(new SelectableAdapter.ClickListener() {
            public void onClick(int position) {
                if (actionMode == null) {
                    Intent intent = new Intent(getActivity(), WorkoutRecordActivity.class);
                    ExerciseEntry exercise = (ExerciseEntry) items.get(position);
                    intent.putExtra("exercise_name", exercise.getName());
                    intent.putExtra("sets", (int) exercise.getSets());
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    workoutInputAdapter.toggleSelection(position);

                    if (workoutInputAdapter.getSelectedItems().size() > 0) {
                        actionMode.setTitle(workoutInputAdapter.getSelectedItems().size() + " item(s) Selected");
                    } else {
                        actionMode.finish();
                    }
                }
            }

            public void onLongClick(int position) {
                if (actionMode == null) {
                    // Start the contextual action mode using the ActionMode.Callback
                    actionMode = getActivity().startActionMode(actionModeCallback);
                }

                workoutInputAdapter.toggleSelection(position);

                if (workoutInputAdapter.getSelectedItems().size() > 0) {
                    actionMode.setTitle(workoutInputAdapter.getSelectedItems().size() + " item(s) Selected");
                } else {
                    actionMode.finish();
                }
            }
        });

        recyclerView.setAdapter(workoutInputAdapter);

        if (workoutInputAdapter.getItemCount() == 1) {
            Snackbar.make(getView(), "Add items by selecting the '+' button", Snackbar.LENGTH_LONG).show();
        }

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
            empty.setVisibility(View.VISIBLE);
        }
    }

    /* Activity result */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_CODE && requestCode == REQUEST_CODE) {
            if (intent.hasExtra("selected_fragment")) {
                Toast.makeText(getContext(), intent.getExtras().getString("selected_fragment"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* Inflate options menu */
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_workout_input, menu);
    }

    /* Option item click event */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Add new workout to the items list using cursor data */
    private void initWorkout(Cursor cursor) {
        items.add(new Workout(cursor.getString(cursor.getColumnIndex("workout_name"))));
    }

    /* Add new exercise entry to the items list using cursor data*/
    private void initExerciseEntry(Cursor cursor) {
        items.add(new ExerciseEntry(
                cursor.getLong(cursor.getColumnIndex("exercise_entry_id")),
                cursor.getString(cursor.getColumnIndex("exercise_name")),
                cursor.getLong(cursor.getColumnIndex("sets"))));
    }

    /* Floating action button click event */
    public void fabClicked() {
        if (items.size() == 0) {
            Intent intent = new Intent(getActivity(), AddNutritionEntryActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            workoutInputAdapter.addRow(new ExerciseEntry(1, "Sample " + (items.size() - 1), 20));
        }
    }

    /* Action mode actions */
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            // Inflate a menu resource providing context menu items
            actionMode.getMenuInflater().inflate(R.menu.menu_workout_input_context, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            // Update when invalidate()
            return false;   // return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            // Handle actions
            switch (menuItem.getItemId()) {
                case R.id.remove:
                    workoutInputAdapter.removeSelected();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exists the action mode
        public void onDestroyActionMode(ActionMode am) {
            // Update when removing contextual action bar, deselect items
            workoutInputAdapter.clearSelection();
            workoutInputAdapter.reset();
            actionMode = null;

            if (workoutInputAdapter.getItemCount() == 1) {
                Snackbar.make(getView(), "Add exercises by selecting the '+' button", Snackbar.LENGTH_LONG).show();
            }
        }
    };
}
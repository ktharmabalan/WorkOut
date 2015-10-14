package ca.codemake.workout.workout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.RoutineCreateAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class WorkoutInputActivity extends Fragment {

    private WorkoutDbHelper db;
    private RoutineCreateAdapter adapter;
    private final static String TAG = "WorkoutInputActivity";
    private ArrayList<Item> items;

    private ExerciseEntry exerciseEntry;
    private Workout workout;
    private ListView listView;

    private Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setContentView(R.layout.activity_workout_input);

        View rootView = inflater.inflate(R.layout.activity_workout_input, container, false);

        /* Set TextView for date with current date */
        TextView date = (TextView) rootView.findViewById(R.id.workout_date);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date.setText(simpleDateFormat.format(calendar.getTime()));

        listView = (ListView) rootView.findViewById(R.id.list_workout_tracker);

        db = new WorkoutDbHelper(getActivity());
        items = new ArrayList<>();

        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.isFirst()) {
                    initWorkout(cursor);
                }
                initExerciseEntry(cursor);
            } while (cursor.moveToNext());
        }

        adapter = new RoutineCreateAdapter(getActivity());
        adapter.setItems(items);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getApplicationContext(), WorkoutRecordActivity.class);
                Intent i = new Intent(getActivity(), WorkoutRecordActivity.class);
                startActivity(i);

            }
        };
/*
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(), "Long: " + i, Toast.LENGTH_SHORT).show();
                    return true;
            }
        });
*/
        listView.setOnItemClickListener(onItemClickListener);
//        listView.setOnItemClickListener(adapter);
        listView.setItemsCanFocus(true);

        // Contextual Actions
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int selected = 0;
            private HashMap<Integer, Integer> itemsSelected = new HashMap<>();

            private ArrayList<Integer> its = new ArrayList<Integer>();

            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean b) {

                // Perform action on item selected
                if (b) {
                    selected++;
                    itemsSelected.put(position, position);
                } else {
                    selected--;
                    itemsSelected.remove(position);
                }

                if (listView.getCheckedItemCount() > 0)
                    actionMode.setTitle(listView.getCheckedItemCount() + " Selected");
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // Inflate menu
                actionMode.getMenuInflater().inflate(R.menu.menu_workout_input_context, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // Update when invalidate()
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // Handle actions
                switch (menuItem.getItemId()) {
                    case R.id.remove:

                        for (int i = 0; i < items.size(); i++) {
                            if (itemsSelected.containsKey(i)) {
                                adapter.remove(i);
                            }
                        }

                        if (listView.getCheckedItemCount() > 0)
                            actionMode.setTitle(listView.getCheckedItemCount() + " Removed");
                        else
                            actionMode.setTitle("Removed");

//                        Toast.makeText(getApplicationContext(),"Remove", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }

            public void onDestroyActionMode(ActionMode actionMode) {
                // Update when removing contextual action bar, deselect items
                selected = 0;
                itemsSelected.clear();
            }
        });

        return rootView;
    }

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_workout_input);
//
//        /* Set TextView for date with current date */
//        TextView date = (TextView) findViewById(R.id.workout_date);
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
//        date.setText(simpleDateFormat.format(calendar.getTime()));
//
//        listView = (ListView) findViewById(R.id.list_workout_tracker);
//
//        db = new WorkoutDbHelper(getApplicationContext());
//        items = new ArrayList<>();
//
//        Cursor cursor = db.getExerciseEntriesByWorkoutId(1);
//
//        if (cursor.moveToFirst()) {
//            do {
//                if (cursor.isFirst()) {
//                    initWorkout(cursor);
//                }
//                initExerciseEntry(cursor);
//            } while (cursor.moveToNext());
//        }
//
//        adapter = new RoutineCreateAdapter(this);
//        adapter.setItems(items);
//        listView.setAdapter(adapter);
//
//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
////                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
////                Intent i = new Intent(getApplicationContext(), WorkoutRecordActivity.class);
//                Intent i = new Intent(getApplicationContext(), WorkoutRecordActivity.class);
//                startActivity(i);
//
//            }
//        };
///*
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Toast.makeText(getApplicationContext(), "Long: " + i, Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//        });
//*/
//        listView.setOnItemClickListener(onItemClickListener);
////        listView.setOnItemClickListener(adapter);
//        listView.setItemsCanFocus(true);
//
//        // Contextual Actions
//        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//
//            private int selected = 0;
//            private HashMap<Integer, Integer> itemsSelected = new HashMap<>();
//
//            private ArrayList<Integer> its = new ArrayList<Integer>();
//
//            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean b) {
//
//                // Perform action on item selected
//                if (b) {
//                    selected++;
//                    itemsSelected.put(position, position);
//                } else {
//                    selected--;
//                    itemsSelected.remove(position);
//                }
//
//                if (listView.getCheckedItemCount() > 0)
//                    actionMode.setTitle(listView.getCheckedItemCount() + " Selected");
//            }
//
//            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                // Inflate menu
//                actionMode.getMenuInflater().inflate(R.menu.menu_workout_input_context, menu);
//                return true;
//            }
//
//            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                // Update when invalidate()
//                return false;
//            }
//
//            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//                // Handle actions
//                switch (menuItem.getItemId()) {
//                    case R.id.remove:
//
//                        for (int i = 0; i < items.size(); i++) {
//                            if (itemsSelected.containsKey(i)) {
//                                adapter.remove(i);
//                            }
//                        }
//
//                        if (listView.getCheckedItemCount() > 0)
//                            actionMode.setTitle(listView.getCheckedItemCount() + " Removed");
//                        else
//                            actionMode.setTitle("Removed");
//
////                        Toast.makeText(getApplicationContext(),"Remove", Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//
//            public void onDestroyActionMode(ActionMode actionMode) {
//                // Update when removing contextual action bar, deselect items
//                selected = 0;
//                itemsSelected.clear();
//            }
//        });
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_workout_input, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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

    public void onPause() {
        super.onPause();
        if (db != null) {
            Log.d(TAG, "onPause");
            db.close();
        }
    }

    public void onResume() {
        super.onResume();
        if (db != null) {
            Log.d(TAG, "onResume");
            db.open();
        }
    }
}

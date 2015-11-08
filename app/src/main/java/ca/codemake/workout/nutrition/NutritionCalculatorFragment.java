package ca.codemake.workout.nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ca.codemake.workout.DatePickerFragment;
import ca.codemake.workout.R;
import ca.codemake.workout.adapters.NutritionCalculatorAdapter;
import ca.codemake.workout.adapters.SelectableAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionCalculatorFragment extends Fragment {

    private static final String TAG = "NutritionCalculator";

    private WorkoutDbHelper db;
    private ArrayList<Item> items;

    private String mealName = null;

    private SimpleDateFormat simpleDateFormat;
    private TextView date = null;
    private ProgressBar progressBar = null;
    private TextView progressBarText = null;
    private FloatingActionButton fab;
    private TextView empty;

    private RecyclerView recyclerView;
    private NutritionCalculatorAdapter nutritionCalculatorAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int totalCalories = 0;

    private final int RESULT_CODE = 1;
    private final int REQUEST_CODE = 1;

    private static ActionMode actionMode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_nutrition_calculator, container, false);

        getData();
        initViews(rootView);

        return rootView;
    }

    private void getData() {
        items = new ArrayList<>();
        db = WorkoutDbHelper.getInstance(getActivity().getApplicationContext());

        Cursor cursor = db.getAllMealEntries();

        /* Populate the array list used for recycler view with meals and meal entries */
        if (cursor.moveToFirst()) {
            do {
                initMeal(cursor);
                initMealEntry(cursor);
            } while (cursor.moveToNext());
        }
    }

    public void initViews(View rootView) {
        /* Initialize date */
        simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date = (TextView) rootView.findViewById(R.id.nutritionDate);
        date.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));

        /* Initialize progress bar with total calories for the day */
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setProgress((totalCalories * 100) / (1000 + totalCalories));

        progressBarText = (TextView) rootView.findViewById(R.id.progressBarText);
        if(totalCalories == 0)
            progressBarText.setText((totalCalories + 1000) + " calories left out of " + (totalCalories + 1000));
        else
            progressBarText.setText(totalCalories + " calories left out of " + (totalCalories + 1000));

        /* Initialize recycler view */
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.nutrition_list);
        recyclerView.setLayoutManager(layoutManager);

        /* Initialize recycler view adapter */
        nutritionCalculatorAdapter = new NutritionCalculatorAdapter(getContext(), items);
        nutritionCalculatorAdapter.setNutritionClickListener(new NutritionCalculatorAdapter.NutritionClickListener() {
            public void onClick(int position, boolean isDivider) {
                if (actionMode == null) {
                    Intent intent = new Intent(getActivity(), AddNutritionEntryActivity.class);

                    MealEntry mealEntry = null;
                    if(!items.get(position).isDivider()) {
                        Log.d("Tag", "is Not divider");
                        mealEntry = (MealEntry) items.get(position);
                        Log.d("Tag", "" + mealEntry.meal_entry_id);
                        intent.putExtra("edit", "edit");
                        intent.putExtra("ENTRY_ID", mealEntry.meal_entry_id);
                    }
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    nutritionCalculatorAdapter.toggleSelection(position);
                    if (nutritionCalculatorAdapter.getSelectedItems().size() > 0) {
                        actionMode.setTitle(nutritionCalculatorAdapter.getSelectedItems().size() + " item(s) Selected");
                    } else {
                        actionMode.finish();
                    }
                }
            }

            public void onLongClick(int position, boolean isDivider) {
                if (actionMode == null) {
                    // Start the contextual action mode using the ActionMode.Callback
                    actionMode = getActivity().startActionMode(actionModeCallback);
                }

                nutritionCalculatorAdapter.toggleSelection(position);

                if (nutritionCalculatorAdapter.getSelectedItems().size() > 0) {
                    actionMode.setTitle(nutritionCalculatorAdapter.getSelectedItems().size() + " item(s) Selected");
                } else {
                    actionMode.finish();
                }
            }
        });

        nutritionCalculatorAdapter.setButtonListener(new SelectableAdapter.ButtonListener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), AddNutritionEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        recyclerView.setAdapter(nutritionCalculatorAdapter);

        /* Initialize floating action button */
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fabClicked();
            }
        });

        if(items.size() == 0) {
            progressBar.setVisibility(View.GONE);
            progressBarText.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            empty = (TextView) rootView.findViewById(R.id.emptyView);
            empty.setVisibility(View.VISIBLE);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.menu_nutrition_calculator_activity_actions, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_new_meal:
                MealPickerFragment mealPickerFragment = new MealPickerFragment();
                mealPickerFragment.setButtonListener(new MealPickerFragment.ButtonListener() {
                    public void positiveButtonClick(String mealName) {
//                        nutritionListAdapter.addToItems(new Meal(mealName));
                        nutritionCalculatorAdapter.addToItems(new Meal(mealName));
                    }
                });
                mealPickerFragment.show(getFragmentManager(), "mealPicker");

//                Intent intent = new Intent(getActivity(), AddMealActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case R.id.action_calendar:
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setCalendarDateSelector(new DatePickerFragment.CalendarDateSelector() {
                    public void onSelectedDate(String date) {
                        TextView dateText = (TextView) getActivity().findViewById(R.id.nutritionDate);
                        dateText.setText(date);
                    }
                });
                newFragment.show(getFragmentManager(), "datePicker");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_CODE && requestCode == REQUEST_CODE) {
            if (intent.hasExtra("selected_fragment")) {
                Toast.makeText(getContext(), intent.getExtras().getString("selected_fragment"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* Add new meal to the items list using cursor data */
    private void initMeal(Cursor cursor) {
        if (mealName == null) {
            mealName = cursor.getString(cursor.getColumnIndex("meal_name"));
            totalCalories = cursor.getInt(cursor.getColumnIndex("all_calories"));

            items.add(new Meal(cursor.getString(cursor.getColumnIndex("meal_name")),
                    cursor.getInt(cursor.getColumnIndex("total_calories"))));
        }
        if (!cursor.getString(cursor.getColumnIndex("meal_name")).equals(mealName)) {
            mealName = cursor.getString(cursor.getColumnIndex("meal_name"));

            items.add(new Meal(cursor.getString(cursor.getColumnIndex("meal_name")),
                    cursor.getInt(cursor.getColumnIndex("total_calories"))));
        }
    }

    /* Add new meal entry to the items list using cursor data */
    private void initMealEntry(Cursor cursor) {
        Log.d("Tag", "" + cursor.getLong(cursor.getColumnIndex("meal_entry_id")));
//        items.add(new MealEntry(cursor.getString(cursor.getColumnIndex("food_name")), cursor.getInt(cursor.getColumnIndex("calories"))));
        items.add(new MealEntry(cursor.getString(cursor.getColumnIndex("food_name")), cursor.getInt(cursor.getColumnIndex("calories")), cursor.getLong(cursor.getColumnIndex("meal_entry_id"))));
    }

    public void fabClicked() {
        Intent intent = new Intent(getActivity(), AddNutritionEntryActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

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
                    nutritionCalculatorAdapter.removeSelected();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exists the action mode
        public void onDestroyActionMode(ActionMode am) {
            // Update when removing contextual action bar, deselect items
            nutritionCalculatorAdapter.clearSelection();
            nutritionCalculatorAdapter.reset();
            actionMode = null;

            if (nutritionCalculatorAdapter.getItemCount() == 1) {
                Snackbar.make(getView(), "Add exercises by selecting the '+' button", Snackbar.LENGTH_LONG).show();
            }
        }
    };
}
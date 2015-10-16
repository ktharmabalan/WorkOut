package ca.codemake.workout.nutrition;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ca.codemake.workout.R;
import ca.codemake.workout.adapters.NutritionAdapter;
import ca.codemake.workout.database.WorkoutDbHelper;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionCalculatorFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE = 1;
    private NutritionAdapter nutritionAdapter;
    private WorkoutDbHelper db;
    private static final String TAG = "NutritionCalculator";

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_nutrition_calculator, container, false);

        listView = (ListView) rootView.findViewById(R.id.nutrition_list);

//        db = new WorkoutDbHelper(getActivity());
        db = WorkoutDbHelper.getInstance(getActivity().getApplicationContext());

        setUpButtons(rootView);
        loadData(rootView);

        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.menu_nutrition_calculator_activity_actions, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_new_meal:
                Intent i = new Intent(getActivity(), AddMealActivity.class);
                startActivity(i);
                return true;
            case R.id.action_calendar:
                /* Create a dialog with a calendar view */
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                View view = inflater.inflate(R.layout.calendar, null);

                CalendarView calendar = (CalendarView) view.findViewById(R.id.calendar);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going to the dialog layout
                builder.setView(view);

                builder.setTitle("Title");
//                builder.setMessage("Hello World!");

                AlertDialog dialog = builder.create();
                dialog.show();

                /* Dialog configurations */
                /*
                // Set title divider color
                int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
                View titleDivider = dialog.findViewById(titleDividerId);
                if (titleDivider != null)
                    titleDivider.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                 */

                /* Calendar configurations */
                /*
                // sets whether to show the week number.
                calendar.setShowWeekNumber(false);
                // sets the first day of week according to Calendar.
                // here we set Monday as the first day of the Calendar
                calendar.setFirstDayOfWeek(2);
                //The background color for the selected week.
                calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green_blue_light));
                //sets the color for the dates of an unfocused month.
                calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.green_blue));
                //sets the color for the separator line between weeks.
                calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.light_orange));
                //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
                calendar.setSelectedDateVerticalBar(R.color.dark_orange);
                */

                /* Calendar date changed */
                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
                        Toast.makeText(getActivity(), simpleDateFormat.format(new Date(calendarView.getDate())), Toast.LENGTH_SHORT).show();
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData(View rootView) {
        nutritionAdapter = new NutritionAdapter(getActivity());

        Cursor cursor = db.getAllMealEntries();

        /* Insert nutrition data into database if there are no meal entries */
/*        if(cursor.getCount() == 0) {
            db.newMeal("Breakfast", 0, 1);
            db.newMeal("Brunch", 0, 1);
            db.newMeal("Lunch", 0, 1);
            db.newMeal("Dinner", 0, 1);
            db.newMeal("Snack", 0, 1);

            db.newFood("Eggs", 100, 0);
            db.newFood("Bacon", 150, 0);
            db.newFood("Sausage", 130, 0);
            db.newFood("Toast", 50, 0);
            db.newFood("Ham", 75, 0);
            db.newFood("Pancake", 250, 0);

            db.newMealEntry(1, 1);
            db.newMealEntry(1, 2);
            db.newMealEntry(2, 3);
            db.newMealEntry(3, 4);
            db.newMealEntry(4, 5);
            db.newMealEntry(5, 1);
            db.newMealEntry(4, 1);
            db.newMealEntry(1, 6);

            cursor = db.getAllMealEntries();
        }*/

        /* Populate the array list used for list view with meals and meal entries */
        ArrayList<Item> items = new ArrayList<>();
        int totalCalories = 0;

        if (cursor.moveToFirst()) {
            Meal meal;
            String mealName = null;

            do {
                if (mealName == null) {
                    mealName = cursor.getString(cursor.getColumnIndex("meal_name"));
                    meal = new Meal(mealName);
                    meal.setCalories(cursor.getInt(cursor.getColumnIndex("total_calories")));
                    items.add(meal);
                    totalCalories = cursor.getInt(cursor.getColumnIndex("all_calories"));
                }

                if (!cursor.getString(cursor.getColumnIndex("meal_name")).equals(mealName)) {
                    mealName = cursor.getString(cursor.getColumnIndex("meal_name"));

                    meal = new Meal(mealName);
                    meal.setCalories(cursor.getInt(cursor.getColumnIndex("total_calories")));
                    items.add(meal);
                }

                items.add(new MealEntry(cursor.getString(cursor.getColumnIndex("food_name")), cursor.getInt(cursor.getColumnIndex("calories"))));

            } while (cursor.moveToNext());
        }

        nutritionAdapter.setItems(items);
//        setListAdapter(nutritionAdapter);
        listView.setAdapter(nutritionAdapter);

        /* Set TextView for date with current date */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");

        TextView date = null;
        ProgressBar progressBar = null;
        TextView progressBarText = null;

        date = (TextView) rootView.findViewById(R.id.textView14);

        /* Update the progress bar with total calories for the day */
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setProgress(75);
        progressBarText = (TextView) rootView.findViewById(R.id.progressBarText);


        progressBarText.setText(String.valueOf(totalCalories));
        date.setText(simpleDateFormat.format(calendar.getTime()));

    }

    public void setUpButtons(View rootView) {
        Button b = (Button) rootView.findViewById(R.id.btn_nutrition_day);
        b.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button b = (Button) v;

        if (b.getId() == R.id.btn_nutrition_day) {
            Intent i = new Intent(getActivity(), AddNutritionEntryActivity.class);
            startActivity(i);
//            startActivityForResult(i, REQUEST_CODE);
        }
    }

    public void onPause() {
        super.onPause();
//        if (db != null) {
//            Log.d(TAG, "onPause");
//            db.close("NutritionCalculatorFragment");
//        }
    }

    public void onResume() {
        super.onResume();
        db = WorkoutDbHelper.getInstance(getActivity().getApplicationContext());
//        if (db != null) {
//            Log.d(TAG, "onResume");
//            db.open("NutritionCalculatorFragment");
//        }
    }
}
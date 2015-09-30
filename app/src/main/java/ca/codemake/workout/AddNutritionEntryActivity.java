package ca.codemake.workout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ca.codemake.workout.database.WorkoutDbAdapter;

/**
 * Created by Kajan on 8/19/2015.
 */
public class AddNutritionEntryActivity extends Activity {

    private HashMap<String, String> autoText;
    private ArrayList autoName;
//    private Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrition_entry);

//        toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        autoName = new ArrayList();
        autoText = new HashMap<>();

        TextView date = (TextView) findViewById(R.id.txt_nutrition_day);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date.setText(simpleDateFormat.format(calendar.getTime()));

        WorkoutDbAdapter db = new WorkoutDbAdapter(getApplicationContext());
        db.open();
        Cursor cursor = db.getAllFoods();
        if(cursor.moveToFirst()) {
            do {
                autoName.add(cursor.getString(0));
                autoText.put(cursor.getString(0), String.valueOf(cursor.getLong(1)));
            } while (cursor.moveToNext());
        }
        db.close();

        if(autoName.size() > 0) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.foodName);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autoName);
            autoCompleteTextView.setAdapter(adapter);
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_nutrition_entry_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
//                showHelp();
                Intent i = new Intent(getApplicationContext(), AddFoodActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

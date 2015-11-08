package ca.codemake.workout.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ca.codemake.workout.BaseActivity;
import ca.codemake.workout.R;

public class AddNutritionEntryActivity extends BaseActivity {

    private HashMap<String, String> autoText;
    private ArrayList foodNameList;
    private ArrayList mealNameList;

    private final int RESULT_CODE = 1;
    public boolean clicked = false;
    public boolean edit = false;

    private Switch calories_categories;
    private RadioButton calories_consumed;
    private RadioButton calories_expended;
    private RadioGroup radioGroup;

    private LinearLayout calories_consumed_container;
    private LinearLayout calories_expended_container;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateContentFrame(R.layout.activity_add_nutrition_entry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if((intent.hasExtra("edit")) && (intent.getStringExtra("edit").equals("edit"))) {
            Log.d("Tag", "Has extra " + intent.getLongExtra("ENTRY_ID", -1));
//            Toast.makeText(getApplicationContext(), "" + intent.getIntExtra("ENTRY_ID", -1), Toast.LENGTH_SHORT).show();
        }

        calories_consumed_container = (LinearLayout) findViewById(R.id.calories_consumed_container);

        calories_expended_container = (LinearLayout) findViewById(R.id.calories_expended_container);

        radioGroup = (RadioGroup) findViewById(R.id.calories_category);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.calories_consumed_radio) {
                    calories_expended_container.setVisibility(View.GONE);
                    calories_consumed_container.setVisibility(View.VISIBLE);
                } else if(checkedId == R.id.calories_expended_radio) {
                    calories_consumed_container.setVisibility(View.GONE);
                    calories_expended_container.setVisibility(View.VISIBLE);
                }
            }
        });

/*        calories_consumed = (RadioButton) findViewById(R.id.calories_consumed_radio);
        calories_consumed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calories_consumed_container.setVisibility(View.GONE);
                calories_expended_container.setVisibility(View.VISIBLE);
            }
        });
        calories_expended = (RadioButton) findViewById(R.id.calories_expended_radio);
        calories_expended.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calories_consumed_container.setVisibility(View.GONE);
                calories_expended_container.setVisibility(View.VISIBLE);
            }
        });*/

//        calories_categories = (Switch) findViewById(R.id.calories_category);
//        calories_categories.setChecked(false);
//        calories_categories.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                calories_categories.setChecked(isChecked);
//                if (isChecked) {
//                    calories_categories.setText("Calories Expended");
//                    calories_consumed_container.setVisibility(View.GONE);
//                    calories_expended_container.setVisibility(View.VISIBLE);
//                } else {
//                    calories_categories.setText("Calories Consumed");
//                    calories_expended_container.setVisibility(View.GONE);
//                    calories_consumed_container.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        TextView date = (TextView) findViewById(R.id.txt_nutrition_date);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        date.setText(simpleDateFormat.format(calendar.getTime()));

        foodNameList = new ArrayList();
        mealNameList = new ArrayList();

        foodNameList.add("Bacon");
        foodNameList.add("Eggs");
        foodNameList.add("Sausage");
        foodNameList.add("Toast");
        foodNameList.add("Juice");
        foodNameList.add("Apple");

        mealNameList.add("Breakfast");
        mealNameList.add("Lunch");
        mealNameList.add("Dinner");
        mealNameList.add("Other");


        AutoCompleteTextView mealName = (AutoCompleteTextView) findViewById(R.id.meal_name);

        final AutoCompleteTextView foodName = (AutoCompleteTextView) findViewById(R.id.food_name);
        final TextInputLayout foodTextInputLayout = (TextInputLayout) findViewById(R.id.food_name_layout);

        if (mealNameList.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mealNameList);
            mealName.setAdapter(adapter);
        }

        if (foodNameList.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodNameList);
            foodName.setAdapter(adapter);
        }

/*        foodName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                EditText text = (EditText) v;
                if (!hasFocus && text.length() > 1) {
                    isNewItem(text.getText());
                }
            }
        });

        foodName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() >= foodName.getThreshold()) {
                    isNewItem(s);
                }
            }
        });*/
    }

    private void isNewItem(Editable text) {
        if (text.length() > 0) {
            String food = text.toString().substring(0, 1).toUpperCase();
            if (text.toString().length() > 1)
                food += text.toString().substring(1).toLowerCase();

            if (!foodNameList.contains(food)) {
                edit = true;
                Log.d("Tag", "Contains " + foodNameList.contains(food));
            } else {
                edit = false;
            }
            supportInvalidateOptionsMenu();
        }
    }

    public void finish() {
        // Prepare intent
        Intent intent = new Intent();
        intent.putExtra("selected_fragment", AddNutritionEntryActivity.this.getClass().getSimpleName());
        // Activity finished ok, return the data
        setResult(RESULT_CODE, intent);
        super.finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_nutrition_entry_activity_actions, menu);

        if (!edit) {
            MenuItem item = menu.findItem(R.id.action_new_food);
            item.setVisible(false);

            item = menu.findItem(R.id.action_edit);
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
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
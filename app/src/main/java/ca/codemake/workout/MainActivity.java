package ca.codemake.workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.codemake.workout.database.WorkoutDbHelper;


public class MainActivity extends Activity implements View.OnClickListener {

//    private Toolbar toolbar;
    private WorkoutDbHelper db;
    private final static String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new WorkoutDbHelper(getApplicationContext());

//        toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);

        setUpButtons();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_begin_workout);
        b.setOnClickListener(this);

        b = (Button) this.findViewById(R.id.btn_nutrition_calculator);
        b.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_begin_workout) {
//            Toast.makeText(this, "Begin Workout", Toast.LENGTH_SHORT).show();
            if(db.getConfiguration("CHOSEN_ROUTINE").getCount() == 0) {
                Intent i = new Intent(getApplicationContext(), CreateRoutineActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(getApplicationContext(), WorkoutInputActivity.class);
                startActivity(i);
            }
        } else if(b.getId() == R.id.btn_nutrition_calculator) {
//            Toast.makeText(this, "Nutrition Calculator", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), NutritionCalculatorActivity.class);
            startActivity(i);
        }
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        db.close();
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        db.open();
    }
}

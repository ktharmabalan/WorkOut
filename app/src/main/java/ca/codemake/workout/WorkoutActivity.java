package ca.codemake.workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WorkoutActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_regimen);
        setUpButtons();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
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
        Button b = (Button) this.findViewById(R.id.btn_workout_day);
        b.setOnClickListener(this);

        b = (Button) this.findViewById(R.id.btn_custom_workout);
        b.setOnClickListener(this);

        b = (Button) this.findViewById(R.id.btn_recommended_workout);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_workout_day) {
            Intent i = new Intent(getApplicationContext(), WorkoutMenuActivity.class);
            startActivity(i);
        } else if(b.getId() == R.id.btn_custom_workout) {
            Intent i = new Intent(getApplicationContext(), CustomWorkoutActivity.class);
            startActivity(i);
        } else if(b.getId() == R.id.btn_recommended_workout) {
            Intent i = new Intent(getApplicationContext(), RecommendedWorkoutActivity.class);
            startActivity(i);
        }
    }
}

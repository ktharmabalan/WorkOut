package ca.codemake.workout.workout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.codemake.workout.R;

/**
 * Created by Kajan on 8/19/2015.
 */
public class CustomWorkoutGroupActivity extends Activity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout_group);
        setUpButtons();
    }

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_custom_workout);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_custom_workout) {
//            Intent i = new Intent(getApplicationContext(), AddNutritionEntryActivity.class);
//            startActivity(i);
        }
    }
}

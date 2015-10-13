package ca.codemake.workout.workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.codemake.workout.R;

public class WorkoutMenuActivity extends Activity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_menu);
        setUpButtons();
    }

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_begin_workout);
        b.setOnClickListener(this);

        b = (Button) this.findViewById(R.id.btn_workout_history);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_begin_workout) {
            Intent i = new Intent(getApplicationContext(), WorkoutInputActivity.class);
            startActivity(i);
        } else if(b.getId() == R.id.btn_workout_history) {
            Intent i = new Intent(getApplicationContext(), WorkoutHistoryActivity.class);
            startActivity(i);
        }
    }
}

package ca.codemake.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class WorkoutMenuActivity extends ActionBarActivity implements View.OnClickListener {

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

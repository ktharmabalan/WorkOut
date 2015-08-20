package ca.codemake.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kajan on 8/19/2015.
 */
public class RecommendedWorkoutDetailsActivity extends ActionBarActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations_detaisl);
        setUpButtons();
    }

    public void setUpButtons() {
        Button b = (Button) this.findViewById(R.id.btn_recommendation_start);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if(b.getId() == R.id.btn_recommendation_start) {
//            Intent i = new Intent(getApplicationContext(), NutritionDayActivity.class);
//            startActivity(i);
        }
    }
}

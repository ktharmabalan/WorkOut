package ca.codemake.workout.workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.codemake.workout.R;

public class RecommendedWorkoutActivity extends Activity implements ListView.OnItemClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_workout);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(), RecommendedWorkoutDetailsActivity.class);
        startActivity(i);
//        Toast.makeText(this, "List item clicked", Toast.LENGTH_SHORT).show();
    }
}

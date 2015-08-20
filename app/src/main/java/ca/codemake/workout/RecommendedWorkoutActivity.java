package ca.codemake.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Kajan on 8/19/2015.
 */
public class RecommendedWorkoutActivity extends ActionBarActivity implements ListView.OnItemClickListener {

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

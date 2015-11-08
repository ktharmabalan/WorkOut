package ca.codemake.workout.nutrition;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;

import ca.codemake.workout.BaseActivity;
import ca.codemake.workout.R;

public class AddFoodActivity extends BaseActivity {

    private Switch nutrition_switch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateContentFrame(R.layout.activity_add_food);

        nutrition_switch = (Switch) findViewById(R.id.switch1);
        nutrition_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            TableLayout nutrition_facts_table = (TableLayout) findViewById(R.id.nutrition_facts_table);

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nutrition_facts_table.setVisibility(View.VISIBLE);
                } else {
                    nutrition_facts_table.setVisibility(View.GONE);
                }
            }
        });
    }
}

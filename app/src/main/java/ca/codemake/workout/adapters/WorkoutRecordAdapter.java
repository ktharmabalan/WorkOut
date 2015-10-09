package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.codemake.workout.R;
import ca.codemake.workout.models.ExerciseEntry;

public class WorkoutRecordAdapter extends SimpleAdapter {

    public WorkoutRecordAdapter(Context context) {
        super(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.workout_record_item, null);

        ExerciseEntry e = (ExerciseEntry) items.get(position);
        TextView textView = null;
        textView = (TextView) convertView.findViewById(R.id.exercise_name);
        textView.setText(e.getName());
        textView = (TextView) convertView.findViewById(R.id.weight);
        textView.setText(String.valueOf(e.getWeight()) + "lbs");
        textView = (TextView) convertView.findViewById(R.id.sets);
        textView.setText(String.valueOf(e.getSets()) + " x");
        textView = (TextView) convertView.findViewById(R.id.reps);
        textView.setText(String.valueOf(e.getReps()));

        return convertView;
    }


}

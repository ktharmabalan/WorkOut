package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Exercise;

public class ChooseExerciseAdapter extends SimpleAdapter {

    public ChooseExerciseAdapter(Context context) {
        super(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.exercise_row_item, null);
        }

        textView = (TextView) convertView.findViewById(R.id.name);
        Exercise exercise = (Exercise) items.get(position);
        textView.setText(exercise.getName());

        return convertView;
    }
}

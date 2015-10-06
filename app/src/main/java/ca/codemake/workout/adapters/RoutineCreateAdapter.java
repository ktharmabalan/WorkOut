package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ca.codemake.workout.R;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Workout;

public class RoutineCreateAdapter extends SimpleAdapter {

    public RoutineCreateAdapter(Context context) {
        super(context);
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        TextView textView = null;

        if (items.get(position).isDivider()) {
            convertView = inflater.inflate(R.layout.wokout_row_divider, null);
            textView = (TextView) convertView.findViewById(R.id.workout_name);

            Workout workout = (Workout) items.get(position);
            textView.setText(workout.getName());

            Button addExercise = (Button) convertView.findViewById(R.id.addExerciseToWeekDay);
            addExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(parent.getContext(), "Add Exercise", Toast.LENGTH_SHORT).show();
                }
            });

            Button editWeekday = (Button) convertView.findViewById(R.id.editWeekday);
            editWeekday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(parent.getContext(), "Edit Weekday", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            convertView = inflater.inflate(R.layout.workout_row_item, null);
            textView = (TextView) convertView.findViewById(R.id.exercise_name);

            ExerciseEntry exercise = (ExerciseEntry) items.get(position);
            textView.setText(exercise.getName());

            textView = (TextView) convertView.findViewById(R.id.sets);
            textView.setText(String.valueOf(exercise.getSets()) + " sets");
        }

        return convertView;
    }
}

package ca.codemake.workout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ca.codemake.workout.R;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class RoutineCreateAdapter extends SimpleAdapter implements ListView.OnItemClickListener {

    public RoutineCreateAdapter(Context context) {
        super(context);
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        TextView textView = null;

        if (items.get(position).isDivider()) {
            convertView = inflater.inflate(R.layout.wokout_row_divider, null);

            Workout workout = (Workout) items.get(position);

//            EditText editText = (EditText) convertView.findViewById(R.id.workout_name);
//            editText.setText(workout.getName());
            textView = (TextView) convertView.findViewById(R.id.workout_name);
            textView.setText(workout.getName());

            Button addExercise = (Button) convertView.findViewById(R.id.addExerciseToWeekDay);
            addExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
//                    Toast.makeText(parent.getContext(), "Add Exercise", Toast.LENGTH_SHORT).show();

//                    int pos = position > 0 ? position - 1 : 0;
                    int curPos = 0;

                    for(Item item : items.subList(position+1, items.size())) {
                        if(item.isDivider()) {
                            break;
                        }
                        Log.d("TAG", item.toString());
                        curPos++;
                    }

                    Log.d("TAG", "Size: " + items.size() + ", Position: " + position + ", curPos: " + curPos);

/*                    for (int i = items.size(); i > position + curPos; i--) {
                        Log.d("TAG", "SIZE: " + items.size() + ", i: " + i);
                        if(i == items.size()) {
                            items.add(items.get(items.size()-1));
                        }
                        items.add(i-1, items.get(i - 2));
                    }*/
                    items.add(position + 1 + curPos, new ExerciseEntry(1, "Sample " + (position + 1) + "-" + curPos, 20));

                    for (Item i : items) {
                        if(i.isDivider()) {
                            Workout w = (Workout) i;
                            Log.d("TAG", w.toString());
                        } else {
                            ExerciseEntry e = (ExerciseEntry) i;
                            Log.d("TAG", e.toString());
                        }
                    }
                    notifyDataSetChanged();


//                    addToItems(new ExerciseEntry(1, "Sample", 20));
                }
            });

            Button editWeekday = (Button) convertView.findViewById(R.id.editWeekday);
            editWeekday.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(parent.getContext(), "Edit Weekday", Toast.LENGTH_SHORT).show();
//                    context.startActionMode
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

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    public void remove(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }
}

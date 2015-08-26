package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;

public class ChooseExerciseAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    ArrayList<String> exercises;

    public ChooseExerciseAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        exercises = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.exercise_row_item, null);
        }

        textView = (TextView) convertView.findViewById(R.id.exerciseName);
        textView.setText(exercises.get(position));

        return convertView;
    }

    public void addExercise(String exercise) {
        exercises.add(exercise);
    }
}

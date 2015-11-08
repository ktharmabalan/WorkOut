package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;

public class WorkoutRecordAdapter extends SelectableAdapter {

    public WorkoutRecordAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        //        TextView exercise_name;
        TextView set_number;
        EditText weight;
        //        EditText sets;
        EditText reps;

        public RowViewHolder(View itemView) {
            super(itemView);

//            exercise_name = (TextView) itemView.findViewById(R.id.exercise_name);
            set_number = (TextView) itemView.findViewById(R.id.setNumber);
            weight = (EditText) itemView.findViewById(R.id.weight);
//            sets = (EditText) itemView.findViewById(R.id.sets);
            reps = (EditText) itemView.findViewById(R.id.reps);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        if(viewType == 1) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_record_item, parent, false);
        return new RowViewHolder(view);
//        }
//        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((RowViewHolder) holder).exercise_name.setText(((ExerciseEntry) items.get(position)).getName());
        ((RowViewHolder) holder).set_number.setText("Set " + (position + 1));
        ((RowViewHolder) holder).weight.setText(String.valueOf(((ExerciseEntry) items.get(position)).getWeight()) + "Lbs");
//        ((RowViewHolder) holder).sets.setText(String.valueOf(((ExerciseEntry) items.get(position)).getSets()));
        ((RowViewHolder) holder).reps.setText(String.valueOf(((ExerciseEntry) items.get(position)).getReps()));
    }

    public void addNewSet(ExerciseEntry exerciseEntry) {
        items.add(exerciseEntry);
        notifyDataSetChanged();
    }
}

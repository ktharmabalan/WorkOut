package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Exercise;
import ca.codemake.workout.models.Item;

public class ExerciseAdapter extends SelectableAdapter {

    public ExerciseAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView exercise_name;

        public RowViewHolder(View itemView) {
            super(itemView);

            exercise_name = (TextView) itemView.findViewById(R.id.exercise_name);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row, parent, false);
        return new RowViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder) holder).exercise_name.setText(((Exercise) items.get(position)).getName());
    }
}

package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.ExerciseEntry;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Workout;

public class WorkoutInputAdapter extends SelectableAdapter {

    public WorkoutInputAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class DividerViewHolder extends RecyclerView.ViewHolder {
        // Workout Divider
        TextView workoutName;
        ImageButton addExercise;
        ImageButton editWorkout;

        public DividerViewHolder(View itemView) {
            super(itemView);

            workoutName = (TextView) itemView.findViewById(R.id.workout_name);
            addExercise = (ImageButton) itemView.findViewById(R.id.addExerciseToWeekDay);
            addExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    int curPos = 0;
//
//                    for (Item item : items.subList(position + 1, items.size())) {
//                        if (item.isDivider()) {
//                            break;
//                        }
//                        curPos++;
//                    }
//
//                    items.add(position + 1 + curPos, new ExerciseEntry(1, "Sample " + (position + 1) + "-" + curPos, 20));
//                    notifyDataSetChanged();
                }
            });

            editWorkout = (ImageButton) itemView.findViewById(R.id.editWeekday);
            editWorkout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(context, "Edit Weekday", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        View view;
        // Workout Row
        TextView exerciseName;
        TextView sets;
        View selected;
//        TextView reps;

        public RowViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            exerciseName = (TextView) itemView.findViewById(R.id.exercise_name);
            sets = (TextView) itemView.findViewById(R.id.sets);
            selected = (View) itemView.findViewById(R.id.selected);
//            reps = (TextView) itemView.findViewById(R.id.reps);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row_divider, parent, false);
            return new DividerViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row_item, parent, false);
            return new RowViewHolder(v);
        }
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                ((DividerViewHolder) holder).workoutName.setText(((Workout) items.get(position)).getName());
                break;
            case 1:
                ((RowViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (clickListener != null) {
                            clickListener.onClick(position);
                        }
                    }
                });

                ((RowViewHolder) holder).view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if (clickListener != null) {
                            clickListener.onLongClick(position);
                            return true;
                        }
                        return false;
                    }
                });

                ((RowViewHolder) holder).exerciseName.setText(((ExerciseEntry) items.get(position)).getName());
                ((RowViewHolder) holder).sets.setText(String.valueOf(((ExerciseEntry) items.get(position)).getSets()) + " sets");
//                ((RowViewHolder) holder).reps.setText(String.valueOf(((ExerciseEntry) items.get(position)).getReps()));

                if (isSelected(position)) {
//                    ((RowViewHolder) holder).selected.setVisibility(View.VISIBLE);
                    ((RowViewHolder) holder).view.setBackgroundResource(R.drawable.background2);
//                    ((RowViewHolder) holder).view.setBackgroundResource(R.color.selected);
                } else {
//                    ((RowViewHolder) holder).selected.setVisibility(View.GONE);
                    ((RowViewHolder) holder).view.setBackgroundResource(R.drawable.background1);
//                    ((RowViewHolder) holder).view.setBackgroundResource(R.color.white);
                }
                break;
            default:
                break;
        }
    }

    public void addRow(Item item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }
}
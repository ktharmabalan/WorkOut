package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;

public class MealAdapter extends SelectableAdapter {

    public MealAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView meal_name;
        CheckBox visible;

        public RowViewHolder(View itemView) {
            super(itemView);

            meal_name = (TextView) itemView.findViewById(R.id.meal_name);
            visible = (CheckBox) itemView.findViewById(R.id.visible);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_row, parent, false);
        return new RowViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder) holder).meal_name.setText(((Meal) items.get(position)).getMealName());
        ((RowViewHolder) holder).visible.setChecked(((Meal) items.get(position)).isVisible());
        ((RowViewHolder) holder).visible.setText(((Meal) items.get(position)).isVisible() ? "Active" : "Inactive");
    }
}

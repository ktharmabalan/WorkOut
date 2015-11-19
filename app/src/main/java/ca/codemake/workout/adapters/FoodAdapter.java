package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Food;
import ca.codemake.workout.models.Item;

public class FoodAdapter extends SelectableAdapter {

    public FoodAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView calories;
        TextView servingSize;

        public RowViewHolder(View itemView) {
            super(itemView);

            foodName = (TextView) itemView.findViewById(R.id.food_name);
            calories = (TextView) itemView.findViewById(R.id.calories);
            servingSize = (TextView) itemView.findViewById(R.id.serving_size);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row, parent, false);
        return new RowViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder) holder).foodName.setText(((Food) items.get(position)).getFoodName());
        ((RowViewHolder) holder).calories.setText(String.valueOf(((Food) items.get(position)).getCalories()));
        ((RowViewHolder) holder).servingSize.setText(((Food) items.get(position)).getServingSize());
    }
}

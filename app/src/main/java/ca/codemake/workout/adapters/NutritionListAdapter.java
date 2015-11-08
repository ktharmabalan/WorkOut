package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionListAdapter extends SimpleListAdapter {

    private ClickListener clickListener;

    public interface ClickListener {
        public void onClick(int position);
        public void onLongClick(int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public NutritionListAdapter(Context context) {
        super(context);
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return  position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        TextView foodName = null;
        TextView calories = null;
        TextView servingSize = null;

        TextView mealName = null;
        Button addMealItem = null;

        if(items.get(position).isDivider())
            convertView = inflater.inflate(R.layout.food_entry_divider, null);
        else
            convertView = inflater.inflate(R.layout.food_entry_row_item, null);

        if(items.get(position).isDivider()) {
            Meal meal = (Meal) items.get(position);

            if(items.get(position).hasMarginTop()) {
                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.marginTop);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                params.setMargins(0, 20, 0, 0);
                linearLayout.setLayoutParams(params);
            }

            mealName = (TextView) convertView.findViewById(R.id.meal_name);
            mealName.setText(meal.getMealName());
            calories = (TextView) convertView.findViewById(R.id.mealCalories);
            calories.setText(String.valueOf(meal.getCalories()));

            addMealItem = (Button) convertView.findViewById(R.id.addMealEntry);

            addMealItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(clickListener != null) {
                        clickListener.onClick(position);
                    }
                }
            });
        } else {
            MealEntry mealEntry = (MealEntry) items.get(position);

            foodName = (TextView) convertView.findViewById(R.id.food_name);
            foodName.setText(mealEntry.getFoodName());
            calories = (TextView) convertView.findViewById(R.id.calories);
            calories.setText(String.valueOf(mealEntry.getCalories()));
            servingSize = (TextView) convertView.findViewById(R.id.serving_size);
            servingSize.setText(mealEntry.getServingSize());
        }
        return convertView;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
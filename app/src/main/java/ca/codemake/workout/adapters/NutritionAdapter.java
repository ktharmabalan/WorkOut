package ca.codemake.workout.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.AddNutritionEntryActivity;
import ca.codemake.workout.R;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    ArrayList<Meal> meals;

    // TEST
    ArrayList<MealEntry> mealEntries;
    ArrayList<Item> items;

    public NutritionAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        meals = new ArrayList<>();
        // TEST
        items = new ArrayList<>();
    }

    public int getCount() {
//        return meals.size();
        return items.size();
    }

    public Object getItem(int position) {
//        return meals.get(position);
        return items.get(position);
    }

    public long getItemId(int position) {
        return  position;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        TextView foodName = null;
        TextView calories = null;
        TextView servingSize = null;

        TextView mealName = null;
        Button addMealItem = null;

//        if(convertView == null) {
            if(items.get(position).isDivider())
                convertView = inflater.inflate(R.layout.food_entry_divider, null);
            else
                convertView = inflater.inflate(R.layout.food_entry_row_item, null);
//        }

        if(items.get(position).isDivider()) {
            Meal meal = (Meal) items.get(position);

            if(items.get(position).hasMarginTop()) {
                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.marginTop);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                params.setMargins(0, 20, 0, 0);
                linearLayout.setLayoutParams(params);
            }

            mealName = (TextView) convertView.findViewById(R.id.mealName);
            mealName.setText(meal.getMealName());
            calories = (TextView) convertView.findViewById(R.id.mealCalories);
            calories.setText(String.valueOf(meal.getCalories()));

            addMealItem = (Button) convertView.findViewById(R.id.button3);
            final TextView finalMealName = mealName;

            addMealItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(parent.getContext(), AddNutritionEntryActivity.class);
                    parent.getContext().startActivity(i);
//                    Toast.makeText(parent.getContext(), finalMealName.getText(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            MealEntry mealEntry = (MealEntry) items.get(position);

            foodName = (TextView) convertView.findViewById(R.id.foodName);
            foodName.setText(mealEntry.getFoodName());
            calories = (TextView) convertView.findViewById(R.id.calories);
            calories.setText(String.valueOf(mealEntry.getCalories()));
            servingSize = (TextView) convertView.findViewById(R.id.servingSize);
            servingSize.setText(mealEntry.getServingSize());
        }

        return convertView;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    // TESTING
    public void addMealEntries(ArrayList<MealEntry> mealEntries) {
        this.mealEntries = mealEntries;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}

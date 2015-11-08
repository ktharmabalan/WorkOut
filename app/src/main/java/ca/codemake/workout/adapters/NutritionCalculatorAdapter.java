package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Meal;
import ca.codemake.workout.models.MealEntry;

public class NutritionCalculatorAdapter extends SelectableAdapter {
    Meal meal;
    MealEntry mealEntry;
    NutritionClickListener nutritionClickListener;

    public interface NutritionClickListener {
        void onClick(int position, boolean isDivider);

        void onLongClick(int position, boolean isDivider);
    }

    public void setNutritionClickListener(NutritionClickListener nutritionClickListener) {
        this.nutritionClickListener = nutritionClickListener;
    }

    public NutritionCalculatorAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    private class DividerViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        TextView mealCalories;
        ImageButton addMealEntry;

        public DividerViewHolder(View view) {
            super(view);

            mealName = (TextView) view.findViewById(R.id.meal_name);
            mealCalories = (TextView) view.findViewById(R.id.mealCalories);
            addMealEntry = (ImageButton) view.findViewById(R.id.addMealEntry);
            addMealEntry.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (buttonListener != null) {
                        buttonListener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    private class RowViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView foodName;
        TextView calories;
        TextView servingSize;

        CheckBox checkBox;

        public RowViewHolder(View v) {
            super(v);

            view = v;
            foodName = (TextView) view.findViewById(R.id.food_name);
            calories = (TextView) view.findViewById(R.id.calories);
            servingSize = (TextView) view.findViewById(R.id.serving_size);

            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            checkBox.setChecked(false);

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (nutritionClickListener != null) {
                        nutritionClickListener.onClick(getAdapterPosition(), false);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (nutritionClickListener != null) {
                        nutritionClickListener.onLongClick(getAdapterPosition(), false);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_entry_divider, parent, false);
            return new DividerViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_entry_row_item, parent, false);
            return new RowViewHolder(view);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case 0:
                meal = (Meal) items.get(position);
                ((DividerViewHolder) holder).mealName.setText(meal.getMealName());
                ((DividerViewHolder) holder).mealCalories.setText(String.valueOf(meal.getCalories()));
                break;
            case 1:
                mealEntry = (MealEntry) items.get(position);
                ((RowViewHolder) holder).foodName.setText(mealEntry.getFoodName());
                ((RowViewHolder) holder).calories.setText(String.valueOf(mealEntry.getCalories()));
                ((RowViewHolder) holder).servingSize.setText(mealEntry.getServingSize());

/*                ((RowViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (nutritionClickListener != null) {
                            nutritionClickListener.onClick(position, false);
                        }
                    }
                });

                ((RowViewHolder) holder).view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Log.d("OMG", "CALLED1");
                        if (nutritionClickListener != null) {
                            Log.d("OMG", "CALLED2");
                            nutritionClickListener.onLongClick(position, false);
                            return true;
                        }
                        return false;
                    }
                });*/


                if (isSelected(position)) {
                    ((RowViewHolder) holder).view.setBackgroundResource(R.drawable.background2);
//                    ((RowViewHolder) holder).checkBox.setChecked(true);
//                    ((RowViewHolder) holder).checkBox.setVisibility(View.VISIBLE);
                } else {
                    ((RowViewHolder) holder).view.setBackgroundResource(R.drawable.background1);
//                    ((RowViewHolder) holder).checkBox.setChecked(false);
//                    ((RowViewHolder) holder).checkBox.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

    public void addToItems(Meal meal) {
        items.add(meal);
        notifyDataSetChanged();
    }
}

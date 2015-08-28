package ca.codemake.workout.models;

import java.util.ArrayList;

public class Meal implements Item {

    private String mealName;
    private int calories;
    private ArrayList<MealEntry> mealEntries;

    public Meal(String mealName) {
        this.mealName = mealName;
        mealEntries = new ArrayList<>();
        calories = 0;
    }

    public void AddMealEntry(MealEntry mealEntry) {
        mealEntries.add(mealEntry);
        calories += mealEntry.getCalories();
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Override
    public boolean isDivider() {
        return true;
    }
}

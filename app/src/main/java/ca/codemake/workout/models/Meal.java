package ca.codemake.workout.models;

import java.util.ArrayList;

public class Meal implements Item {

    private String mealName;
    private int calories = 0;
    private ArrayList<MealEntry> mealEntries;
    private boolean marginTop = false;

    public Meal(String mealName) {
        this.mealName = mealName;
        mealEntries = new ArrayList<>();
    }

    public Meal(String mealName, boolean marginTop) {
        this.mealName = mealName;
        this.marginTop = marginTop;
        mealEntries = new ArrayList<>();
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

    public void setMarginTop(boolean marginTop) {
        this.marginTop = marginTop;
    }

    public boolean hasMarginTop() {
        return marginTop;
    }
}
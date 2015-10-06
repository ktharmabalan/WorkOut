package ca.codemake.workout.models;

public class MealEntry implements Item {
    private String foodName;
    private int calories;
    private String servingSize;
    private boolean marginTop = false;

    public MealEntry(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = "Serving size";
    }

    public MealEntry(String foodName, int calories, String servingSize) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = servingSize;
    }

    public MealEntry(String foodName, int calories, boolean marginTop) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = "Serving size";
        this.marginTop = marginTop;
    }

    public MealEntry(String foodName, int calories, String servingSize, boolean marginTop) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = servingSize;
        this.marginTop = marginTop;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public boolean isDivider() {
        return false;
    }

    public void setMarginTop(boolean marginTop) {
        this.marginTop = marginTop;
    }

    public boolean hasMarginTop() {
        return marginTop;
    }
}

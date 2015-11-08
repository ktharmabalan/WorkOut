package ca.codemake.workout.models;

public class MealEntry implements Item {
    public long meal_entry_id;
    private long meal_id;
    private String meal_name;
    private long food_id;
    private String foodName;
    private int calories;
    private String servingSize;
    private double number_of_servings;

    public MealEntry(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = "Serving size";
    }

    public MealEntry(String foodName, int calories, long meal_entry_id) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = "Serving size";
        this.meal_entry_id = meal_entry_id;
    }

    public MealEntry(String foodName, int calories, String servingSize) {
        this.foodName = foodName;
        this.calories = calories;
        this.servingSize = servingSize;
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

    public boolean hasMarginTop() {
        return false;
    }
}

package ca.codemake.workout.models;

public class Food implements Item {

    private String foodName;
    private int calories;
    private String servingSize;

    public Food(String foodName, int calories, String servingSize) {
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

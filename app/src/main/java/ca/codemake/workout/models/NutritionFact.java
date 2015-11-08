package ca.codemake.workout.models;

public class NutritionFact {
    public String title = null;
    public boolean dailyValue = false;
    public boolean amountCheck = false;

    public NutritionFact(String title, boolean dailyValue, boolean amountCheck) {
        this.title = title;
        this.dailyValue = dailyValue;
        this.amountCheck = amountCheck;
    }
}

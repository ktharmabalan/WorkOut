package ca.codemake.workout.models;

import java.util.ArrayList;

public class WorkoutHeading {

    public String weekday;
    public String bodyGroup;
    public ArrayList<WorkoutRow> exerciseNames;

    public WorkoutHeading(String weekday, String bodyGroup) {
        this.weekday = weekday;
        this.bodyGroup = bodyGroup;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getBodyGroup() {
        return bodyGroup;
    }

    public void setBodyGroup(String bodyGroup) {
        this.bodyGroup = bodyGroup;
    }

    public ArrayList<WorkoutRow> getExerciseName() {
        return exerciseNames;
    }

    public void setExerciseName(ArrayList<WorkoutRow> exerciseNames) {
        this.exerciseNames = exerciseNames;
    }
}
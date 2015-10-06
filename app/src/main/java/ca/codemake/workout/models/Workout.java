package ca.codemake.workout.models;

import java.util.ArrayList;

public class Workout implements Item {

    private int id;
    private String name;
    private int routine_id;
    private String weekday;
    private String date;
    private boolean marginTop;

    private ArrayList<Exercise> exercises;

    public Workout(String name) {
        this.name = name;
        this.marginTop = false;
    }

    public Workout(String name, boolean marginTop) {
        this.name = name;
        this.marginTop = marginTop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void replaceAtPosition(int position, Exercise exercise) {
        if(exercises.size() <= position) {
            exercises.add(position, exercise);
        }
    }

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
        this.routine_id = routine_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMarginTop() {
        return marginTop;
    }

    public void setMarginTop(boolean marginTop) {
        this.marginTop = marginTop;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public ArrayList<Exercise> getExerciseName() {
        return exercises;
    }

    public void setExerciseName(ArrayList<Exercise> exerciseNames) {
        this.exercises = exerciseNames;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isDivider() {
        return true;
    }

    public boolean hasMarginTop() {
        return marginTop;
    }
}
package ca.codemake.workout.models;

import java.util.ArrayList;

public class Routine implements Item {

    private int id;
    private String name;
    private String start_date;
    private String end_date;
    private ArrayList<Workout> workouts;
    private int position;

    public Routine(String name) {
        this.name = name;
        workouts = new ArrayList<>();
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    public void replaceAtPosition(int position, Workout workout) {
        if(workouts.size() <= position) {
            workouts.add(position, workout);
        }
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public boolean isDivider() {
        return false;
    }

    public boolean hasMarginTop() {
        return false;
    }
}

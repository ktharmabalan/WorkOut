package ca.codemake.workout.models;

import java.util.ArrayList;

public class Routine implements Item {

    private int id;
    private String name;
    private String date_created;
    private String end_date;
    private int days_followed;
    private boolean active;
    private ArrayList<Workout> workouts;
    private int position;

    public Routine(String name, boolean active) {
        this.name = name;
        this.active = active;

    }

    public Routine(String name, String date_created, int days_followed, boolean active) {
        this.name = name;
        this.date_created = date_created;
        this.days_followed = days_followed;
        this.active = active;
    }

    public boolean isActive() {
        return active;
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
        if (workouts.size() <= position) {
            workouts.add(position, workout);
        }
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public boolean isDivider() {
        return false;
    }

    public boolean hasMarginTop() {
        return false;
    }
}

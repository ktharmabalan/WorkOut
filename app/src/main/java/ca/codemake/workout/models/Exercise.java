package ca.codemake.workout.models;

import java.util.ArrayList;

public class Exercise implements Item {

    private int id;
    private String name;
    private String description;
    private ArrayList<BodyGroup> bodyGroups;

    public Exercise(String name) {
        this.name = name;
        bodyGroups = new ArrayList<>();
    }

    public ArrayList<BodyGroup> getBodyGroups() {
        return bodyGroups;
    }

    public void setBodyGroups(ArrayList<BodyGroup> bodyGroups) {
        this.bodyGroups = bodyGroups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isDivider() {
        return false;
    }

    public boolean hasMarginTop() {
        return false;
    }
}

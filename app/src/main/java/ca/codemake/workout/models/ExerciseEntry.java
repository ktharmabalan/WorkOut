package ca.codemake.workout.models;

public class ExerciseEntry implements Item {

    private long id;
    private String name;
    private long sets;

    public ExerciseEntry(long id, String name, long sets) {
        setId(id);
        setName(name);
        setSets(sets);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSets() {
        return sets;
    }

    public void setSets(long sets) {
        this.sets = sets;
    }

    public boolean isDivider() {
        return false;
    }

    public boolean hasMarginTop() {
        return false;
    }

    @Override
    public String toString() {
        return "ExerciseEntry{ name='" + name + '}';
    }
}

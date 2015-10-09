package ca.codemake.workout.models;

public class ExerciseEntry implements Item {

    private long id;
    private String name;
    private long sets;
    private double weight;
    private long reps;

    public ExerciseEntry(long id, String name, long sets) {
        setId(id);
        setName(name);
        setSets(sets);
    }

    public ExerciseEntry(String name, double weight, long sets, long reps) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
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

    public long getReps() {
        return reps;
    }

    public void setReps(long reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return "ExerciseEntry{ name='" + name + '}';
    }
}

package ca.codemake.workout.models;

public class WorkoutRow {
    public String exerciseName;
    public int sets;

    public WorkoutRow(String exerciseName, int sets) {
        this.exerciseName = exerciseName;
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void increaseSet() {
        this.sets++;
    }

    public void decreaseSet() {
        this.sets--;
    }
}

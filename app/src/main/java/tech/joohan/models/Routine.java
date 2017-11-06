package tech.joohan.models;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    List<Exercise> exercises;
    public Routine (String name) {
        this.name = name;
    }
    public Routine (String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

}

package tech.joohan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Routine implements Serializable {
    String name;
    List<Exercise> exercises;

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


    public Routine(String name) {
        this.name = name;
    }

    public Routine(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

}

package tech.joohan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Routine implements Parcelable {
    private String name;
    private List<Exercise> exercises;

    protected Routine(Parcel in) {
        this.name = in.readString();
        this.exercises = in.readArrayList(Exercise.class.getClassLoader());
    }



    public static final Creator<Routine> CREATOR = new Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeList(this.exercises);
    }
}

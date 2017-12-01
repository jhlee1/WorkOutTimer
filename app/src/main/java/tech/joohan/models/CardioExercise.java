package tech.joohan.models;


import android.os.Parcel;

import java.util.List;

public class CardioExercise extends Exercise {
    long kilometers;
    int mins;
    int secs;

    protected CardioExercise(Parcel in) {
        super(in);
    }


}

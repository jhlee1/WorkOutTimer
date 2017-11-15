package tech.joohan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WeightTraining extends Exercise implements Parcelable {
    private List<Float> weights = new ArrayList<>();
    private List<Integer> timeForWorkout = new ArrayList<>();
    private List<Integer> timeForBreak  = new ArrayList<>();
    private List<Integer> repetition = new ArrayList<>();
    private int numOfSets;

    public WeightTraining (String name) {
        super(name);
    }
    public WeightTraining (int numOfSets, List<Float> weights, List<Integer> timeForWorkout, List<Integer> timeForBreak, List<Integer> repetition ) {
        this.weights = weights;
        this.timeForWorkout = timeForWorkout;
        this.timeForBreak = timeForBreak;
        this.repetition = repetition;
        this.numOfSets = numOfSets;
    }
    protected WeightTraining(Parcel in) {
        super(in);
        this.weights = in.readArrayList(Float.class.getClassLoader());
        this.timeForWorkout = in.readArrayList(Integer.class.getClassLoader());
        this.timeForBreak = in.readArrayList(Integer.class.getClassLoader());
        this.repetition = in.readArrayList(Integer.class.getClassLoader());
        this.numOfSets = in.readInt();

    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeList(this.weights);
        parcel.writeList(this.timeForWorkout);
        parcel.writeList(this.timeForBreak);
        parcel.writeList(this.repetition);
        parcel.writeInt(numOfSets);

    }
    public static final Creator<WeightTraining> CREATOR = new Creator<WeightTraining>() {
        @Override
        public WeightTraining createFromParcel(Parcel in) {
            return new WeightTraining(in);
        }

        @Override
        public WeightTraining[] newArray(int size) {
            return new WeightTraining[size];
        }
    };
}

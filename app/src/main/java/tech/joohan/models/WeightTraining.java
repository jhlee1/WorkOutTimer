package tech.joohan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeightTraining extends Exercise implements Parcelable {
    private List<Float> weights = new LinkedList<>();
    private List<Integer> timeForWorkout = new LinkedList<>();
    private List<Integer> timeForBreak  = new LinkedList<>();
    private List<Integer> repetition = new LinkedList<>();
    private int numOfSets;

    public void setWeights(List<Float> weights) {
        this.weights = weights;
    }
    public List<Float> getWeights() {
        return weights;
    }
    public void setTimeForWorkout(List<Integer> timeForWorkout) {
        this.timeForWorkout = timeForWorkout;
    }
    public List<Integer> getTimeForWorkout() {
        return timeForWorkout;
    }
    public void setTimeForBreak(List<Integer> timeForBreak) {
        this.timeForBreak = timeForBreak;
    }
    public List<Integer> getTimeForBreak() {
        return timeForBreak;
    }
    public void setRepetition(List<Integer> repetition) {
        this.repetition = repetition;
    }
    public List<Integer> getRepetition() {
        return repetition;
    }
    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }
    public int getNumOfSets() {
        return numOfSets;
    }

    public WeightTraining (String name) {
        super(name);
    }
    public WeightTraining (String name, String desc) {
        super(name, desc);
    }
    public WeightTraining (String name, String desc,int numOfSets, List<Float> weights, List<Integer> timeForWorkout, List<Integer> timeForBreak, List<Integer> repetition ) {
        super(name,desc);
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
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        private List<Float> weights = new LinkedList<>();
        private List<Integer> timeForWorkout = new LinkedList<>();
        private List<Integer> timeForBreak  = new LinkedList<>();
        private List<Integer> repetition = new LinkedList<>();
        private String name ;
        private int numOfSets;
        private String description;

        public Builder setWeights (List<Float> weights) {
            this.weights = weights;
            return this;
        }
        public Builder setTimeForWorkout (List<Integer> timeForWorkout) {
            this.timeForWorkout = timeForWorkout;
            return this;
        }
        public Builder setTimeForBreak(List<Integer> timeForBreak) {
            this.timeForBreak = timeForBreak;
            return this;
        }
        public Builder setRepetition(List<Integer> repetition) {
            this.repetition = repetition;
            return this;
        }
        public Builder setNumberOfSets(int numOfSets) {
            this.numOfSets = numOfSets;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }public Builder setDesciption(String description) {
            this.description = description;
            return this;
        }

        public WeightTraining build() {
            return new WeightTraining(name,description,numOfSets,weights,timeForWorkout,timeForBreak,repetition);
        }
    }




}

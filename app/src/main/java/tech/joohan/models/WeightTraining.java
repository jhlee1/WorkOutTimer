package tech.joohan.models;

import android.os.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WeightTraining extends Exercise {
    private ArrayList<WorkoutSet> plannedWorkoutSets = new ArrayList<>();
    private ArrayList<BreakTime> plannedBreakTimes = new ArrayList<>();
    private ArrayList<WorkoutSet> workoutSets = new ArrayList<>();
    private ArrayList<BreakTime> breakTimes = new ArrayList<>();
    private int numOfSets;

}

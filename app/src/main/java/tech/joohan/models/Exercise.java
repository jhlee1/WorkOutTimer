package tech.joohan.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Exercise implements Parcelable{
    protected String name;
    protected String description;

    protected Exercise(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public Exercise( String name) {
        this.name = name;
    }
    public Exercise(String name, String desc) {
        this.description = desc;
        this.name = name;
    }

    public Exercise() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
    }
    public List<String> propertiesToList() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add(this.name);
        properties.add(this.description);
        Log.d("ExerciseProperty","E1");
        return properties;
    }
}

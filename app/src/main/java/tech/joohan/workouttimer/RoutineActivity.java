package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import tech.joohan.models.Exercise;
import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {
    private int routineIndex;
    private ArrayList<Routine> routines;
    private Dialog createExerciseDialog;
    private String routineFileName;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Intent intent = getIntent();
        routineIndex = intent.getIntExtra("routineIndex",9999);
        TextView routineName = (TextView) findViewById(R.id.routineName);
        routines = intent.getParcelableArrayListExtra("routines");
        routineName.setText(routines.get(routineIndex).getName());

        /**
         * Add an exercise to the routine
         */
        ImageView addExerciseButton = (ImageView) findViewById(R.id.addExercise);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineIntent = new Intent(view.getContext(), WeightTrainingCreateActivity.class);
                routineIntent.putExtra("routineIndex",routineIndex);
                routineIntent.putParcelableArrayListExtra("routines",routines);
                view.getContext().startActivity(routineIntent);
            }
        });
        routineFileName = getResources().getString(R.string.routine_filename);
        FileInputStream inputStream = null;
        gson = new GsonBuilder().create();
        try {
            inputStream = this.openFileInput(routineFileName);
            Scanner s = new Scanner(inputStream);
            Type listType = new TypeToken<ArrayList<Routine>>(){}.getType();
            String input = null;
            if (s.hasNext()) {
                input = s.useDelimiter("\\A").next();
                routines = gson.fromJson(input, listType);
            } else {
                routines = new ArrayList<>();
            }
        } catch (IOException e ) {
                Log.d("outputstream exception", "Fail to create a routine file");
        }
        for(Routine r : routines) {
            Log.d("JSONREADING", r.getName());
            for(Exercise e : r.getExercises()) {
                Log.d("JSONREADING", e.getName());
                Log.d("JSONREADING", e.getDescription());
            }
        }

        /**
         * Implementing expandable list to show the exercises belong to this routine
         */

        ExpandableListView exerciseListView = (ExpandableListView) findViewById(R.id.exerciseList);
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter(this,routines.get(routineIndex).getExercises(),routines.get(routineIndex).exercisesToListForm());
        exerciseListView.setAdapter(exerciseListAdapter);
        exerciseListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        });
        exerciseListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });
        exerciseListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });
    }
}

package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import tech.joohan.models.Routine;
import tech.joohan.models.WeightTraining;

public class WeightTrainingCreateActivity extends Activity {
    private int routineIndex;
    private EditText name;
    private EditText numOfSets;
    private EditText repsOfSets;
    private EditText breaksOfSets;
    private EditText weightsOfSets;
    private EditText timesOfSets;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_weighttraining_layout);
        final Intent intent = getIntent();
        routineIndex = intent.getIntExtra("routineIndex",9999);
        final ArrayList<Routine> routines = intent.getParcelableArrayListExtra("routines");
        name  = (EditText) findViewById(R.id.exerciseNameInput);
        numOfSets = (EditText) findViewById(R.id.numOfSetsInput);
        repsOfSets = (EditText) findViewById(R.id.repsForEachSetInput);
        breaksOfSets = (EditText) findViewById(R.id.breakForEachSetInput);
        weightsOfSets = (EditText) findViewById(R.id.weightsForEachSetInput);
        timesOfSets = (EditText) findViewById(R.id.timeForEachSetInput);
        description = (EditText) findViewById(R.id.descriptionInput);
        Button b = (Button) findViewById(R.id.createExerciseButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validationChecker())
                    return;
                int sets = Integer.parseInt(numOfSets.getText().toString());
                List<Integer> reps = readIntListFromEditTextInput(repsOfSets, new LinkedList<Integer>());
                List<Integer> breaks = readIntListFromEditTextInput(breaksOfSets, new LinkedList<Integer>());
                List<Float> weights = readFloatListFromEditTextInput(weightsOfSets, new LinkedList<Float>());
                List<Integer> times = readIntListFromEditTextInput(timesOfSets, new LinkedList<Integer>());
                WeightTraining wt = WeightTraining.builder().setName(name.getText().toString())
                        .setNumberOfSets(sets)
                        .setRepetition(reps)
                        .setTimeForBreak(breaks)
                        .setWeights(weights)
                        .setTimeForWorkout(times)
                        .setDesciption(description.getText().toString())
                        .build();
                routines.get(routineIndex).getExercises().add(wt);
                Gson gson = new Gson();
                String jsonString = gson.toJson(routines);
                try {
                    FileOutputStream outputStream = openFileOutput(getResources().getString(R.string.routine_filename), view.getContext().MODE_PRIVATE);
                    outputStream.write(jsonString.getBytes());
                    outputStream.close();
                } catch(IOException e) {
                    Log.d("IOException", "Failed to write json");
                }
                finish();
            }
        });
    }

    private List<Integer> readIntListFromEditTextInput(EditText editText, List<Integer> list) {
        String tmp = editText.getText().toString();
        if (tmp.isEmpty()) {
            return null;
        } else {
            for(String s : tmp.split(",")) {
                list.add(Integer.parseInt(s));
            }
        }
        return list;
    }
    private List<Float> readFloatListFromEditTextInput(EditText editText, List<Float> list) {
        String tmp = editText.getText().toString();
        if (tmp.isEmpty()) {
            return null;
        } else {
            for(String s : tmp.split(",")) {
                list.add(Float.parseFloat(s));
            }
        }
        return list;
    }

    private boolean validationChecker() {
        String s = "\\d+(,{1}\\d+)*";
        if (name.getText().toString().isEmpty()) {
            name.setError("Please fill out this field");
            return false;
        } else {
            name.setError(null);
        }
        if (numOfSets.getText().toString().isEmpty()) {
            numOfSets.setError("Please fill out this field");
            return false;
        } else{
            numOfSets.setError(null);
        }
        if(!Pattern.matches(s,repsOfSets.getText().toString()) || repsOfSets.getText().toString().isEmpty()) {
            repsOfSets.setError("Please split by ,");
            return false;
        } else {
            repsOfSets.setError(null);
        }
        if(!breaksOfSets.getText().toString().matches(s) || breaksOfSets.getText().toString().isEmpty()) {
            breaksOfSets.setError("Please split by ,");
            return false;
        } else {
            breaksOfSets.setError(null);
        }
        if(!weightsOfSets.getText().toString().isEmpty() && !weightsOfSets.getText().toString().matches(s)) {
            weightsOfSets.setError("Please split by ,");
            return false;
        } else {
            weightsOfSets.setError(null);
        }
        if(!timesOfSets.getText().toString().isEmpty() && !timesOfSets.getText().toString().matches(s)) {
            timesOfSets.setError("Please split by ,");
            return false;
        } else {
            timesOfSets.setError(null);
        }

        return true;
    }
}

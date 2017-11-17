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

import tech.joohan.models.Routine;
import tech.joohan.models.WeightTraining;

public class WeightTrainingCreateActivity extends Activity {
    private int routineIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_weighttraining_layout);
        final Intent intent = getIntent();
        routineIndex = intent.getIntExtra("routineIndex",9999);
        final ArrayList<Routine> routines = intent.getParcelableArrayListExtra("routines");
        Button b = (Button) findViewById(R.id.createExerciseButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add a validations for empty input and num,num,num ....
                EditText name = (EditText) findViewById(R.id.exerciseNameInput);
                EditText numOfSets = (EditText) findViewById(R.id.numOfSetsInput);
                EditText repsOfSets = (EditText) findViewById(R.id.repsForEachSetInput);
                EditText breaksOfSets = (EditText) findViewById(R.id.breakForEachSetInput);
                EditText weightsOfSets = (EditText) findViewById(R.id.weightsForEachSetInput);
                EditText timesOfSets = (EditText) findViewById(R.id.timeForEachSetInput); // Add enter listener so the user does not have to click create by entering
                EditText description = (EditText) findViewById(R.id.descriptionInput);
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


}

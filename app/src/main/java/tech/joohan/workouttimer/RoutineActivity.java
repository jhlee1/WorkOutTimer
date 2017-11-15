package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {

    private Dialog createRoutineDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Intent intent = getIntent();
        Routine routine = (Routine) intent.getParcelableExtra("routine");
        TextView routineName = (TextView) findViewById(R.id.routineName);
        routineName.setText(routine.getName());
        ArrayList<Routine> routines = intent.getParcelableArrayListExtra("routines");

        ImageView addExerciseButton = (ImageView) findViewById(R.id.addExercise);
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRoutineDialog = createDialog();
                createRoutineDialog.show();
            }
        });
    }

    //Another option to get input ... to create another dialog box
    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.create_weighttraining_layout,null))
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        //Save to DB
//                        EditText routineInput = (EditText) createRoutineDialog.findViewById(R.id.routineInput);
//                        routines.add(new Routine(routineInput.getText().toString()));
//                        String jsonString = gson.toJson(routines);
//                        try {
//                            FileOutputStream outputStream = openFileOutput(routineFileName,getContext().MODE_PRIVATE);
//                            outputStream.write(jsonString.getBytes());
//                            outputStream.close();
//                        } catch (IOException e ) {
//                            Log.d("FileNotFound", "Fails to write a new routine");
//                        }
//                        ListView lv = (ListView) findViewById(R.id.routineList);
//                        lv.invalidate();





                        EditText numOfSets = (EditText) findViewById(R.id.numOfSetsInput);
                        EditText repsOfSets = (EditText) findViewById(R.id.repsForEachSetInput);
                        EditText breaksOfSets = (EditText) findViewById(R.id.breakForEachSetInput);
                        EditText weightsOfSets = (EditText) findViewById(R.id.weightsForEachSetInput);
                        EditText timesOfSets = (EditText) findViewById(R.id.timeForEachSetInput);








                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Cancel
                        createRoutineDialog.cancel();
                    }
                });
        return builder.create();
    }
    private Context getContext() {
        return this;
    }
}

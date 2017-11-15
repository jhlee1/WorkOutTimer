package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tech.joohan.models.Exercise;
import tech.joohan.models.Routine;
import tech.joohan.models.WeightTraining;

public class RoutineListActivity extends Activity {
    private RoutineListAdapter routineListAdapter;
    private List<Routine> routines;
    private Dialog createRoutineDialog;
    private Gson gson;
    private String routineFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list);
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
            try {
                FileOutputStream outputStream = openFileOutput(routineFileName, this.MODE_PRIVATE);
                outputStream.close();
                inputStream = this.openFileInput(routineFileName);
                inputStream.close();
            } catch (IOException e2) {
                Log.d("outputstream exception", "Fail to create a routine file");

            }
        }

        ListView listView = (ListView) findViewById(R.id.routineList);
        routineListAdapter = new RoutineListAdapter(this,routines);
        listView.setAdapter(routineListAdapter);

        ImageView addButton = (ImageView) findViewById(R.id.addRoutine);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRoutineDialog = createDialog();
                createRoutineDialog.show();
            }
        });
        //Just for testing purpose
//        Routine tmp = new Routine("Testing1");
//        Exercise tmp2 = new WeightTraining("Exercise1");
//        tmp2.setName("Test Exercise");
//        tmp.getExercises().add(tmp2);
//        routines.add(tmp);



    }

    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.create_routine_layout,null))
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                     //Save to DB
                        EditText routineInput = (EditText) createRoutineDialog.findViewById(R.id.routineInput);
                        routines.add(new Routine(routineInput.getText().toString()));
                        String jsonString = gson.toJson(routines);
                        try {
                            FileOutputStream outputStream = openFileOutput(routineFileName,getContext().MODE_PRIVATE);
                            outputStream.write(jsonString.getBytes());
                            outputStream.close();
                        } catch (IOException e ) {
                            Log.d("FileNotFound", "Fails to write a new routine");
                        }
                        ListView lv = (ListView) findViewById(R.id.routineList);
                        lv.invalidate();
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

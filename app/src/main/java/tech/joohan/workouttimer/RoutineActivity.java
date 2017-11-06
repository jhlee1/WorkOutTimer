package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {
    private RoutineListAdapter routineListAdapter;
    private List<Routine> routines;
    private Dialog createRoutineDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        routines = new ArrayList<>();
//        routines.add(new Routine("Testing1"));
//        routines.add(new Routine("Testing2"));
//        routines.add(new Routine("Testing3"));
//        routines.add(new Routine("Testing4"));
//        routines.add(new Routine("Testing1"));
//        routines.add(new Routine("Testing2"));
//        routines.add(new Routine("Testing3"));
//        routines.add(new Routine("Testing4"));
//        routines.add(new Routine("Testing1"));
//        routines.add(new Routine("Testing2"));
//        routines.add(new Routine("Testing3"));
//        routines.add(new Routine("Testing4"));
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


    }

    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.create_routine_layout,null))
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     //Save to DB
                        EditText routineInput = (EditText) findViewById(R.id.routineInput);
                        Log.d("Create a routine", "Saving"+ routineInput+ " into DB");
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
}

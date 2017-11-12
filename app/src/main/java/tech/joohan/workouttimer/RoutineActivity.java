package tech.joohan.workouttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Intent intent = getIntent();
        Routine routine = (Routine) intent.getParcelableExtra("routine");
        TextView routineName = (TextView) findViewById(R.id.routineName);
        routineName.setText(routine.getName());
        ArrayList<Routine> routines = intent.getParcelableArrayListExtra("routines");
        for(Routine r : routines) {
            if (r.getExercises().size() > 0)
                Log.d("Routine", r.getExercises().get(0).getName());
        }


    }
}

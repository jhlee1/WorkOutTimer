package tech.joohan.workouttimer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Routine r = (Routine) getIntent().getSerializableExtra("routine");
        TextView routineName = (TextView) findViewById(R.id.routineName);
        Log.d("routineName" , routineName+ " ");
        routineName.setText(r.getName());
//        Log.d("Got this", r.getName());


    }
}

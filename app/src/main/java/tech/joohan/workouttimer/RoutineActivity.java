package tech.joohan.workouttimer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.joohan.models.Routine;

public class RoutineActivity extends Activity {
    private int routineIndex;
    private Dialog createExerciseDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Intent intent = getIntent();
        routineIndex = intent.getIntExtra("routineIndex",9999);
        TextView routineName = (TextView) findViewById(R.id.routineName);
        final ArrayList<Routine> routines = intent.getParcelableArrayListExtra("routines");
        routineName.setText(routines.get(routineIndex).getName());


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
    }

}

package tech.joohan.workouttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button routineButton = (Button) findViewById(R.id.routineButton);
        routineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineIntent = new Intent(MainActivity.this, RoutineActivity.class);
                MainActivity.this.startActivity(routineIntent);
            }
        });


        Button workoutButton = (Button) findViewById(R.id.workoutButton);
        Button historyButton = (Button) findViewById(R.id.historyButton);
        Button circuitButton = (Button) findViewById(R.id.circuitButton);


    }
}

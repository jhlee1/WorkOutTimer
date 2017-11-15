package tech.joohan.workouttimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tech.joohan.models.Routine;


public class RoutineListAdapter extends BaseAdapter {
    private Context context;
    private List<Routine> routines;

    public RoutineListAdapter (Context context, List<Routine> routines) {
        this.context = context;
        this.routines = routines;
    }
    @Override
    public int getCount() {
        return routines.size();
    }

    @Override
    public Object getItem(int i) {
        return routines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {
        final View v = View.inflate(context,R.layout.routine, null);
        TextView routineName = (TextView) v.findViewById(R.id.routineTitle);
        routineName.setText(routines.get(i).getName());
        final int selectedRoutineIndex = i;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineIntent = new Intent(v.getContext(), RoutineActivity.class);
                routineIntent.putExtra("routineIndex",selectedRoutineIndex);
                routineIntent.putParcelableArrayListExtra("routines", (ArrayList<? extends Parcelable>) routines);
                v.getContext().startActivity(routineIntent);
            }
        });

        ImageView deleteButton = (ImageView) v.findViewById(R.id.deleteRoutine);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routines.remove(selectedRoutineIndex);
                try {
                    FileOutputStream outputStream = v.getContext().openFileOutput(v.getResources().getString(R.string.routine_filename),v.getContext().MODE_PRIVATE);
                    outputStream.write(new Gson().toJson(routines).getBytes());
                    outputStream.close();
                } catch (IOException e ) {
                    Log.d("FileNotFound", "Fails to write a new routine");
                }
                notifyDataSetChanged();
            }
        });
        return v;
    }


}

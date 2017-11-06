package tech.joohan.workouttimer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.routine, null);
        TextView routineName = (TextView) v.findViewById(R.id.routineTitle);
        routineName.setText(routines.get(i).getName());
//        ImageView deleteButton = (ImageView) v.findViewById(R.id.deleteRoutine);
        return v;
    }
}

package tech.joohan.workouttimer;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tech.joohan.models.Exercise;

/**
 * Created by Joohan on 2017-11-18.
 */

public class ExerciseListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Exercise> exercises;
    private HashMap<String,ArrayList<String>> exerciseProperties;
    public ExerciseListAdapter(Context context, List<Exercise> exercises, HashMap<String, ArrayList<String>> exerciseProperties){
        this.context = context;
        this.exerciseProperties = exerciseProperties;
        this.exercises = exercises;
    }

    @Override
    public int getGroupCount() {
        return exercises.size();
    }

    @Override
    public Exercise getGroup(int i) {
        return exercises.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater groupInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = groupInflater.inflate(R.layout.exercise_list_parent,viewGroup,false);
        }
        TextView exerciseName = (TextView) view.findViewById(R.id.exerciseName);
        exerciseName.setText(getGroup(i).getName());
        return view;
    }

    //It is child view beginning. Start from here~~~

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }


    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

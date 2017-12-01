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
    private HashMap<Exercise,List<String>> exerciseProperties;
    public ExerciseListAdapter(Context context, List<Exercise> exercises, HashMap<Exercise, List<String>> exerciseProperties){
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

        return this.exerciseProperties.get(this.exercises.get(i)).size();
    }


    @Override
    public String getChild(int groupPosition, int childPosition) {
        return this.exerciseProperties.get(this.exercises.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int parentPosition, int childPosition) {
        return childPosition;
    }



    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String property = getChild(groupPosition,childPosition).toString();
        if(view == null) {
            LayoutInflater childInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = childInflater.inflate(R.layout.exercise_list_child,null);
        }
        TextView exerciseProperty = (TextView) view.findViewById(R.id.exerciseProperty);
        exerciseProperty.setText(getChild(groupPosition,childPosition));
        return view;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

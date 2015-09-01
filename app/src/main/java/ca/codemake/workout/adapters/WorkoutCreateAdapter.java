package ca.codemake.workout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ca.codemake.workout.R;
import ca.codemake.workout.models.WorkoutHeading;
import ca.codemake.workout.models.WorkoutRow;

public class WorkoutCreateAdapter extends BaseAdapter {

//    ArrayList<WorkoutHeading> workoutHeadingList = new ArrayList<WorkoutHeading>();

    private static final int WORKOUT_HEADING = 0;
    private static final int WORKOUT_ROW = 1;

    private ArrayList<WorkoutHeading> workoutHeadings = new ArrayList<>();
    private ArrayList<WorkoutRow> workoutRows = new ArrayList<>();

    private HashMap<Integer, Integer> rowType = new HashMap<>();
    private HashMap<Integer, Integer> positionToType = new HashMap<>();
    private int rowPosition = 0;

    private LayoutInflater inflater;

    public WorkoutCreateAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRow(WorkoutRow item) {
        rowType.put(rowPosition, WORKOUT_ROW);
        workoutRows.add(item);
        positionToType.put(rowPosition, workoutRows.size() - 1);
        rowPosition++;
        notifyDataSetChanged();
    }

    public void addHeading(WorkoutHeading heading) {
//        workoutRows.add(item);
//        workoutHeadings.add(workoutRows.size() - 1);
        rowType.put(rowPosition, WORKOUT_HEADING);
        workoutHeadings.add(heading);
        positionToType.put(rowPosition, workoutHeadings.size() - 1);
        rowPosition++;
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        if(rowType.get(position) == WORKOUT_HEADING) {
            return WORKOUT_HEADING;
        } else {
            return WORKOUT_ROW;
        }
//        return rowType. contains(position) ? WORKOUT_HEADING : WORKOUT_ROW;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getCount() {
//        return workoutHeadingList.size();
//        return workoutRows.size();
        return rowPosition;
    }

    public Object getItem(int position) {
//        return workoutHeadingList.get(position);
        if(getItemViewType(position) == WORKOUT_HEADING) {
            return workoutHeadings.get(position);
        } else {
            return workoutRows.get(position);
        }
//        return workoutRows.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        int rowType = getItemViewType(position);

        if(convertView == null) {
            switch (rowType) {
                case WORKOUT_HEADING:
                    convertView = inflater.inflate(R.layout.wokout_row_divider, null);
                    textView = (TextView) convertView.findViewById(R.id.divider);
                    break;
                case WORKOUT_ROW:
                    convertView = inflater.inflate(R.layout.workout_row_item, null);
                    textView = (TextView) convertView.findViewById(R.id.exercise);
                    break;
            }
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }

        if(getItemViewType(position) == 0) {
//            textView.setText(workoutHeadings.get(position).weekday);
            textView.setText(workoutHeadings.get(positionToType.get(position)).weekday);
        } else {
//            textView.setText(workoutRows.get(position).exerciseName);
            textView.setText(workoutRows.get(positionToType.get(position)).exerciseName);
        }
        // textView.setText(workoutRows.get(position));

        return convertView;
    }
}

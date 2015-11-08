package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ca.codemake.workout.R;
import ca.codemake.workout.models.Item;
import ca.codemake.workout.models.Routine;

public class RoutineAdapter extends SelectableAdapter {

    public RoutineAdapter(Context context, ArrayList<Item> items) {
        super(context, items);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView routine_name;
        TextView active;
//        TextView date_created;
//        TextView days_followed;

        public RowViewHolder(View itemView) {
            super(itemView);

            routine_name = (TextView) itemView.findViewById(R.id.routine_name);
            active = (TextView) itemView.findViewById(R.id.active);
//            date_created = (TextView) itemView.findViewById(R.id.date_created);
//            days_followed = (TextView) itemView.findViewById(R.id.days_followed);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_row, parent, false);
        return new RowViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder) holder).routine_name.setText(((Routine) items.get(position)).getName());
        ((RowViewHolder) holder).active.setText(((Routine) items.get(position)).isActive() ? "active" : "");
    }
}

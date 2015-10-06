package ca.codemake.workout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import ca.codemake.workout.models.Item;

public class SimpleAdapter extends BaseAdapter {
    protected LayoutInflater inflater;
    protected ArrayList<Item> items;

    public SimpleAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        items = new ArrayList<>();
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return  position;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        return convertView;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}

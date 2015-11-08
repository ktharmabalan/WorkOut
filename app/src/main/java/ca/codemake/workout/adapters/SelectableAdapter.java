package ca.codemake.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.codemake.workout.models.Item;

public abstract class SelectableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SparseBooleanArray selectedItems;
    protected ArrayList<Item> items;

    protected ClickListener clickListener;
    protected ButtonListener buttonListener;

    protected Context context;

    public interface ClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    public interface ButtonListener {
        void onClick(int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void removeSelected() {
        List<Integer> toRemove = getSelectedItems();
        Collections.reverse(toRemove);
        for (Integer i : toRemove){
            if (isSelected(i)) {
//                Log.d("REMOVE", "> " + i + ", Size > " + items.size());
//                toggleSelection(i);
                items.remove((int)i);
            }
        }

        clearSelection();
        notifyDataSetChanged();
    }

    public void reset() {
        notifyDataSetChanged();
    }

    public SelectableAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
        selectedItems = new SparseBooleanArray();
    }

    // Check if item at position is selected
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    // Toggle selection
    public void toggleSelection(int position) {
        // if position exists delete it
        if(selectedItems.get(position)) {
            selectedItems.delete(position);
        } else {
            // put value of true for position
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    // Clear all selections
    public void clearSelection() {
        List<Integer> selectedList = getSelectedItems();
        selectedItems.clear();
        for(Integer i : selectedList) {
            notifyItemChanged(i);
        }
    }

    // Get list of selected positions
    public List<Integer> getSelectedItems() {
        List<Integer> selectedList = new ArrayList<>(selectedItems.size());
        for(int i = 0; i < selectedItems.size(); i++) {
            selectedList.add(selectedItems.keyAt(i));
        }
        return selectedList;
    }

    public int getItemCount() {
        return items.size();
    }

    public int getItemViewType(int position) {
        return items.get(position).isDivider() ? 0 : 1;
    }
}

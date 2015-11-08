package ca.codemake.workout.nutrition;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import ca.codemake.workout.R;

public class MealPickerFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public interface ButtonListener {
        public abstract void positiveButtonClick(String s);
    }

    private ButtonListener buttonListener;

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final View v = getActivity().getLayoutInflater().inflate(R.layout.meal_picker_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Create new meal")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (buttonListener != null) {
                            EditText editText = (EditText) v.findViewById(R.id.mealTitle);
                            buttonListener.positiveButtonClick(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton("Cancel", this)
                .setView(v);

        return builder.create();
    }

    public void onClick(DialogInterface dialog, int which) {
    }
}

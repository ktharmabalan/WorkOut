package ca.codemake.workout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import ca.codemake.workout.models.NutritionFact;

public class SettingPickerFragment extends DialogFragment {

    public interface SettingPickerInterface {
        public void positiveClick(NutritionFact nutritionFact);
//        public void dailyValueCheck(boolean isChecked);
//        public void amountCheck(boolean isChecked);
    }

    private SettingPickerInterface settingPickerInterface;

    public void setSettingPickerInterface(SettingPickerInterface settingPickerInterface) {
        this.settingPickerInterface = settingPickerInterface;
    }

    public boolean dailyValue = false;
    public boolean amountCheck = false;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.setting_picker_dialog, null);

        final CheckBox amountCheckedBox = (CheckBox) v.findViewById(R.id.amountCheck);
        amountCheck = amountCheckedBox.isChecked();
        amountCheckedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                amountCheck = isChecked;
                Log.d("Tag", "Amount Checked: " + isChecked + ", " + amountCheck);
//                if(settingPickerInterface != null) {
//                    settingPickerInterface.amountCheck(isChecked);
//                }
            }
        });

        CheckBox dailyValueBox = (CheckBox) v.findViewById(R.id.valueCheck);
        dailyValue = dailyValueBox.isChecked();
        dailyValueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dailyValue = isChecked;
                Log.d("Tag", "Daily Value: " + isChecked + ", " + dailyValue);
//                if(settingPickerInterface != null) {
//                    settingPickerInterface.dailyValueCheck(isChecked);
//                }
            }
        });

        final EditText editText = (EditText) v.findViewById(R.id.pickerTitle);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Create new meal")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(settingPickerInterface != null) {
                            settingPickerInterface.positiveClick(new NutritionFact(editText.getText().toString(), dailyValue, amountCheck));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setView(v);

        return builder.create();
    }
}
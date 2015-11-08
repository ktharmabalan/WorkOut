package ca.codemake.workout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    CalendarDateSelector calendarDateSelector;

    public interface CalendarDateSelector {
        public abstract void onSelectedDate(String date);
    }

    public void setCalendarDateSelector(CalendarDateSelector calendarDateSelector) {
        this.calendarDateSelector = calendarDateSelector;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current da te as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        calendarDateSelector.onSelectedDate(simpleDateFormat.format(calendar.getTime()));
    }
}
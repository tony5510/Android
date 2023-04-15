package ru.mirea.mitrofanov.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.MessageFormat;
import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment {

    private int myHour, myMinute;
    private final Calendar cal = Calendar.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        myHour = cal.get(Calendar.HOUR_OF_DAY);
        myMinute = cal.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        myHour = hourOfDay;
                        myMinute = minute;
                        String msg = MessageFormat.format("The time is {0} hours {1} minutes", myHour, myMinute);
                        Toast.makeText(getActivity(), msg,
                                Toast.LENGTH_LONG).show();
                    }
                }, myHour, myMinute, true);

        return tpd;
    }
}

package ru.mirea.mitrofanov.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.MessageFormat;
import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment {


    private final Calendar cal = Calendar.getInstance();
    private int myYear, myMonth, myDay;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker dpView, int year, int month, int dayOfMonth) {
                myYear = year;
                myMonth = month;
                myDay = dayOfMonth;
                String msg = MessageFormat.format("The Date is {0}.{1}.{2}",myDay, myMonth, myYear);
                Toast.makeText(getActivity(), msg,
                        Toast.LENGTH_LONG).show();
            }
        },myYear, myMonth, myMonth);
        return dpd;
    }
}
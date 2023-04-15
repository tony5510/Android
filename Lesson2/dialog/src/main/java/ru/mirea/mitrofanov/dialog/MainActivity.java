package ru.mirea.mitrofanov.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onClickTimeDialog(View view) {
        MyTimeDialogFragment myTimeDialogFragment = new MyTimeDialogFragment();
        myTimeDialogFragment.show(getSupportFragmentManager(), "time");
    }

    public void onClickDateDialog(View view) {
        MyDateDialogFragment myDateDialogFragment = new MyDateDialogFragment();
        myDateDialogFragment.show(getSupportFragmentManager(), "date");
    }

    public void onClickProgressDialog(View view) {
        MyProgressDialogFragment myProgressDialogFragment = new MyProgressDialogFragment();
        myProgressDialogFragment.show(getSupportFragmentManager(), "progress");
    }

    public void onFinalClick() {
        Snackbar.make(findViewById(R.id.button4), "Progress complete", Snackbar.LENGTH_SHORT)
                .show();
    }
}
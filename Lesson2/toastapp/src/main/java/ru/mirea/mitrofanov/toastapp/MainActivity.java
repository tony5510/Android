package ru.mirea.mitrofanov.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotification(View view) {
        String count = String.valueOf(editText1.length());
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Студент №17 Группа БСБО-03-20 Количество символов - %s", count),
                Toast.LENGTH_SHORT);
        toast.show();
    }
}
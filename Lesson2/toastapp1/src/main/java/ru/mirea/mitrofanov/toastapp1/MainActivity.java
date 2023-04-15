package ru.mirea.mitrofanov.toastapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText1);
    }

    public void showNotification(View view) {
        String count = String.valueOf(editText.length());
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Студент №8 Группа БСБО-03-20 Количество символов - %s", count),
                Toast.LENGTH_SHORT);
        toast.show();

    }

}
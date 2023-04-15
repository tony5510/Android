package ru.mirea.mitrofanov.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText fio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fio =findViewById(R.id.editTextTextPersonName);
    }

    public void onClickNewActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key", String.format("MIREA - %s", fio.getText()));
        startActivity(intent);


    }
}
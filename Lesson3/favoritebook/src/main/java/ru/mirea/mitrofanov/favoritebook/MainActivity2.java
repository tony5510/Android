package ru.mirea.mitrofanov.favoritebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView textViewBook;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        String nameOfTheBook = extras.getString(MainActivity.KEY);
        textViewBook = findViewById(R.id.textViewBook1);
        textViewBook.setText("Любимая книга разработчика – " + nameOfTheBook);
        editText = findViewById(R.id.editTextBook1);
    }

    public void chooseBook(View view) {
        Intent data = new Intent();
        data.putExtra(MainActivity.USER_MESSAGE, editText.getText().toString());
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}


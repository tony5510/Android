package ru.mirea.mitrofanov.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.mitrofanov.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("mitrofanov_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("GROUP", String.valueOf(binding.editText1.getText()));
                editor.putString("NUMBER", String.valueOf(binding.editText2.getText()));
                editor.putString("FILM", String.valueOf(binding.editText3.getText()));
                editor.apply();
            }
        });

        binding.editText1.setText(sharedPref.getString("GROUP", "BSBO-03-20"));
        binding.editText2.setText(sharedPref.getString("NUMBER", "17"));
        binding.editText3.setText(sharedPref.getString("FILM", "ResidentEvil"));
    }
}
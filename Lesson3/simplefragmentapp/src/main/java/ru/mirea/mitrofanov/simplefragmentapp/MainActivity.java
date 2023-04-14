package ru.mirea.mitrofanov.simplefragmentapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.mitrofanov.simplefragmentapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public class MainActivity extends AppCompatActivity {

        Fragment fragment1, fragment2;
        FragmentManager fragmentManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            fragment1 = new FirstFragment();
            fragment2 = new SecondFragment();
        }

        public void onClick(View view) {
            fragmentManager = getSupportFragmentManager();
            switch (view.getId()){
                case R.id.button1:
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
                    break;
                case R.id.button2:
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();
                    break;
                default:
                    break;
            }
        }
    }
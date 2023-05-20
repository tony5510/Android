package ru.mirea.mitrofanov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.mitrofanov.thread.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-03-20, НОМЕР ПО СПИСКУ: 17, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Обитель зла");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { new Thread(new Runnable() {
                public void run() {
                    int numberThread = count++;
                    Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %s ", numberThread, "БСБО-03-20","17"));
                    long endTime = System.currentTimeMillis() + 20 * 1000; while (System.currentTimeMillis() < endTime) {
                        synchronized (this) { try {
                            wait(endTime - System.currentTimeMillis());
                            Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime); } catch (Exception e) {
                            throw new RuntimeException(e); }
                        }
                        Log.d("ThreadProject", "Выполнен поток № " + numberThread); }
                } }).start();
            } });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        float lesson = Float.parseFloat(binding.editTextLessons.getText().toString())/Float.parseFloat(binding.editTextDays.getText().toString());
                        binding.textView2.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView2.setText("Среднее количество пар в день: " + Float.toString(lesson));
                            }
                        });
                    }

                };
                Thread thread  = new Thread(runnable);
                thread.start();
            }
        });
    }
}
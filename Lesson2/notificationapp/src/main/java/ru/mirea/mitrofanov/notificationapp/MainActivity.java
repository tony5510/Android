package ru.mirea.mitrofanov.notificationapp;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int PermissionCode = 200;

    private static final String CHANNEL_ID = "ru.mirea.mitrofanov.notificationapp.ANDROID";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
        }
        else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений!");
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, PermissionCode);
        }
    }

    public void onClickNewMessageNotification(View view) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                CHANNEL_ID)
                .setContentText("Разработчик: Митрофанов Павел Дмитриевич, БСБО-03-20:17")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Уведомление");
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Митрофанов Павел Дмитриевич", importance);

        channel.setDescription("MIREA Channel");
        notificationManager.createNotificationChannel(channel);
        notificationManager.notify(1, builder.build());

    }
}
package ru.mirea.mitrofanov.serviceapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PlayerService extends Service {
    public PlayerService() {
    }

    private MediaPlayer mediaPlayer;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) { stopForeground(true);
            } });
        return super.onStartCommand(intent, flags, startId); }
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Playing The Beatles - Yesterday(Remastered 2009)") .setSmallIcon(R.mipmap.ic_launcher) .setPriority(NotificationCompat.PRIORITY_HIGH) .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("The Beatles - Yesterday(Remastered 2009)")) .setContentTitle("Music Player");
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Student Mitrofanov P.D. Notification", importance);
        channel.setDescription("MIREA Channel");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this); notificationManager.createNotificationChannel(channel);
        startForeground(1, builder.build());
        mediaPlayer= MediaPlayer.create(this, R.raw.thebeatlesyesterday); mediaPlayer.setLooping(false);
    }
    @Override
    public void onDestroy() { stopForeground(true); mediaPlayer.stop();
    }
}
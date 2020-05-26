package com.example.musicclient;

import android.app.Notification;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationChannel;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
public class MusicPlayerService extends Service {
    private MediaPlayer mPlayer;
    private int mStartID;
    private Notification notification ;

    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannel();
        final Intent notificationIntent = new Intent(getApplicationContext(),
                MusicListActivity.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        notification =
                new NotificationCompat.Builder(getApplicationContext(), "Music Player Style")
                        .setSmallIcon(android.R.drawable.ic_media_play)
                        .setOngoing(true).setContentTitle("Music Playing")
                        .setContentText("Click to Access Music Player")
                        .setTicker("Music is playing!")
                        .setFullScreenIntent(pendingIntent, false)
                        .build();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("API service style", "API service notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("The channel for api service notifications");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getExtras().getString("url");
        // Set up the Media Player
        mPlayer = MediaPlayer.create(this, Uri.parse(url));

        if (null != mPlayer) {

            mPlayer.setLooping(false);

            // Stop Service when music has finished playing
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {

                    // stop Service if it was started with this ID
                    // Otherwise let other start commands proceed
                    stopSelf(mStartID);

                }
            });
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

        if (null != mPlayer) {

            mPlayer.stop();
            mPlayer.release();

        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}

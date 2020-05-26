package com.example.musiccentral;

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

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.util.Log;


import com.example.musiccommon.MusicAIDL;
import com.example.musiccommon.Song;

import java.util.List;

public class MusicService extends Service {
    private Notification notification;
    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannel();
        final Intent notificationIntent = new Intent(getApplicationContext(),
                MusicService.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0) ;
        notification = new NotificationCompat.Builder(getApplicationContext(), "API service style")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("API service active")
                .setContentText("Click to open main activity")
                .setTicker("Api service active!")
                .setFullScreenIntent(pendingIntent, false)
                .build();
        Log.i("MusicService", "Service started-notification created");
        startForeground(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("API service style", "API service notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("The channel for api service notifications");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Bitmap bitmapCreator(int id){
        Drawable imageDrawable = ContextCompat.getDrawable(this, MusicData.titleImage.get(id));
        Bitmap songImage = ((BitmapDrawable) imageDrawable).getBitmap();
        Log.i("bitmapCreator", "created Bitmap");
        return songImage;
    }

    private final MusicAIDL.Stub mBinder = new MusicAIDL.Stub() {

        @Override
        public int getNumbersofSongs() throws RemoteException {
            return MusicData.idAuthor.size();
        }

        @Override
        public String getTitle(int id) throws RemoteException {
            return MusicData.idTitles.get(id);
        }

        @Override
        public String getAuthor(int id) throws RemoteException {
            return MusicData.idAuthor.get(id);
        }

        @Override
        public String getUrl(int id) throws RemoteException {
            return MusicData.titleUrl.get(id);
        }

        @Override
        public List<String> getTitleList() throws RemoteException {
            return (List<String>) MusicData.idTitles.values();
        }

        @Override
        public List<String> getAuthorList() throws RemoteException {
            return (List<String>) MusicData.idAuthor.values();
        }

        @Override
        public List<String> getUrlList() throws RemoteException {
            return (List<String>) MusicData.titleUrl.values();
        }

        @Override
        public Bitmap getBitmap(int id) throws RemoteException {
            return bitmapCreator(id);
        }

        @Override
        public Song getSongInfo(int id) throws RemoteException {
            for(Song song: MusicData.idSongDao){
                if(song.getId() == id){
                    return song;
                }
            }
            return MusicData.idSongDao.get(0);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

package com.example.musicclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musiccommon.MusicAIDL;
import com.example.musiccommon.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    private MusicAIDL baseService;
    boolean mIsBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(MusicAIDL.class.getName());
        ResolveInfo info = getPackageManager().resolveService(i, 0);
        i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
        bindService(i, MusicListActivity.this.serviceConnection, Context.BIND_AUTO_CREATE);


    }



    public final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("onserviceconnected", "Connected");
            baseService = MusicAIDL.Stub.asInterface(service);
            Log.i("baseService", baseService.toString());
            mIsBound = true;

            final List<Song> song_List = new ArrayList<Song>();
            for(int j = 1; j<=5; j++){
                try {
                    song_List.add(baseService.getSongInfo(j));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            Log.i("size", String.valueOf(song_List.size()));

//            Error in getting listview. The code works and you can see in log that service pulls data

            final ListView listView = (ListView) findViewById(R.id.song1List);
            Log.i("this", MusicListActivity.this.toString());


//            Data is gotten from the other application
            Log.i("songlist", song_List.get(0).getAuthor());

            CustomAdapter customAdapter = new CustomAdapter(MusicListActivity.this, song_List);
            Log.i("ListAdapter", listView.toString());
            listView.setAdapter(customAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MusicListActivity.this, MusicPlayerService.class);
                    intent.putExtra("url", song_List.get(position).getUrl());
                    startService(intent);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            baseService = null;
            mIsBound = false;
            TextView statusView = findViewById(R.id.textViewstaus);
            statusView.setText("MusicCentral not connected");
        }
    };
}

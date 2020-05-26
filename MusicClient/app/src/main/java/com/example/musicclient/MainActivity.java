package com.example.musicclient;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.TextView;

import com.example.musiccommon.MusicAIDL;

public class MainActivity extends AppCompatActivity {
    private boolean mIsBound = false;
    private MusicAIDL baseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button binderButton = (Button) findViewById(R.id.button);
        final Button unbindButton = (Button) findViewById(R.id.button2);
        final Button showListButton = (Button) findViewById(R.id.button3);

        binderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsBound){
                    Log.i("Binder button", "Already connected");
                } else {
                    Boolean b = false;
                    Intent i = new Intent(MusicAIDL.class.getName());

                    Log.i("Intent", i.toString());

                    ResolveInfo info = getPackageManager().resolveService(i, 0);

//                    Log.i("pm", getPackageManager().toString());
//                    Log.i("info", info.toString());
//                    Log.i("packageName", info.serviceInfo.toString());
//                    Log.i("Name", info.serviceInfo.name);

                    i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
                    b = bindService(i, MainActivity.this.serviceConnection, Context.BIND_AUTO_CREATE);
                    if (b) {
                        Log.i("binderbutton", "Ugo says bindService() succeeded!");
                        showListButton.setVisibility(View.VISIBLE);
                    } else {
                        Log.i("binderbutton", "Ugo says bindService() failed!");
                    }
                }
            }
        });

        unbindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsBound){
                    mIsBound = false;
                    unbindService(MainActivity.this.serviceConnection);
                }
            }
        });

        showListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView statusView = findViewById(R.id.textViewstaus);
        if(mIsBound) {
            statusView.setText("MusicCentral connected");
        }
    }

    public final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("onserviceconnected", "Connected");
            baseService =MusicAIDL.Stub.asInterface(service);
            Log.i("baseService", baseService.toString());
            mIsBound = true;
            TextView statusView = findViewById(R.id.textViewstaus);
            statusView.setText("MusicCentral connected");

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

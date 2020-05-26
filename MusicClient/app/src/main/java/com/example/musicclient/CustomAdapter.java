package com.example.musicclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musiccommon.Song;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends BaseAdapter {

    public static int height = 600;
    public static int width = 500;
    public static int padding = 10;

    private Context context;
    private List<Song> songList;

    //    Constructor
    public CustomAdapter(Context c, List<Song> songList){
        context = c;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return songList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//      For regenerating layout after recycling
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.song_item_layout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.small_poster);
        TextView textView1 = convertView.findViewById(R.id.songTitle);
        TextView textView2 = convertView.findViewById(R.id.authorName);
        textView1.setText(songList.get(position).getTitle());
        textView2.setText(songList.get(position).getAuthor());
        imageView.setImageBitmap(songList.get(position).getImage());

        return convertView;
    }

}

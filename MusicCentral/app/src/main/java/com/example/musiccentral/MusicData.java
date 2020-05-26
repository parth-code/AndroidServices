package com.example.musiccentral;

import com.example.musiccommon.Song;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//"Brighten Your Day", "Mixaund", "https://www.free-stock-music.com/music/mixaund-brighten-your-day.mp3"
//"Above The Clouds", "FSM Team feat. < e s c p >", "https://www.free-stock-music.com/music/above-the-clouds.mp3"
//"Boat", "Vlad Gluschenko", "https://www.free-stock-music.com/music/vlad-gluschenko-boat.mp3"
//"Now We Ride (Metal Version)", "Alexander Nakarada", "https://www.free-stock-music.com/music/alexander-nakarada-now-we-ride-metal-version.mp3"
//"Bliss", "MusicbyAden", "https://www.free-stock-music.com/music/musicbyaden-bliss.mp3"

public class MusicData {
    public static List<Song> idSongDao = new ArrayList<Song>(){
        {
            add(new Song(1, "Brighten Your Day", "Mixaund", "https://www.free-stock-music.com/music/mixaund-brighten-your-day.mp3", null));
            add(new Song(2, "Above The Clouds", "FSM Team feat. < e s c p >", "https://www.free-stock-music.com/music/above-the-clouds.mp3", null));
            add(new Song(3, "Boat", "Vlad Gluschenko", "https://www.free-stock-music.com/music/vlad-gluschenko-boat.mp3", null));
            add(new Song(4, "Now We Ride (Metal Version)", "Alexander Nakarada", "https://www.free-stock-music.com/music/alexander-nakarada-now-we-ride-metal-version.mp3", null));
            add(new Song(5, "Bliss", "MusicbyAden", "https://www.free-stock-music.com/music/musicbyaden-bliss.mp3", null));
        }
    };

    static Map<Integer, String> idTitles = new LinkedHashMap<Integer, String>(){{
        put(1, "Brighten Your Day");
        put(2, "Above The Clouds");
        put(3, "Boat");
        put(4, "Now We Ride (Metal Version)");
        put(5, "Bliss");

    }};

    static Map<Integer, String> idAuthor = new LinkedHashMap<Integer, String>(){{
    put(1, "Mixaund");
    put(2, "FSM Team feat. < e s c p >");
    put(3, "Vlad Gluschenko");
    put(4, "Alexander Nakarada");
    put(5, "MusicbyAden");
   }};

   static Map<Integer, String> titleUrl = new LinkedHashMap<Integer, String>(){{
       put(1, "https://www.free-stock-music.com/music/mixaund-brighten-your-day.mp3");
       put(2, "https://www.free-stock-music.com/music/above-the-clouds.mp3");
       put(3, "https://www.free-stock-music.com/music/vlad-gluschenko-boat.mp3");
       put(4, "https://www.free-stock-music.com/music/alexander-nakarada-now-we-ride-metal-version.mp3");
       put(5, "https://www.free-stock-music.com/music/musicbyaden-bliss.mp3");

   }};
//    Needs to be converted to Bitmap to send using AIDL
   static Map<Integer, Integer> titleImage = new LinkedHashMap<Integer, Integer>(){{
        put(1, R.drawable.brightenyourday);
        put(2, R.drawable.abovetheclouds);
        put(3, R.drawable.boat);
        put(4, R.drawable.nowweride);
        put(5, R.drawable.bliss);
    }};
}

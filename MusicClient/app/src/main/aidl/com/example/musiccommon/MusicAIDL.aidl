// MusicAIDL.aidl
package com.example.musiccommon;
// Declare any non-default types here with import statements
import com.example.musiccommon.Song;
interface MusicAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    int getNumbersofSongs();
    String getTitle(in int id);
    String getAuthor(in int id);
    String getUrl(in int id);
    List<String> getTitleList();
    List<String> getAuthorList();
    List<String> getUrlList();
    Bitmap getBitmap(in int id);
    Song getSongInfo(int id);
//    List getSongURLandAudioById(in int id);

}

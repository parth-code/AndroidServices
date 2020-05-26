package com.example.musiccommon;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.musiccentral.MusicData;

public final class Song implements Parcelable {
    int id;
    String title;
    String author;
    String url;
    Bitmap image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Song(int id, String title, String author, String url, Bitmap image){
        this.id = id;
        this.author = author;
        this.title = title;
        this.url = url;
        this.image = image;
    }
//    Parcelable
    protected Song(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        url = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(url);
        dest.writeParcelable(image, flags);
    }
}

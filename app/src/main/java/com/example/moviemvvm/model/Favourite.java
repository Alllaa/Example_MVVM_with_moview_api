package com.example.moviemvvm.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class Favourite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "poster_path")
    String poster_path;

    @ColumnInfo(name = "state")
    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Favourite(@NonNull String title, String poster_path,int state) {
        this.title = title;
        this.poster_path = poster_path;
        this.state = state;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}

package com.example.moviemvvm.model.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviemvvm.AppClass;
import com.example.moviemvvm.model.Favourite;
import com.example.moviemvvm.model.MovieNow;
import com.example.moviemvvm.model.MovieRated;

@Database(entities = {MovieNow.class,MovieRated.class, Favourite.class}, version = 1, exportSchema = false)
public abstract class DatabaseMovie extends RoomDatabase {

    public abstract MovieNowDao getMovieNowDao();
    public abstract MovieRatedDao getMovieRatedDao();
    public abstract FavouriteDao getFavouriteDao();

    private static DatabaseMovie databaseMovie;

    public static DatabaseMovie getInstance() {
        if (null == databaseMovie) {
            synchronized (DatabaseMovie.class) {
                if (databaseMovie == null) {
                    databaseMovie = Room.databaseBuilder(AppClass.getObject(),
                            DatabaseMovie.class, "movie.db").
                            allowMainThreadQueries().build();
                }
            }
        }
        return databaseMovie;
    }


}

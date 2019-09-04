package com.example.moviemvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviemvvm.SingleLiveData;
import com.example.moviemvvm.model.Favourite;
import com.example.moviemvvm.model.MovieNow;
import com.example.moviemvvm.model.database.DatabaseMovie;
import com.example.moviemvvm.model.repository.MoviesNowGetData;

import java.util.List;

public class PLayNowViewModel extends ViewModel {

    private SingleLiveData<List<MovieNow>> liveData;
    private MoviesNowGetData moviesNowGetData = MoviesNowGetData.getInstance();
    DatabaseMovie databaseMovie = DatabaseMovie.getInstance();
    //List<Integer>states = new ArrayList<>();

    //SingleLiveEvent singleLiveEvent;

    public LiveData<List<MovieNow>> getMoives(int num) {
        liveData = moviesNowGetData.getMoviesFromServer(num);
        return liveData;

    }


    public void saveFavouriteFilm(MovieNow movieNow) {

        int state = movieNow.getState();
        if (state == 0) {
            Favourite favourite = new Favourite(movieNow.getTitle(), movieNow.getPoster_path(), 1);
            databaseMovie.getFavouriteDao().insert(favourite);
            Log.d("inserted", "data is inserted");

            movieNow.setState(1);
            databaseMovie.getMovieNowDao().setState(movieNow);
        } else {
            Favourite favourite = new Favourite(movieNow.getTitle(), movieNow.getPoster_path(), 0);
            databaseMovie.getFavouriteDao().delete(favourite);
            Log.d("deleted", "data is deleted");

            movieNow.setState(0);
            databaseMovie.getMovieNowDao().setState(movieNow);
        }


    }


}

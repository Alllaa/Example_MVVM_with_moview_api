package com.example.moviemvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviemvvm.model.Favourite;
import com.example.moviemvvm.model.MovieNow;
import com.example.moviemvvm.model.database.DatabaseMovie;

import java.util.List;

public class FavouriteViewModel extends ViewModel {
    DatabaseMovie databaseMovie;
    private MutableLiveData<List<Favourite>> liveData = new MutableLiveData<>();

    public LiveData<List<Favourite>> getMovies() {
        databaseMovie = DatabaseMovie.getInstance();
        List<Favourite> list = databaseMovie.getFavouriteDao().getAll();

        liveData.setValue(list);
        return liveData;
    }

    public void delFavouritMovie(Favourite favourite) {
        databaseMovie.getFavouriteDao().delete(favourite);
        MovieNow movieNow = new MovieNow();
        movieNow.setTitle(favourite.getTitle());
        movieNow.setPoster_path(favourite.getPoster_path());
        movieNow.setState(0);
        databaseMovie.getMovieNowDao().setState(movieNow);
    }


}

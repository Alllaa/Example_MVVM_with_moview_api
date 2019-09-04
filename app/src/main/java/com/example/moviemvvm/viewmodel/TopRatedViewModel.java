package com.example.moviemvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviemvvm.SingleLiveData;
import com.example.moviemvvm.model.MovieRated;
import com.example.moviemvvm.model.repository.MoviesRatedGetData;

import java.util.List;

public class TopRatedViewModel extends ViewModel {
    private SingleLiveData<List<MovieRated>> liveData;
    private MoviesRatedGetData moviesNowGetData;

    public void intit()
    {
        if(liveData != null)
        {
            return;
        }
        moviesNowGetData = MoviesRatedGetData.getInstance();
    }

    public LiveData<List<MovieRated>> getMoivesRated(int num)
    {
        liveData = moviesNowGetData.getMoviesFromServer(num);
        return liveData;

    }
}

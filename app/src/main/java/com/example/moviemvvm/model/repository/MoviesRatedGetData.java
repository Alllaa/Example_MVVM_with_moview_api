package com.example.moviemvvm.model.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviemvvm.SingleLiveData;
import com.example.moviemvvm.model.MovieRated;
import com.example.moviemvvm.model.MovieRatedList;
import com.example.moviemvvm.model.database.DatabaseMovie;
import com.example.moviemvvm.model.rest.ApiService;
import com.example.moviemvvm.model.rest.GetMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRatedGetData {
    private static MoviesRatedGetData moviesNowGetData;
    private List<MovieRated> dataSet ;
    SingleLiveData<List<MovieRated>> data = new SingleLiveData<List<MovieRated>>();
    DatabaseMovie databaseMovie = DatabaseMovie.getInstance();

    public static MoviesRatedGetData getInstance()
    {
        if (moviesNowGetData == null)
        {
            moviesNowGetData = new MoviesRatedGetData();
        }
        return moviesNowGetData;
    }

    public SingleLiveData<List<MovieRated>> getMoviesFromServer(final int num)
    {
        ApiService apiService;
        apiService = GetMovies.getClient().create(ApiService.class);

        Call<MovieRatedList> call =apiService.getTopMovie("7306fc3ca843fa9b34280a8f0f8d7b40",num);
        call.enqueue(new Callback<MovieRatedList>() {
            @Override
            public void onResponse(Call<MovieRatedList> call, Response<MovieRatedList> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Number","num of page is = "+ num);
                    //Log.d("MoviesNowGetData:","Error in response");
                    return;
                }
                dataSet = response.body().getItems();
                data.setValue(dataSet);
                Log.d("MoviesRatedGetData","Total number of Movies fetched : "+response.body().getItems().size());
                //Log.d("Number","num of page is = "+ num);
                if (num == 1)
                {
                    databaseMovie.getMovieRatedDao().insert(dataSet);
                }
            }

            @Override
            public void onFailure(Call<MovieRatedList> call, Throwable t) {
                Log.d("MoviesNowGetData:","Failure in response");
                if (num <= 1)
                {
                    dataSet = databaseMovie.getMovieRatedDao().getAll();
                    data.setValue(dataSet);
                }
            }
        });

        return data;
    }
}

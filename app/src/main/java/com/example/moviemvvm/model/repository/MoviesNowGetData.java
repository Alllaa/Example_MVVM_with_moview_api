package com.example.moviemvvm.model.repository;

import android.util.Log;

import com.example.moviemvvm.SingleLiveData;
import com.example.moviemvvm.model.Favourite;
import com.example.moviemvvm.model.MovieNow;
import com.example.moviemvvm.model.MovieNowList;
import com.example.moviemvvm.model.database.DatabaseMovie;
import com.example.moviemvvm.model.rest.ApiService;
import com.example.moviemvvm.model.rest.GetMovies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesNowGetData {
    private static MoviesNowGetData moviesNowGetData;
    private List<MovieNow> dataSet;
    SingleLiveData<List<MovieNow>> data = new SingleLiveData<List<MovieNow>>();
    DatabaseMovie databaseMovie = DatabaseMovie.getInstance();
    List<Favourite> states = new ArrayList<>();

    public static MoviesNowGetData getInstance() {
        if (moviesNowGetData == null) {
            moviesNowGetData = new MoviesNowGetData();
        }
        return moviesNowGetData;
    }

    public SingleLiveData<List<MovieNow>> getMoviesFromServer(final int num) {
        ApiService apiService;
        apiService = GetMovies.getClient().create(ApiService.class);

        Call<MovieNowList> call = apiService.getMovieNow("7306fc3ca843fa9b34280a8f0f8d7b40", num);
        call.enqueue(new Callback<MovieNowList>() {
            @Override
            public void onResponse(Call<MovieNowList> call, Response<MovieNowList> response) {
                if (!response.isSuccessful()) {
                    //  Log.d("Number","num of page is = "+ num);
                    Log.d("MoviesNowGetData:", "Error in response");
                    return;
                }
                dataSet = response.body().getItems();
                states = databaseMovie.getFavouriteDao().getAll();
                if (states.size() > 0) {

                    for (int j = 0; j < 20; j++) {

                        for (int i = 0; i < states.size(); i++) {
                            if (states.get(i).getTitle().equals(dataSet.get(j).getTitle())) {
                                dataSet.get(j).setState(states.get(i).getState());
                            }
                        }
                    }
                }

                if (num == 1) {
                    databaseMovie.getMovieNowDao().insert(dataSet);
                }


                data.setValue(dataSet);
                Log.d("MoviesNowGetData", "Total number of Movies data : " + response.body().getItems().size());
            }

            @Override
            public void onFailure(Call<MovieNowList> call, Throwable t) {
                Log.d("MoviesNowGetData:", "Failure in response");
                if (num == 1) {
                    dataSet = databaseMovie.getMovieNowDao().getAll();
                    data.setValue(dataSet);
                }

            }
        });

        return data;
    }

}

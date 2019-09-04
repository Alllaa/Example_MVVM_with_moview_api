package com.example.moviemvvm.model.rest;



import com.example.moviemvvm.model.MovieNowList;
import com.example.moviemvvm.model.MovieRatedList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("3/movie/now_playing")
    Call<MovieNowList> getMovieNow(
            @Query("api_key") String key
            ,@Query("page") int page
    );

    @GET("3/movie/top_rated")
    Call<MovieRatedList> getTopMovie(
            @Query("api_key") String key
            ,@Query("page") int page
    );
}

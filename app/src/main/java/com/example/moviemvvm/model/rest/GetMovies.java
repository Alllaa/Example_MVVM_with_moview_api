package com.example.moviemvvm.model.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetMovies {

    public static final String BASE_URL =   "https://api.themoviedb.org/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";
    public static final String BASE_URL2 = "https://api.themoviedb.org/";
    public static final String IMAGE_BASE_URL2 = "https://image.tmdb.org/t/p/original";

    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;

    public static Retrofit getClient()
    {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
    public static Retrofit getClient2()
    {
        if (retrofit2==null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}

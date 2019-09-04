package com.example.moviemvvm.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviemvvm.model.MovieRated;

import java.util.List;

@Dao
public interface MovieRatedDao {

    @Query("Select * FROM movie_rated")
    List<MovieRated> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieRated> list);


}

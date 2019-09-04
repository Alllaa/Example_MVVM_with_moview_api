package com.example.moviemvvm.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviemvvm.model.MovieNow;

import java.util.List;

@Dao
public interface MovieNowDao {

    @Query("Select * FROM movie_now")
    List<MovieNow> getAll();

//    @Query("Select All state FROM movie_now")
//    List<Integer> getAllState();

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertStates();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void setState(MovieNow movieNow);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieNow> list);

}

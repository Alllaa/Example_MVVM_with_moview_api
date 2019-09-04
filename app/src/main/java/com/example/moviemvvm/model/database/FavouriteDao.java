package com.example.moviemvvm.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviemvvm.model.Favourite;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Query("Select * FROM favourite")
    List<Favourite> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favourite list);

    @Delete
    void delete(Favourite list);
}

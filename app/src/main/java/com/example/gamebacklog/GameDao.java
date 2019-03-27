package com.example.gamebacklog;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GameDao {
    @Insert
    void insert(Game game);

    @Delete
    void delete(Game game);

    @Delete
    void delete(List<Game> game);

    @Update
    void update(Game game);

    @Query("DELETE FROM game_table")
    void clear();

    @Query("SELECT * from game_table")
    LiveData<List<Game>> getAllGames();
}

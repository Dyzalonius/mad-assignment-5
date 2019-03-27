package com.example.gamebacklog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Game.class}, version = 1, exportSchema = false)
public abstract class GameRoomDatabase extends RoomDatabase {

    private final static String NAME_DATABASE = "game_database";
    public abstract GameDao gameDao();
    private static volatile com.example.gamebacklog.GameRoomDatabase INSTANCE;

    public static com.example.gamebacklog.GameRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.gamebacklog.GameRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            com.example.gamebacklog.GameRoomDatabase.class, NAME_DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

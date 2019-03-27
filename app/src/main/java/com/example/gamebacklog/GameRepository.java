package com.example.gamebacklog;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class GameRepository {
    private GameRoomDatabase database;
    private GameDao dao;
    private LiveData<List<Game>> games;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public GameRepository (Context context) {
        database = GameRoomDatabase.getDatabase(context);
        dao = database.gameDao();
        games = dao.getAllGames();
    }

    public LiveData<List<Game>> getAllGames() {
        return games;
    }

    public void insert(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(game);
            }
        });
    }

    public void update(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(game);
            }
        });
    }

    public void delete(final Game game) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(game);
            }
        });
    }

    public void clear() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.clear();
            }
        });
    }
}
package com.example.gamebacklog;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    private GameRepository repo;
    private LiveData<List<Game>> games;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new GameRepository(application.getApplicationContext());
        games = repo.getAllGames();
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }

    public void getAllGames() {
        repo.getAllGames();
    }

    public void insert(Game game) {
        repo.insert(game);
    }

    public void update(Game game) {
        repo.update(game);
    }

    public void delete(Game game) {
        repo.delete(game);
    }

    public void delete(int index) {
        repo.delete(games.getValue().get(index));
    }

    public void clear() {
        repo.clear();
    }
}
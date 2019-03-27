package com.example.gamebacklog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private ArrayList<Game> games;
    private GameAdapter adapter;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)(findViewById(R.id.toolbar)));
        getSupportActionBar().setTitle("Game Backlog");

        FloatingActionButton addButton = findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddGameActivity.class), 1);
            }
        });

        games = new ArrayList<>();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        adapter = new GameAdapter(this, games);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(this);

        mainViewModel.getGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                updateUI(gameList);
            }
        });

        // Add click editing
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    int adapterPosition = recyclerView.getChildAdapterPosition(child);
                    mainViewModel.delete(games.get(adapterPosition));
                }
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    int adapterPosition = recyclerView.getChildAdapterPosition(child);
                    Game game = games.get(adapterPosition);
                    Intent intent = new Intent(MainActivity.this, EditGameActivity.class);
                    intent.putExtra("id", game.getId());
                    intent.putExtra("Title", game.getTitle());
                    intent.putExtra("Platform", game.getPlatform());
                    intent.putExtra("Status", game.getStatus());
                    startActivityForResult(intent, 2);
                }
                return true;
            }
        });

        // Add button deleting
        ImageButton deleteButton = findViewById(R.id.imageButtonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainViewModel.clear();
            }
        });

        // Add onSwipe deleting
        GameItemTouchHelper thing = new GameItemTouchHelper(mainViewModel, this);
        new ItemTouchHelper(thing).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("platform");
                String status = data.getStringExtra("status");
                String date = data.getStringExtra("date");

                Game newItem = new Game(null, title, description, status, date);
                mainViewModel.insert(newItem);
                mainViewModel.getAllGames();
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                Long id = data.getLongExtra("id", -1);
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("platform");
                String status = data.getStringExtra("status");
                String date = data.getStringExtra("date");

                Game game = new Game(id, title, description, status, date);
                mainViewModel.update(game);
                mainViewModel.getAllGames();
            }
        }
    }

    private void updateUI(List<Game> newGames) {
        games.clear();
        games.addAll(newGames);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
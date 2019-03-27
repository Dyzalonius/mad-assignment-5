package com.example.gamebacklog;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private Context context;
    public List<Game> games;

    public GameAdapter(Context context, List<Game> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        final Game game = games.get(position);
        holder.title.setText(game.getTitle());
        holder.platform.setText(game.getPlatform());
        holder.status.setText(game.getStatus());
        holder.date.setText(game.getDate());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}

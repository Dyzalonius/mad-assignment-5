package com.example.gamebacklog;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameViewHolder extends RecyclerView.ViewHolder {

    TextView title, platform, status, date;
    View view;

    public GameViewHolder(@NonNull View gameView) {
        super(gameView);

        title = gameView.findViewById(R.id.textViewTitle);
        platform = gameView.findViewById(R.id.textViewPlatform);
        status = gameView.findViewById(R.id.textViewStatus);
        date = gameView.findViewById(R.id.textViewDate);

        view = gameView;
    }
}

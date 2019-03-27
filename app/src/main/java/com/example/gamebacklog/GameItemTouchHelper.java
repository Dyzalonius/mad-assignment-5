package com.example.gamebacklog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class GameItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    MainViewModel mainViewModel;
    Context context;

    public GameItemTouchHelper(MainViewModel mainViewModel, Context context) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mainViewModel = mainViewModel;
        this.context = context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int index = viewHolder.getAdapterPosition();
        mainViewModel.delete(index);
    }
}

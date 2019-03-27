package com.example.gamebacklog;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_table")
public class Game {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title, platform, status, date;

    public Game(Long id, String title, String platform, String status, String date) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.status = status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}

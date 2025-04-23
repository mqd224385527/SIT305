package com.example.task51c.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class VideoItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String videoUrl;

    public VideoItem(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }
}

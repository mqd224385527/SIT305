package com.example.task51c.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.task51c.model.VideoItem;
import java.util.List;

@Dao
public interface VideoDao {
    @Insert
    void insert(VideoItem video);

    @Query("SELECT * FROM VideoItem ORDER BY id DESC")
    List<VideoItem> getAll();
}

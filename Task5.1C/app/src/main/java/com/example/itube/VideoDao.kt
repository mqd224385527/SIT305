package com.example.itube

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos")
    fun getAllVideos(): LiveData<List<Video>>

    @Insert
    suspend fun insertVideo(video: Video)

    @Delete
    suspend fun deleteVideo(video: Video)
} 
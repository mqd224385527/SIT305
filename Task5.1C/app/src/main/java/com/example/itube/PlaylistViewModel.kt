package com.example.itube

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val videoDao = database.videoDao()
    
    val videos: LiveData<List<Video>> = videoDao.getAllVideos()
    
    fun addVideo(video: Video) {
        viewModelScope.launch {
            videoDao.insertVideo(video)
        }
    }
    
    fun deleteVideo(video: Video) {
        viewModelScope.launch {
            videoDao.deleteVideo(video)
        }
    }
} 
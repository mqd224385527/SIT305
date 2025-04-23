package com.example.itube

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val newsApi = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    fun loadNews() {
        viewModelScope.launch {
            try {
                val response = newsApi.getTopHeadlines(apiKey = "c979da5ddd2e41f78e7bb2a64c7cd17e")
                if (response.status == "ok") {
                    _news.value = response.articles
                } else {
                    _error.value = "Failed to load news"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }
} 
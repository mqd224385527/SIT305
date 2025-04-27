package com.example.task61.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("quiz")
    Call<String> getAdaptiveQuiz();
}

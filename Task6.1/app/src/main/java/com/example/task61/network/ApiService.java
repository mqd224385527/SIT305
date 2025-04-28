package com.example.task61.network;

import com.example.task61.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("register")
    Call<Void> register(@Body User user);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("recommendations")
    Call<List<Recommendation>> getRecommendations();
}

package com.example.task61.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.task61.R;
import com.example.task61.model.Recommendation;
import com.example.task61.network.ApiClient;
import com.example.task61.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationFragment extends Fragment {

    LinearLayout container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommendation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        container = view.findViewById(R.id.recommendationContainer);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getRecommendations().enqueue(new Callback<List<Recommendation>>() {
            @Override
            public void onResponse(Call<List<Recommendation>> call, Response<List<Recommendation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Recommendation rec : response.body()) {
                        TextView tv = new TextView(getContext());
                        tv.setText("• " + rec.getTitle());
                        container.addView(tv);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recommendation>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load recommendations", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.task51c.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.task51c.R;
import com.example.task51c.adapter.NewsAdapter;
import com.example.task51c.model.NewsItem;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem("New Android Version", "Android 13 released!", "https://via.placeholder.com/150"));
        newsList.add(new NewsItem("Google I/O", "Latest Developer tools announced!", "https://via.placeholder.com/150"));

        recyclerView.setAdapter(new NewsAdapter(newsList));
        return view;
    }
}

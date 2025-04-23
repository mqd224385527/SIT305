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
import com.example.task51c.adapter.PlaylistAdapter;
import com.example.task51c.db.AppDatabase;
import com.example.task51c.model.VideoItem;

import java.util.List;

public class PlaylistFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPlaylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<VideoItem> videoList = AppDatabase.getInstance(getContext()).videoDao().getAll();
        recyclerView.setAdapter(new PlaylistAdapter(videoList));

        return view;
    }
}

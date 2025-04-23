package com.example.task51c.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.task51c.R;
import com.example.task51c.db.AppDatabase;
import com.example.task51c.model.VideoItem;

public class VideoPlayerFragment extends Fragment {

    private WebView webView;
    private EditText etTitle, etVideoUrl;
    private Button btnPlay, btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_player, container, false);

        webView = view.findViewById(R.id.webView);
        etTitle = view.findViewById(R.id.etTitle);
        etVideoUrl = view.findViewById(R.id.etVideoUrl);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnSave = view.findViewById(R.id.btnSave);

        btnPlay.setOnClickListener(v -> {
            String videoId = etVideoUrl.getText().toString().trim();
            String html = "<iframe width=\\\"100%\\\" height=\\\"100%\\\" src=\\\"https://www.youtube.com/embed/"
                    + videoId + "\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>";
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadData(html, "text/html", "utf-8");
        });

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String url = etVideoUrl.getText().toString().trim();
            if (!title.isEmpty() && !url.isEmpty()) {
                AppDatabase.getInstance(getContext()).videoDao().insert(new VideoItem(title, url));
            }
        });

        return view;
    }
}

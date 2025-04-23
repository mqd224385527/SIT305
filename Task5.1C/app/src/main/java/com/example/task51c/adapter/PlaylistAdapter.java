package com.example.task51c.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.task51c.R;
import com.example.task51c.model.VideoItem;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<VideoItem> videoList;

    public PlaylistAdapter(List<VideoItem> videoList) {
        this.videoList = videoList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvUrl;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUrl = itemView.findViewById(R.id.tvUrl);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoItem item = videoList.get(position);
        holder.tvTitle.setText(item.title);
        holder.tvUrl.setText("Video ID: " + item.videoUrl);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

package com.example.task71p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onRemoveClick(int position);
    }

    public ItemAdapter(List<Item> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lost_found, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        
        // Set background color based on item type
        if (item.getType().equals("Lost")) {
            holder.itemTypeTextView.setBackgroundResource(android.R.color.holo_red_light);
        } else {
            holder.itemTypeTextView.setBackgroundResource(android.R.color.holo_green_light);
        }
        
        holder.itemTypeTextView.setText(item.getType());
        holder.itemNameTextView.setText(item.getName());
        holder.itemDateTextView.setText(item.getDate());
        holder.itemLocationTextView.setText(item.getLocation());
        holder.itemDescriptionTextView.setText(item.getDescription());
        holder.contactTextView.setText("Contact: " + item.getName() + " (" + item.getPhone() + ")");
        
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onRemoveClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateData(List<Item> newItems) {
        this.itemList = newItems;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTypeTextView;
        TextView itemNameTextView;
        TextView itemDateTextView;
        TextView itemLocationTextView;
        TextView itemDescriptionTextView;
        TextView contactTextView;
        Button removeButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTypeTextView = itemView.findViewById(R.id.itemTypeTextView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemDateTextView = itemView.findViewById(R.id.itemDateTextView);
            itemLocationTextView = itemView.findViewById(R.id.itemLocationTextView);
            itemDescriptionTextView = itemView.findViewById(R.id.itemDescriptionTextView);
            contactTextView = itemView.findViewById(R.id.contactTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
} 
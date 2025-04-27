package com.example.task61.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;

    public User(String username) {
        this.username = username;
    }
}

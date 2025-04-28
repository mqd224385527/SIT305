package com.example.task61.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.task61.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

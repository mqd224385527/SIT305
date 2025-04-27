package com.example.task61.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.task61.models.User;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();
}

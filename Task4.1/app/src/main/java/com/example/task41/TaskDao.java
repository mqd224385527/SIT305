package com.example.task41;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;
@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    List<Task> getAllTasks();
    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    Task getTaskById(int id);
    @Update
    void updateTask(Task task);
    @Delete
    void deleteTask(Task task);
}

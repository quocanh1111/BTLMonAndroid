package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Task;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);
    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM TaskTable ORDER BY taskID ASC")
    List<Task> getAllTasks();
    @Query("SELECT * FROM TaskTable WHERE userID =:id")
    List<Task> getAllUserTasks(int id);
}

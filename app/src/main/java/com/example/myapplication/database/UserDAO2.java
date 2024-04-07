package com.example.myapplication.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Task;
import com.example.myapplication.User;

import java.util.List;

@Dao
public interface UserDAO2 {
    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM Users WHERE USERNAME = :name")
    User getUsersByName(String name);
    @Query("SELECT * FROM Users WHERE UID = :index")
    User getUsersByIndex(int index);
}

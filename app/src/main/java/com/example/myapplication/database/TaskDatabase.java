package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    private static final String DB_NAME = "TasksDB";
    private static TaskDatabase DB;

    public static synchronized TaskDatabase connectDB(Context ct) {
        if (DB == null) {
            DB = Room.databaseBuilder(ct.getApplicationContext(), TaskDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return DB;
    }
}

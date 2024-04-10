package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO2 userDao();
    private static final String DB_NAME = "TaskDatabase";
    private static UserDatabase DB;
    public static synchronized UserDatabase connectDB(Context ct) {
        if (DB == null) {
            DB = Room.databaseBuilder(ct.getApplicationContext(), UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return DB;
    }
}

package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Task;

@Database(entities = {Task.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    private static final String DB_NAME = "TaskDatabase";
    private static TaskDatabase DB;
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Perform the necessary SQL statements to modify the schema
            database.execSQL("ALTER TABLE Tasks ADD COLUMN newColumn TEXT");
        }
    };
    public static synchronized TaskDatabase connectDB(Context ct) {
        if (DB == null) {
            DB = Room.databaseBuilder(ct.getApplicationContext(), TaskDatabase.class, DB_NAME).addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();
        }
        return DB;
    }
}

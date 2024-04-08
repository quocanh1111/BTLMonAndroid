package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Users",indices = {@Index(value = "USERNAME", unique = true)})
public class User implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "UID")
    private int UID;
    @NonNull
    @ColumnInfo(name = "USERNAME")
    private String USERNAME;
    @NonNull
    @ColumnInfo(name = "PASSWORD")
    private String PASSWORD;

    public User(@NonNull String USERNAME, @NonNull String PASSWORD ) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    @NonNull
    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(@NonNull String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    @NonNull
    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(@NonNull String USERNAME) {
        this.USERNAME = USERNAME;
    }
}

package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Users",indices = {@Index(value = "USERNAME", unique = true)})
public class User implements Serializable {
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

    public User(String USERNAME ,String PASSWORD) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}

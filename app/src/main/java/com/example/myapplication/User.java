package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.lifecycle.LiveData;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Users",indices = {@Index(value = "USERNAME", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "UID")
    private int UID;
    @NonNull
    @ColumnInfo(name = "USERNAME")
    private int USERNAME;
    @NonNull
    @ColumnInfo(name = "PASSWORD")
    private int PASSWORD;

    public int getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(int PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(int USERNAME) {
        this.USERNAME = USERNAME;
    }
}

package com.example.myapplication;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.lifecycle.LiveData;
import java.time.LocalDateTime;
import java.util.List;


@Entity(tableName = "Task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "taskID")
    private int taskID;
    @NonNull
    @ColumnInfo(name = "taskName")
    private String taskName;
    @ColumnInfo(name = "taskDescription")
    private String taskDescription;
    @ColumnInfo(name = "isFinished")
    private boolean isFinished;
    @ColumnInfo(name = "taskDate")
    private int taskDate;
    @ColumnInfo(name = "taskMonth")
    private int taskMonth;
    @ColumnInfo(name = "taskYear")
    private int taskYear;
    public Task(){

    }
    public Task(String taskName,String taskDescription, int day,int month,int year){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        isFinished = false;
        taskDate = day;
        taskMonth = month;
        taskYear = year;
    }

    @NonNull
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(@NonNull int taskID) {
        this.taskID = taskID;
    }

    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(int taskDate) {
        this.taskDate = taskDate;
    }

    public int getTaskMonth() {
        return taskMonth;
    }

    public int getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(int taskYear) {
        this.taskYear = taskYear;
    }

    public void setTaskMonth(int taskMonth) {
        this.taskMonth = taskMonth;
    }
    public String getTimeByFormat(){
        String s = String.valueOf(taskDate) + "/" + String.valueOf(taskMonth) + "/" + String.valueOf(taskYear);
        return s;
    }

}

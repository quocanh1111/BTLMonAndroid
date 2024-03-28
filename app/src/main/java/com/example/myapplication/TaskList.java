package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.*;
import android.os.Bundle;

import com.example.myapplication.database.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity {
    private List<Task> tableData;
    private DataAdapter adapter;
    private RecyclerView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        data = findViewById(R.id.dataList);
        tableData = new ArrayList<Task>();
        tableData = TaskDatabase.connectDB(TaskList.this).userDao().getAllTasks();
        adapter = new DataAdapter(tableData);
        data.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        data.setLayoutManager(layoutManager);

    }

}
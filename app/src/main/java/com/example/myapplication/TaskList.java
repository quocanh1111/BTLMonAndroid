package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.example.myapplication.database.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity {
    private List<Task> tableData;
    private DataAdapter adapter;
    private RecyclerView data;
    private Button addBTN;
    private User curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        data = findViewById(R.id.dataList);
        addBTN = findViewById(R.id.add_btn_2);
        Intent i = getIntent();
        if(i != null){
            curUser = (User) i.getSerializableExtra("Task");
        }
        tableData = new ArrayList<Task>();
        tableData = TaskDatabase.connectDB(TaskList.this).userDao().getAllUserTasks(curUser.getUID());
        adapter = new DataAdapter(tableData);
        data.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        data.setLayoutManager(layoutManager);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(TaskList.this, TaskInput.class);
                startActivity(addIntent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        tableData = TaskDatabase.connectDB(TaskList.this).userDao().getAllTasks();
        adapter.setTasks(tableData);
        adapter.notifyDataSetChanged();
    }
}
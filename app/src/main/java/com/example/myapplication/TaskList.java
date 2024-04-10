package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.example.myapplication.database.TaskDatabase;
import com.example.myapplication.database.UserDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
        Context ct = this;
        Intent dataReceiver = getIntent();
        if (dataReceiver != null) {
            if (dataReceiver.hasExtra("LogInUser")) {
                User received_user = (User) dataReceiver.getSerializableExtra("LogInUser");
                if (curUser != null) {
                    curUser = received_user;
                }
            }
            else{
                //
            }
        }
        tableData = new ArrayList<Task>();
        tableData = TaskDatabase.connectDB(TaskList.this).userDao().getAllTasks();
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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView1);
        bottomNavigationView.setSelectedItemId(R.id.item1);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.item1) {
                    //
                    return true;
                }
                else if (itemId == R.id.item2) {
                    Intent intent1 = new Intent(ct, MainActivity.class);
                    intent1.putExtra("User", curUser);
                    startActivity(intent1);
                }
                else if (itemId == R.id.item3) {
                    Intent intent1 = new Intent(ct, UserInfo.class);
                    intent1.putExtra("User", curUser);
                    startActivity(intent1);
                    return true;
                }
                return false;
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
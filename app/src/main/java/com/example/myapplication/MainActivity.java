package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CalendarView;
import android.widget.*;

import com.example.myapplication.database.TaskDatabase;
import com.example.myapplication.database.UserDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendar;
    private TaskDatabase DB1;
    private TextView t;
    private Button bt;
    private Button bt2;
    private Button bt3;
    private List<Task> data;
    private String curUsername;
    private User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);
        t = findViewById(R.id.info);
        bt = findViewById(R.id.button);
        Context ct = this;
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView2);
        bottomNavigationView.setSelectedItemId(R.id.item2);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.item1) {
                    Intent intent1 = new Intent(ct, TaskList.class);
                    intent1.putExtra("User", curUser);
                    startActivity(intent1);
                    return true;
                }
                else if (itemId == R.id.item2) {
                    return true;
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

        // Initialize the TaskDatabase

        Intent dataReceiver = getIntent();
        if (dataReceiver != null) {
            if (dataReceiver.hasExtra("LogInUser")) {
                curUser =(User) dataReceiver.getSerializableExtra("LogInUser");
                if (curUser != null) {
                    data = TaskDatabase.connectDB(MainActivity.this).userDao().getAllUserTasks(curUser.getUID());
                    String chao = "Hello: " + curUser.getUSERNAME() + curUser.getUID();
                    t.setText(chao);
                }
                else{
                    t.setText("Vui lòng đăng nhập");
                }
            }

        }
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle selected day change
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskInput.class);
                intent.putExtra("User", curUser);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB1.close();
    }
}
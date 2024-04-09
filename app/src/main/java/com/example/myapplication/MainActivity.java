package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CalendarView;
import android.widget.*;

import com.example.myapplication.database.TaskDatabase;

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
    private User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calendar = findViewById(R.id.calendar);
        t = findViewById(R.id.info);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button3);
        bt3 = findViewById(R.id.button4);
        Intent dataReceiver = getIntent();
        if(dataReceiver != null){
            curUser = (User) dataReceiver.getSerializableExtra("Task");
        }
        if(curUser!=null){
            data = TaskDatabase.connectDB(this).userDao().getAllUserTasks(curUser.getUID());
            String chao = "Hello: "+curUser.getUSERNAME();
            t.setText(chao);
        }
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskInput.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("User",curUser);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(i);

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TaskList.class);
                intent.putExtra("User",curUser);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy(){

        super.onDestroy();
        DB1.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Set the intent flag to prevent creating a new instance of Activity 1
        Intent intent = getIntent();
        curUser = (User) intent.getSerializableExtra("User");
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        setIntent(intent);

    }
    public Task findTask(int day,int month,int year){
        for(Task t:data){
            if(t.getTaskDate()==day && t.getTaskMonth() == month && t.getTaskYear() == year){
                return t;
            }
        }
        return new Task();
    }
}
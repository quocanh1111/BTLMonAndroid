package com.example.myapplication;

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
    CalendarView calendar;
    TaskDatabase DB;
    Calendar c;
    TextView t;
    Button bt;
    Button bt2;
    int selectedDay;
    int selectedMonth;
    int selectedYear;
    List<Task> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = TaskDatabase.connectDB(this).userDao().getAllTasks();

        calendar = findViewById(R.id.calendar);
        t = findViewById(R.id.info);
        c = Calendar.getInstance();
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button3);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Task t = new Task();
                t = findTask(dayOfMonth,month,year);

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskInput.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TaskList.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy(){

        super.onDestroy();
        DB.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Set the intent flag to prevent creating a new instance of Activity 1
        Intent intent = getIntent();
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
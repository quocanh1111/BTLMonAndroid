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
        DB = TaskDatabase.connectDB(this);
        calendar = findViewById(R.id.calendar);
        t = findViewById(R.id.info);
        c = Calendar.getInstance();
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button3);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDay = dayOfMonth;
                selectedMonth = month;
                selectedYear = year;

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskInput.class);
                intent.putExtra("day",selectedDay);
                intent.putExtra("month",selectedMonth);
                intent.putExtra("year",selectedYear);
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
}
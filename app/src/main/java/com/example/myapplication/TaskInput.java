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
public class TaskInput extends AppCompatActivity{
    private EditText name;
    private TaskDatabase DB;
    private EditText description;
    private Button btn;
    private CalendarView inTime;
    private int inputDay;
    private int inputMonth;
    private int inputYear;
    private User curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_input);
        name = findViewById(R.id.taskName);
        description = findViewById(R.id.taskDescription);
        btn = findViewById(R.id.button2);
        inTime = findViewById(R.id.inputTime);
        Intent intent = getIntent();
        if(intent != null){
            curUser = (User) intent.getSerializableExtra("User");
        }

        inTime.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                inputDay = dayOfMonth;
                inputMonth = month+1;
                inputYear = year;

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(name)){
                    name.setError("This field cant be empty!");
                }
                else if(isEmpty(description)){
                    description.setError("This field cant be empty!");
                }
                else{
                    Task t = new Task(getInputString(name),getInputString(description),inputDay,inputMonth,inputYear,curUser.getUID());
                    TaskDatabase.connectDB(TaskInput.this).userDao().insertTask(t);
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG);
                    name.setText("");
                    description.setText("");
                    finish();
                }
            }
        });

    }
    public boolean isEmpty(EditText t){
        if(t.getText().toString().equals("")){
            return true;
        }
        return false;
    }
    public String getInputString(EditText t){
        return t.getText().toString();
    }

}

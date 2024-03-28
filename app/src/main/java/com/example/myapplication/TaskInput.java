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
    EditText name;
    TaskDatabase DB;
    EditText description;
    Button btn;
    int inputDay;
    int inputMonth;
    int inputYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_input);
        name = findViewById(R.id.taskName);
        description = findViewById(R.id.taskDescription);
        btn = findViewById(R.id.button2);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null){
            inputDay = (int) b.get("day");
            inputMonth = (int) b.get("month");
            inputYear = (int) b.get("year");
        }
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
                    Task t = new Task(getInputString(name),getInputString(description),inputDay,inputMonth,inputYear);
                    TaskDatabase.connectDB(TaskInput.this).userDao().insertTask(t);
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG);
                    name.setText("");
                    description.setText("");
                    Intent intent = new Intent(TaskInput.this, MainActivity.class);
                    startActivity(intent);
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

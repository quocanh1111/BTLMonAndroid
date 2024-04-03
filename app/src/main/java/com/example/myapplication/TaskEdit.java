package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.database.TaskDatabase;

public class TaskEdit extends AppCompatActivity {
    private EditText editedName;
    private EditText editedDescription;
    private CalendarView editedTime;
    private Button editBtn;
    private int editedDay;
    private int editedMonth;
    private int editedYear;
    private Task newTask;
    private boolean isTimeChanged;

    private CheckBox timeChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);
        editedName = findViewById(R.id.taskEditName);
        editedDescription = findViewById(R.id.taskEditDescription);
        editedTime = findViewById(R.id.editTime);
        timeChange = findViewById(R.id.changeTimeOrNot);
        editBtn = findViewById(R.id.Edit);
        Intent intent = getIntent();
        if(intent != null){
            newTask = (Task) intent.getSerializableExtra("Task");
        }
        editedName.setHint(newTask.getTaskName());
        editedDescription.setHint(newTask.getTaskDescription());


        editedTime.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                editedDay = dayOfMonth;
                editedMonth = month+1;
                editedYear = year;
            }
        });

        timeChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    newTask.setTaskDate(editedDay);
                    newTask.setTaskMonth(editedMonth);
                    newTask.setTaskYear(editedYear);
                }
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(editedName)){
                    newTask.setTaskName(editedName.getText().toString());
                }
                if(!isEmpty(editedDescription)){
                    newTask.setTaskDescription(editedDescription.getText().toString());
                }
                TaskDatabase.connectDB(TaskEdit.this).userDao().updateTask(newTask);
                finish();
            }
        });

    }
    public boolean isEmpty(EditText t){
        if(t.getText().toString().equals("")){
            return true;
        }
        return false;
    }
}
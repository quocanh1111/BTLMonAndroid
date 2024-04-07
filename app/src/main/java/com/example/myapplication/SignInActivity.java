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
public class SignInActivity extends AppCompatActivity{
    private EditText newUsername;
    private EditText newPass;
    private EditText confirmNewPass;
    private Button confirm;
    private Button escBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){   //Ham nay no de khoi tao cai activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        newUsername = findViewById(R.id.newusername);
        newPass = findViewById(R.id.newpass);
        confirmNewPass = findViewById(R.id.pass_confirm);
        confirm = findViewById(R.id.confirm_btn);
        escBtn = findViewById(R.id.esc_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                finish();
            }
        });
        escBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void validateInput(){     //Ham nay de kiem tra input
        if(newUsername.getText().toString().equals("")){
            newUsername.setError("Khong duoc bo trong");
        }
        if(newPass.getText().toString().equals("")){
            newPass.setError("Khong duoc bo trong");
        }
        if(confirmNewPass.getText().toString().equals(newPass.getText().toString())){
            confirmNewPass.setError("Phai trung voi mat khau duoc nhap");
        }
    }

}

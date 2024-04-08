package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;
public class LogInActivity extends AppCompatActivity{
    private EditText curUsername;
    private EditText curPass;
    private Button confirm;
    private Button escBtn;
    private String name;
    private String pass;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){   //Ham nay no de khoi tao cai activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        curUsername = findViewById(R.id.nhapTK);
        curPass = findViewById(R.id.nhapMatKhau);
        confirm = findViewById(R.id.cp_btn);
        escBtn = findViewById(R.id.esc_button);
        signUpBtn = findViewById(R.id.DangKyTK);
        name = curUsername.getText().toString();
        pass = curPass.getText().toString();
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
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });

    }
    public void validateInput(){     //Ham nay de kiem tra input
        if(name.equals("")){
            curUsername.setError("Khong duoc bo trong");
        }
        if(pass.equals("")) {
            curPass.setError("Khong duoc bo trong");
        }
        else{
            User log_in_user;
            log_in_user = UserDatabase.connectDB(LogInActivity.this).userDao().getUsersByName(name);
            if(!log_in_user.getPASSWORD().equals(pass)){
                curPass.setError("Sai mat khau, vui long nhap lai");
            }
            else{
                Intent i = new Intent(LogInActivity.this,MainActivity.class);
                i.putExtra("User",log_in_user);
                startActivity(i);
                finish();
            }
        }
    }

}

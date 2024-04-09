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
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
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
        if(getName().equals("")){
            curUsername.setError("Khong duoc bo trong");
            return;
        }
        if(getPass().equals("")) {
            curPass.setError("Khong duoc bo trong");
            return;
        }
        else{
            User log_in_user;
            User new_user = new User(getName(),getPass());
            log_in_user = UserDatabase.connectDB(LogInActivity.this).userDao().getUsersByName(getName());
            if(log_in_user==null){
                curUsername.setError("Nguoi dung khong ton tai!Vui long dang ky");
            }
            else if(!new_user.equals(log_in_user)){
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
    public String getName(){
        return curUsername.getText().toString();
    }
    public String getPass(){
        return curPass.getText().toString();
    }

}

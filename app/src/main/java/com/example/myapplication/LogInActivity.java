package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class LogInActivity extends AppCompatActivity{
    private EditText curUsername;
    private EditText curPass;
    private Button confirm;
    private Button escBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){   //Ham nay no de khoi tao cai activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        curUsername = findViewById(R.id.nhapTK);
        curPass = findViewById(R.id.nhapMatKhau);
        confirm = findViewById(R.id.cp_btn);
        escBtn = findViewById(R.id.esc_button);

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
        if(curUsername.getText().toString().equals("")){
            curUsername.setError("Khong duoc bo trong");
        }
        if(curPass.getText().toString().equals("")) {
            curPass.setError("Khong duoc bo trong");
        }
    }

}

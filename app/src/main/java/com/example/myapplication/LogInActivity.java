package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.UserDatabase;

public class LogInActivity extends AppCompatActivity {
    private EditText curUsername;
    private EditText curPass;
    private Button confirm;
    private Button escBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Intent i = new Intent(LogInActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

    private void validateInput() {
        String name = getName();
        String password = getPass();

        if (name.isEmpty()) {
            curUsername.setError("Không được bỏ trống");
            return;
        }

        if (password.isEmpty()) {
            curPass.setError("Không được bỏ trống");
            return;
        }

        User logInUser = UserDatabase.connectDB(LogInActivity.this).userDao().getUsersByName(name);

        if (logInUser == null) {
            curUsername.setError("Người dùng không tồn tại! Vui lòng đăng ký");
        } else if (!password.equals(logInUser.getPASSWORD())) {
            curPass.setError("Sai mật khẩu, vui lòng nhập lại");
        }
        else {
            Intent i = new Intent(LogInActivity.this, UserInfo.class);
            i.putExtra("LogInUser", logInUser);
            Log.d("MainActivity", "Extra key: " + "LogInUser" + ", value: " + logInUser.getUSERNAME());
            startActivity(i);
            finish();
        }
    }

    private String getName() {
        return curUsername.getText().toString().trim();
    }

    private String getPass() {
        return curPass.getText().toString().trim();
    }
}
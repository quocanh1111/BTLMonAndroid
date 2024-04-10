package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.database.UserDatabase;

public class SignInActivity extends AppCompatActivity {
    private EditText newUsername;
    private EditText newPass;
    private EditText confirmNewPass;
    private Button confirm;
    private Button escBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            }
        });

        escBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void validateInput() {
        String username = newUsername.getText().toString().trim();
        String password = newPass.getText().toString().trim();
        String confirmPassword = confirmNewPass.getText().toString().trim();

        if (username.isEmpty()) {
            newUsername.setError("Không được bỏ trống");
            return;
        }

        if (password.isEmpty()) {
            newPass.setError("Không được bỏ trống");
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmNewPass.setError("Mật khẩu xác nhận không khớp");
            return;
        }

        User existingUser = UserDatabase.connectDB(SignInActivity.this).userDao().getUsersByName(username);

        if (existingUser != null) {
            newUsername.setError("Người dùng đã tồn tại! Vui lòng nhập tên khác");
        }
        else {
            User newUser = new User(username, password);
            UserDatabase.connectDB(SignInActivity.this).userDao().insertUser(newUser);
            finish();
        }
    }
}

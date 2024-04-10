package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.database.TaskDatabase;
import com.example.myapplication.database.UserDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserInfo extends AppCompatActivity {
    private BottomNavigationView btv;
    private Button logInBTN;
    private Button signUpBTN;
    private TextView t1;
    private TextView t2;
    private User curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_info);
        t1 = findViewById(R.id.textView2);
        t2 = findViewById(R.id.textView3);
        logInBTN = findViewById(R.id.button13);
        signUpBTN = findViewById(R.id.button15);
        Context ct = this;
        Intent dataReceiver = getIntent();
        if (dataReceiver != null) {
            if (dataReceiver.hasExtra("LogInUser")) {
                curUser = (User) dataReceiver.getSerializableExtra("LogInUser");
                //Log.d("User: ",curUser.getUSERNAME());
                if (curUser != null) {
                    t1.setText("Hello: "+curUser.getUSERNAME() + curUser.getUID());
                    t2.setText("");
                }
                else{
                    t1.setText("Vui lòng đăng nhập");

                }
            }

        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView3);
        bottomNavigationView.setSelectedItemId(R.id.item3);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.item1) {
                    Intent intent1 = new Intent(ct, TaskList.class);
                    intent1.putExtra("User", curUser);
                    startActivity(intent1);
                    return true;
                }
                else if (itemId == R.id.item2) {
                    Intent intent1 = new Intent(ct, MainActivity.class);
                    intent1.putExtra("User", curUser);
                    startActivity(intent1);
                }
                else if (itemId == R.id.item3) {
                    return true;
                }
                return false;
            }
        });
        logInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ct, LogInActivity.class);
                startActivity(intent1);
            }
        });
        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ct, SignInActivity.class);
                startActivity(intent1);
            }
        });
    }
}
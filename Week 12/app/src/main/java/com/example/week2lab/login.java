package com.example.week2lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    Button loginBtn, clearBtn;
    EditText username, password;
    ArrayList<User> userData = new ArrayList<>();

    String usernameToCompare, passwordToCompare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        loginBtn = findViewById(R.id.loginBtn);
        clearBtn = findViewById(R.id.loginCancelBtn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        User admin = new User("admin","admin","admin");
        userData.add(admin);
        User user = new User("user","user","user");
        userData.add(user);
        User staff = new User("staff","staff","staff");
        userData.add(staff);
    }

    public void onClickLogin(View view){

        boolean flag = false;
        usernameToCompare = username.getText().toString();
        passwordToCompare = password.getText().toString();
        for(int i=0; i < userData.size(); i++){
            if(userData.get(i).getUsername().equals(usernameToCompare) && userData.get(i).getPassword().equals(passwordToCompare)){
                Toast.makeText(getApplicationContext(), "Redirecting", Toast.LENGTH_SHORT).show();
                flag = true;
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loginUser", userData.get(i).getName());
                startActivity(intent);
                break;
            }
        }
        if(!flag){
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCancel(View view){
        finish();
    }
}

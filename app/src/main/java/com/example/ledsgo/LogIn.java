package com.example.ledsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class LogIn extends AppCompatActivity {

    Button buttonSignUP, buttonLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        buttonSignUP = findViewById(R.id.button_Login_CreateUser);
        buttonLogIn = findViewById(R.id.button_Login_StartSession);


        buttonSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectPP connectPP = new ConnectPP(new DeviceRegistry(),new TestObserver());
                Intent intent = new Intent(getApplicationContext(), Show.class);
                startActivity(intent);
            }
        });

    }
}
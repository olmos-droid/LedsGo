package com.example.ledsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button button_ledsgo;

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int coreCount = Runtime.getRuntime().availableProcessors();
        Log.d(TAG, "onCreate: number core "+ coreCount);

        button_ledsgo = findViewById(R.id.button_lodsgo);

        button_ledsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Tiras.class);
                startActivity(intent);
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 5000);



    }
}
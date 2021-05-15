package com.example.ledsgo;

import android.os.Bundle;

import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

/**
 *
 */
public class Tiras extends AppCompatActivity  {
    /**
     * we use this static final to debug in console
     */
    private static final String TAG = "Tiras";

    Button buttonStrip1;
    Button buttonStrip2;
    Button buttonStrip3;
    Button buttonStrip4;
    Button buttonStrip5;
    Button buttonStrip6;
    Button buttonStrip7;
    Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiras);

        //hooks
        buttonStrip1 = findViewById(R.id.button_Strip1);
        buttonStrip2 = findViewById(R.id.button_Strip2);
        buttonStrip3 = findViewById(R.id.button_Strip3);
        buttonStrip4 = findViewById(R.id.button_Strip4);
//        buttonStrip5 = findViewById(R.id.button_Strip5);
//        buttonStrip6 = findViewById(R.id.button_Strip6);
//        buttonStrip7 = findViewById(R.id.button_Strip7);
        buttonStop = findViewById(R.id.button_Stop);
    }

}
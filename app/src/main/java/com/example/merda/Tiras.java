package com.example.merda;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.heroicrobot.dropbit.registry.DeviceRegistry;


public class Tiras extends AppCompatActivity  {
    private static final String TAG = "Tiras";

    Button buttonStrip0;
    Button buttonStrip1;
    Button buttonStop;

    DeviceRegistry registry = new DeviceRegistry();
    com.example.ledsgo.TestObserver testObserver = new com.example.ledsgo.TestObserver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiras);

        buttonStrip0 = findViewById(R.id.button_Strip0);
        buttonStrip1 = findViewById(R.id.button_Strip1);

        buttonStop = findViewById(R.id.button_Stop);
//            registry.addObserver(testObserver);
//
//
//        int nCore = Runtime.getRuntime().availableProcessors(); // miramos cuantos procesadores tiene el phone
//        ExecutorService service = Executors.newFixedThreadPool(nCore);
//        service.execute(new ConnectPP(registry, testObserver));
//
//
//        ExecutorService strip0 = Executors.newFixedThreadPool(1);






    }

}
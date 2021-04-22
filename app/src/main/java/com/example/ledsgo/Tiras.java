package com.example.ledsgo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Tiras extends AppCompatActivity implements LifecycleObserver {
    Button buttonStrip0;
    Button buttonStop;
    Scraper strip0;
    DeviceRegistry registry = new DeviceRegistry();
    TestObserver testObserver = new TestObserver();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiras);

        buttonStrip0 = findViewById(R.id.button_strip0);

        buttonStop = findViewById(R.id.button_Stop);
        registry.addObserver(testObserver);

        ConnectPP connectPP = new ConnectPP(registry, testObserver);
        Thread threadConnectpp = new Thread(connectPP);
        threadConnectpp.start();
        strip0 = new Scraper(0,registry,testObserver,false);




        buttonStrip0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread threadStrip0 = new Thread(strip0);
                threadStrip0.start();
                Toast.makeText(Tiras.this, "todo bien hasta aqui", Toast.LENGTH_SHORT).show();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    strip0.setStopthread(true);

                } catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                }
            }
        });

    }
}
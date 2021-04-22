package com.example.ledsgo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class Tiras extends AppCompatActivity implements LifecycleObserver {
    Button buttonStrip0;
    Button buttonStrip1;
    Button buttonStop;
    Scraper strip0;
    Scraper strip1;
    DeviceRegistry registry = new DeviceRegistry();
    TestObserver testObserver = new TestObserver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiras);

        buttonStrip0 = findViewById(R.id.button_Strip0);
        buttonStrip1 = findViewById(R.id.button_Strip1);

        buttonStop = findViewById(R.id.button_Stop);
        registry.addObserver(testObserver);

        ConnectPP connectPP = new ConnectPP(registry, testObserver);
        Thread threadConnectpp = new Thread(connectPP);
        threadConnectpp.start();


        strip0 = new Scraper(0, registry, testObserver, false);
        strip1 = new Scraper(1, registry, testObserver, false);

        Thread threadStrip0 = new Thread(strip0);
        Thread threadStrip1 = new Thread(strip1);


        buttonStrip0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadStrip0.setName("strip0");
                threadStrip0.start();
                Toast.makeText(Tiras.this, threadStrip0.getName() + " working", Toast.LENGTH_SHORT).show();
            }
        });
        buttonStrip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                threadStrip1.setName("strip1");
                threadStrip1.start();
                Toast.makeText(Tiras.this, threadStrip1.getName() + " working", Toast.LENGTH_SHORT).show();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    //TODO HAY QUE PARAR ESTO
                } catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                }
            }
        });

    }
}
package com.example.merda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tiras extends AppCompatActivity {
    private static final String TAG = "Tiras";

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


        int nCore = Runtime.getRuntime().availableProcessors(); // miramos cuantos procesadores tiene el phone
        ExecutorService service = Executors.newFixedThreadPool(nCore);
        service.execute(new ConnectPP(registry, testObserver));


        ExecutorService strip0 = Executors.newFixedThreadPool(1);





        buttonStrip0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.execute(new Scraper(0, registry, testObserver, false));

            }
        });
        buttonStrip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

    }

}
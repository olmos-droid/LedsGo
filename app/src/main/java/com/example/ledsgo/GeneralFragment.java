package com.example.ledsgo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;
import com.rtugeek.android.colorseekbar.ColorSeekBar;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GeneralFragment extends Fragment {
    public static final String TAG = "GeneralFragment";
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorSeekBar colorSeekBar;
    private TextView textView;
    byte[] colorRGB = {0, 0, 0};
    private Scraper preset;
    private int speed = 100;
    ColorLed colorLed = new ColorLed();
    ExecutorService service;

    public GeneralFragment(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;
        List<Strip> strips = registry.getStrips();


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        registry.addObserver(testObserver);

        int nCore = Runtime.getRuntime().availableProcessors(); // miramos cuantos procesadores tiene el phone
         service = Executors.newFixedThreadPool(nCore);


        ConnectPP connectPP = new ConnectPP(registry, testObserver);
        service.execute(connectPP);


        preset = new Scraper(this.registry, this.testObserver, 0,speed,colorLed);


        // Inflate the layout for this fragment
        List<Strip> strips = registry.getStrips();
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        colorSeekBar = view.findViewById(R.id.color_seek_bar);

        Button btn_preset1 = view.findViewById(R.id.button_general_patertn1);
        Button btn_preset2 = view.findViewById(R.id.button_general_patertn2);
        Button btn_preset3 = view.findViewById(R.id.button_general_patertn3);
        Button btn_preset4 = view.findViewById(R.id.button_general_patertn4);
        Button btn_preset5 = view.findViewById(R.id.button_general_patertn5);
        Button btn_preset6 = view.findViewById(R.id.button_general_patertn6);
        Button btn_preset7 = view.findViewById(R.id.button_general_patertn7);
        Button btn_preset8 = view.findViewById(R.id.button_general_patertn8);

        textView = view.findViewById(R.id.textView_color);


        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void
            onColorChangeListener(int i, int i1, int i2) {
                Color color = null;

                int intColor = colorSeekBar.getColor();
                Log.d(TAG, "onColorChangeListener: " + intColor);

                Log.d(TAG, "onColorChangeListener: red " + color.red(intColor));
                Log.d(TAG, "onColorChangeListener: gree " + color.green(intColor));
                Log.d(TAG, "onColorChangeListener: blue  " + color.blue(intColor));

                //importante los de tipo byte no coincide con los tipo int , es decir un int red = 250 no es lo mismo que un byte = 255
//
//                colorRGB[0] = (byte) color.red(intColor);
//                colorRGB[1] = (byte) color.green(intColor);
//                colorRGB[2] = (byte) color.blue(intColor);


                colorLed.setRed((byte) color.red(intColor));
                colorLed.setGreen((byte) color.green(intColor));
                colorLed.setBlue((byte) color.blue(intColor));


            }
        });


        btn_preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: has apretat el preset 1");

                if (preset.getPreset() == 8)
                {
                    preset = new Scraper(registry, testObserver, 1, speed,colorLed);
                    service.execute(preset);

                } else
                {
                    preset.setPreset(1);
                    service.execute(preset);
                }
            }

        });
        btn_preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: genaral pattern 2 ");
                if (preset.getPreset() == 8)
                {
                    preset = new Scraper(registry, testObserver, 2, speed,colorLed);
                    service.execute(preset);
                } else
                {
                    preset.setPreset(2);
                    service.execute(preset);
                }
            }
        });
        btn_preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: genaral pattern 3 ");
                if (preset.getPreset() == 8)
                {
                    preset = new Scraper(registry, testObserver, 3, speed,colorLed);
                    service.execute(preset);
                } else
                {
                    preset.setPreset(3);
                    service.execute(preset);
                }

            }
        });
        btn_preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_preset7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //esste es el boton que quiero que haga el stop
        btn_preset8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: has apretat el preset 8");
                if (preset.getPreset() == 8)
                {
                    preset = new Scraper(registry, testObserver, 8,speed,colorLed);
                    service.execute(preset);
                } else
                {
                    preset.setPreset(8);
                    service.execute(preset);
                }

            }
        });
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        preset = new Scraper(registry, testObserver, 8,speed,colorLed);
        service.execute(preset);
    }
}
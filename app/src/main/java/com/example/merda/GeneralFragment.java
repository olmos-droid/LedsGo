package com.example.merda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rtugeek.android.colorseekbar.ColorSeekBar;


public class GeneralFragment extends Fragment {
    public static final String TAG = "GeneralFragment";
    private ColorSeekBar colorSeekBar;
    private TextView textView;
    byte[] colorRGB = {0, 0, 0};
//    private Scraper preset;
    private int speed = 10;
    com.example.ledsgo.ColorLed colorLed = new com.example.ledsgo.ColorLed();
//    ExecutorService service;
//    private final DeviceRegistry registry;
//    private final TestObserver testObserver;
    private Button button;
    private View view;


    public GeneralFragment() {
//        service = Executors.newFixedThreadPool(1);
//        registry = new DeviceRegistry();
//        testObserver = new TestObserver();
//
//        registry.addObserver(testObserver);
//
//        registry.setExtraDelay(0);
//        registry.setAutoThrottle(true);
//        registry.setAntiLog(true);
//        registry.startPushing();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        preset = new Scraper(registry, testObserver, 0, speed, colorLed);
//        service.execute(preset);


        view = inflater.inflate(R.layout.fragment_general, container, false);
        return view;


//        colorSeekBar = view.findViewById(R.id.color_seek_bar);
//
//       // button=view.findViewById(R.id.button3);
//
//
//        Button btn_preset1 = view.findViewById(R.id.button_general_patertn1);
//        Button btn_preset2 = view.findViewById(R.id.button_general_patertn2);
//        Button btn_preset3 = view.findViewById(R.id.button_general_patertn3);
//        Button btn_preset4 = view.findViewById(R.id.button_general_patertn4);
//        Button btn_preset5 = view.findViewById(R.id.button_general_patertn5);
//        Button btn_preset6 = view.findViewById(R.id.button_general_patertn6);
//        Button btn_preset7 = view.findViewById(R.id.button_general_patertn7);
//        Button btn_preset8 = view.findViewById(R.id.button_general_patertn8);
//
//        textView = view.findViewById(R.id.textView_color);
//
//        //todo intentarpasar las cosas a HSL  mirarse el color util de android developers
//        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
//            @Override
//            public void
//            onColorChangeListener(int i, int i1, int i2) {
//                Color color = null;
//
//                int intColor = colorSeekBar.getColor();
//                int intIntensidad = colorSeekBar.getAlphaValue();
////                Log.d(TAG, "onColorChangeListener: intensidad " + intIntensidad);
//                int intIntensidadBarPosaition = colorSeekBar.getAlphaBarPosition();
////                Log.d(TAG, "onColorChangeListener: bar position " + intIntensidadBarPosaition);
//                int intIntensidadAlphavalue = colorSeekBar.getAlphaValue();
////                Log.d(TAG, "onColorChangeListener: intensidadAlphavalue" + intIntensidadAlphavalue);
////                Log.d(TAG, "onColorChangeListener: " + intColor);
////
////                Log.d(TAG, "onColorChangeListener: red " + color.red(intColor));
////                Log.d(TAG, "onColorChangeListener: gree " + color.green(intColor));
////                Log.d(TAG, "onColorChangeListener: blue  " + color.blue(intColor));
//
//                //importante los de tipo byte no coincide con los tipo int , es decir un int red = 250 no es lo mismo que un byte = 255
//
//
//                colorLed.setRed((byte) (color.red(intColor) * intIntensidad / 255));
//                colorLed.setGreen((byte) (color.green(intColor) * intIntensidad / 255));
//                colorLed.setBlue((byte) (color.blue(intColor) * intIntensidad / 255));
//
//
//            }
//        });
//
//
//
//        btn_preset1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: has apretat el preset 1");
//                preset.setStoplighting(false);
//                if (preset.getPreset() != 1) {
//
//                    preset.setPreset(1);
//
//                }
//            }
//
//        });
//        btn_preset2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: genaral pattern 2 ");
//                preset.setStoplighting(false);
//                if (preset.getPreset() != 2) {
//
//                    preset.setPreset(2);
//                }
//
//            }
//        });
//        btn_preset3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn_preset4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn_preset5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn_preset6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btn_preset7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        //esste es el boton que quiero que haga el stop
//        btn_preset8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d(TAG, "onClick: has apretat el preset 8");
//                if (preset.getPreset() != 8) {
//                    preset.setPreset(8);
//                }
//
//
//            }
//        });
//        return view;
    }



//    @Override
//    public void onStop() {
//        super.onStop();
//        preset = new Scraper(registry, testObserver, 8,speed,colorLed);
//        service.execute(preset);
//    }
}
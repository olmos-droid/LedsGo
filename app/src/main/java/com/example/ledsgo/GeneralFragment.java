package com.example.ledsgo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.heroicrobot.dropbit.registry.DeviceRegistry;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.util.concurrent.ExecutorService;

/**
 *
 */
public class GeneralFragment extends Fragment {
    public static final String TAG = "GeneralFragment";
    private ColorSeekBar colorSeekBar;
    private TextView textView;
    byte[] colorRGB = {0, 0, 0};
    private Scraper preset;

    private ColorLed colorLed = new ColorLed();
    private SeekBar speed;


    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ExecutorService service;

//    private Scraper scraperStrip1;
//    private Scraper scraperStrip2;
//    private Scraper scraperStrip3;
//    private Scraper scraperStrip4;
//    private Scraper scraperStrip5;
//    private Scraper scraperStrip6;
//    private Scraper scraperStrip7;
//    private Scraper scraperStrip8;


    /**
     *
     */
    public GeneralFragment(DeviceRegistry registry, TestObserver testObserver, ExecutorService service) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.service = service;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_general, container, false);
        speed = view.findViewById(R.id.seekBarSpeed);
        preset = new Scraper(registry, testObserver, 0, speed, colorLed, 0);
        service.execute(preset);

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


        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               preset.setSpeed(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //todo intentarpasar las cosas a HSL  mirarse el color util de android developers
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            /**
             *
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void
            onColorChangeListener(int i, int i1, int i2) {
                Color color = null;

                int intColor = colorSeekBar.getColor();
                int intIntensidad = colorSeekBar.getAlphaValue();

                int intIntensidadBarPosaition = colorSeekBar.getAlphaBarPosition();
                int intIntensidadAlphavalue = colorSeekBar.getAlphaValue();

                colorLed.setRed((byte) (color.red(intColor) * intIntensidad / 255));
                colorLed.setGreen((byte) (color.green(intColor) * intIntensidad / 255));
                colorLed.setBlue((byte) (color.blue(intColor) * intIntensidad / 255));
            }
        });


        btn_preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preset.setStopLighting(false);
                isThisPreset(1);
            }

        });
        btn_preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(2);
            }
        });
        btn_preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(3);
            }
        });
        btn_preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(4);
            }
        });
        btn_preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(5);

            }
        });
        btn_preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(6);

            }
        });
        btn_preset7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(7);
            }
        });

        btn_preset8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(8);
            }
        });
        return view;
    }

    /**
     * @param preset
     */
    private void isThisPreset(int preset) {
        this.preset.setStopLighting(false);

        if (this.preset.getPreset() != preset) {
            this.preset.setPreset(preset);
        }
    }


}
package com.example.ledsgo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
    private Scraper strip0;
    private Scraper strip1;

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
        strip0 = new Scraper(registry, testObserver, 0, speed, colorLed, 0);
        service.execute(strip0);
        strip1 = new Scraper(registry, testObserver, 0, speed, colorLed, 1);
        service.execute(strip1);

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
               strip0.setSpeed(progress);
               strip1.setSpeed(progress);

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
                strip0.setStopLighting(false);
                strip1.setStopLighting(false);
                isThisPreset(1,strip0);
                isThisPreset(1,strip1);
            }

        });
        btn_preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(2,strip0);
                isThisPreset(2,strip1);
            }
        });
        btn_preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(3,strip0);
                isThisPreset(3,strip1);
            }
        });
        btn_preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(4,strip0);
                isThisPreset(4,strip1);
            }
        });
        btn_preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(5,strip0);
                isThisPreset(5,strip1);

            }
        });
        btn_preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(6,strip0);
                isThisPreset(6,strip1);


            }
        });
        btn_preset7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(7,strip0);
                isThisPreset(7,strip1);
            }
        });

        btn_preset8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(8,strip0);
                isThisPreset(8,strip1);
            }
        });
        return view;
    }

    /**
     * @param preset
     * @param strip
     */
    private void isThisPreset(int preset, Scraper strip) {
        strip.setStopLighting(false);

        if (strip.getPreset() != preset) {
            strip.setPreset(preset);
        }
    }


}
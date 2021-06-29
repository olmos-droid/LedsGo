package com.example.myapplication30;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.heroicrobot.dropbit.registry.DeviceRegistry;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.util.concurrent.ExecutorService;


public class GroupsFragment extends Fragment {
    public static ColorSeekBar colorSeekBar;
    public static TextView textView;
    byte[] colorRGB = {0, 0, 0};
    public static Scraper strip0;
    public static Scraper strip1;
    public static Scraper strip2;
    public static   Scraper strip3;
    public static    Scraper strip4;
    public static    Scraper strip5;
    public static    Scraper strip6;
    public static    Scraper strip7;

    public static ColorLed colorLed = new ColorLed();
    public static SeekBar speed;


    public static DeviceRegistry registry;
    public static TestObserver testObserver;
    public static ExecutorService service;
    private DatabaseReference mDatabase;


    public GroupsFragment(DeviceRegistry registry, TestObserver testObserver,ExecutorService service) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.service = service;
    }

    /**
     *
      * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
// Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        speed = view.findViewById(R.id.seekBar_groups);
        strip0 = new Scraper(registry, testObserver, 0, speed, colorLed, 0);
        service.execute(strip0);
        strip1 = new Scraper(registry, testObserver, 0, speed, colorLed, 1);
        service.execute(strip1);
        strip2 = new Scraper(registry, testObserver, 0, speed, colorLed, 2);
        service.execute(strip2);
        strip3 = new Scraper(registry, testObserver, 0, speed, colorLed, 3);
        service.execute(strip3);
        strip4 = new Scraper(registry, testObserver, 0, speed, colorLed, 4);
        service.execute(strip4);
        strip5 = new Scraper(registry, testObserver, 0, speed, colorLed, 5);
        service.execute(strip5);
        strip6 = new Scraper(registry, testObserver, 0, speed, colorLed, 6);
        service.execute(strip6);
        strip7 = new Scraper(registry, testObserver, 0, speed, colorLed, 7);
        service.execute(strip7);

        stop();

        colorSeekBar = view.findViewById(R.id.color_seek_bar_groups);


        Button btn_preset1 = view.findViewById(R.id.button_group_patertn1);
        Button btn_preset2 = view.findViewById(R.id.button_group_patertn2);
        Button btn_preset3 = view.findViewById(R.id.button_group_patertn3);
        Button btn_preset4 = view.findViewById(R.id.button_group_patertn4);
        Button btn_preset5 = view.findViewById(R.id.button_group_patertn5);
        Button btn_preset6 = view.findViewById(R.id.button_group_patertn6);
        Button btn_preset7 = view.findViewById(R.id.button_group_patertn7);
        Button btn_preset8 = view.findViewById(R.id.button_group_patertn8);



        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                strip0.setSpeed(progress);
                strip1.setSpeed(progress);
                strip2.setSpeed(progress);
                strip3.setSpeed(progress);
                strip4.setSpeed(progress);
                strip5.setSpeed(progress);
                strip6.setSpeed(progress);
                strip7.setSpeed(progress);

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

                isThisPreset(1, strip0);
                isThisPreset(1, strip1);

                isThisPreset(1, strip2);

                isThisPreset(1, strip3);

                isThisPreset(1, strip4);

                isThisPreset(1, strip5);

                isThisPreset(1, strip6);

                isThisPreset(1, strip7);
                strip0.setStopLighting(false);
                strip1.setStopLighting(false);
                strip2.setStopLighting(false);
                strip3.setStopLighting(false);
                strip4.setStopLighting(false);
                strip5.setStopLighting(false);
                strip6.setStopLighting(false);
                strip7.setStopLighting(false);

            }

        });
        btn_preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                strip0.setStopLighting(false);
//                strip1.setStopLighting(false);
                strip0.setStopLighting(false);
                strip1.setStopLighting(false);
                strip2.setStopLighting(false);
                strip3.setStopLighting(false);
                strip4.setStopLighting(false);
                strip5.setStopLighting(false);
                strip6.setStopLighting(false);
                strip7.setStopLighting(false);
                isThisPreset(2, strip0);
                isThisPreset(2, strip1);
                isThisPreset(2, strip2);
                isThisPreset(2, strip3);
                isThisPreset(2, strip4);
                isThisPreset(2, strip5);
                isThisPreset(2, strip6);
                isThisPreset(2, strip7);
            }
        });
        btn_preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                strip0.setStopLighting(false);
//                strip1.setStopLighting(false);
                strip0.setStopLighting(false);
                strip1.setStopLighting(false);
                strip2.setStopLighting(false);
                strip3.setStopLighting(false);
                strip4.setStopLighting(false);
                strip5.setStopLighting(false);
                strip6.setStopLighting(false);
                strip7.setStopLighting(false);
                isThisPreset(3, strip0);
                isThisPreset(3, strip1);
                isThisPreset(3, strip2);
                isThisPreset(3, strip3);
                isThisPreset(3, strip4);
                isThisPreset(3, strip5);
                isThisPreset(3, strip6);
                isThisPreset(3, strip7);
            }
        });
        btn_preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(4, strip0);
                isThisPreset(4, strip1);
                isThisPreset(4, strip2);
                isThisPreset(4, strip3);
                isThisPreset(4, strip4);
                isThisPreset(4, strip5);
                isThisPreset(4, strip6);
                isThisPreset(4, strip7);
            }
        });
        btn_preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(5, strip0);
                isThisPreset(5, strip1);
                isThisPreset(5, strip2);
                isThisPreset(5, strip3);
                isThisPreset(5, strip4);
                isThisPreset(5, strip5);
                isThisPreset(5, strip6);
                isThisPreset(5, strip7);
            }
        });
        btn_preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(6, strip0);
                isThisPreset(6, strip1);
                isThisPreset(6, strip2);
                isThisPreset(6, strip3);
                isThisPreset(6, strip4);
                isThisPreset(6, strip5);
                isThisPreset(6, strip6);
                isThisPreset(6, strip7);


            }
        });
        btn_preset7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(7, strip0);
                isThisPreset(7, strip1);
                isThisPreset(7, strip2);
                isThisPreset(7, strip3);
                isThisPreset(7, strip4);
                isThisPreset(7, strip5);
                isThisPreset(7, strip6);
                isThisPreset(7, strip7);
            }
        });

        btn_preset8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThisPreset(8, strip0);
                isThisPreset(8, strip1);
                isThisPreset(8, strip2);
                isThisPreset(8, strip3);
                isThisPreset(8, strip4);
                isThisPreset(8, strip5);
                isThisPreset(8, strip6);
                isThisPreset(8, strip7);
            }
        });
        return view;
    }
    private void isThisPreset(int preset, Scraper strip) {
        strip.setStopLighting(false);

        if (strip.getPreset() != preset) {
            strip.setPreset(preset);
        }
    }
    public void stop(){
        ColorLed colorLed=new ColorLed();
        colorLed.setBlue((byte) 0);
        colorLed.setGreen((byte) 0);
        colorLed.setRed((byte) 0);
        strip0.setColorLed(colorLed);
        strip1.setColorLed(colorLed);
        strip2.setColorLed(colorLed);
        strip3.setColorLed(colorLed);
        strip4.setColorLed(colorLed);
        strip5.setColorLed(colorLed);
        strip6.setColorLed(colorLed);
        strip7.setColorLed(colorLed);



    }
}
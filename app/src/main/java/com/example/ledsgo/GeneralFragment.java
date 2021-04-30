package com.example.ledsgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.util.List;


public class GeneralFragment extends Fragment {
    public static final String TAG = "GeneralFragment";
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorSeekBar colorSeekBar;


    public GeneralFragment(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;
        List<Strip> strips = registry.getStrips();


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        List<Strip> strips = registry.getStrips();
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        colorSeekBar = view.findViewById(R.id.colorSlider_genereal);
        Button btn_preset1 = view.findViewById(R.id.button_general_patertn1);
        Button btn_preset2 = view.findViewById(R.id.button_general_patertn2);
        Button btn_preset3 = view.findViewById(R.id.button_general_patertn3);
        Button btn_preset4 = view.findViewById(R.id.button_general_patertn4);
        Button btn_preset5 = view.findViewById(R.id.button_general_patertn5);
        Button btn_preset6 = view.findViewById(R.id.button_general_patertn6);
        Button btn_preset7 = view.findViewById(R.id.button_general_patertn7);
        Button btn_preset8 = view.findViewById(R.id.button_general_patertn8);

        Log.d(TAG, "onCreateView: " + colorSeekBar.getColors());

        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                Log.d(TAG, "onCreateView: " + colorSeekBar.getColors());
            }
        });


        btn_preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scraper scraper = new Scraper(0,registry,testObserver);

            }

        });
        btn_preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        btn_preset8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
package com.example.ledsgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.concurrent.ExecutorService;


public class GeneralFragment extends Fragment {
    public static final String TAG = "GeneralFragment";
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ExecutorService service;

    public GeneralFragment(DeviceRegistry registry, TestObserver testObserver,ExecutorService service) {
        this.service = service;
        this.registry = registry;
        this.testObserver = testObserver;

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_general, container, false);
        Button btn_preset1 = view.findViewById(R.id.button_general_patertn1);
        btn_preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                service.execute(new Scraper(0, registry, testObserver));

            }
        });


        return view;


    }
}
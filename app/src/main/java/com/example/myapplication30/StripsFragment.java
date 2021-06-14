package com.example.myapplication30;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.concurrent.ExecutorService;


public class StripsFragment extends Fragment {
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ExecutorService service;

    /**
     * Required empty public constructor
     */


    public StripsFragment(DeviceRegistry registry, TestObserver testObserver,ExecutorService service) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.service = service;
    }

    /**
     * Required empty public constructor
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_strips, container, false);
    }
}
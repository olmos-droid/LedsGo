package com.example.ledsgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.concurrent.ExecutorService;


public class GroupsFragment extends Fragment {
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ExecutorService service;


    public GroupsFragment(DeviceRegistry registry, TestObserver testObserver,ExecutorService service) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.service = service;
    }




    /**
     * Required empty public constructor
     *
     */
    public GroupsFragment() {
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
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }
}
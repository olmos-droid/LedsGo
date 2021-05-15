package com.example.ledsgo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StripsFragment extends Fragment {
    /**
     * Required empty public constructor
     */
    public StripsFragment() {
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
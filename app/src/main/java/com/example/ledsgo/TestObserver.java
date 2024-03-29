package com.example.ledsgo;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {
    private static final String TAG = "TAG";

    public boolean hasStrips = false;

    @Override
    public void update(Observable registry, Object updatedDevice) {
        Log.d(TAG, "update: Registry changed!");
        if (updatedDevice != null)
        {
            Log.d(TAG, "update: Device change: " + updatedDevice);
        }
        this.hasStrips = true;
    }

}

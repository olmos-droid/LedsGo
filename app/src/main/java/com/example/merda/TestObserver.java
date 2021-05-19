package com.example.merda;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {
    private static final String TAG = "TAG";

    public boolean isHasStrips() {
        return hasStrips;
    }

    private boolean hasStrips = false;

    @Override
    public void update(Observable registry, Object updatedDevice) {
//        Log.d(TAG, "update: Registry changed!");
        if (updatedDevice != null)
        {
            Log.d(TAG, "update: Device change: " + updatedDevice);
        }
        this.hasStrips = true;
    }

}

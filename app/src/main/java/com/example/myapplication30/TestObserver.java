package com.example.myapplication30;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class TestObserver implements Observer {
    private static final String TAG = "TAG";
    public boolean hasStrips = false;

    /**
     * @param registry
     * @param updatedDevice
     */
    @Override
    public void update(Observable registry, Object updatedDevice) {
        //Log.d(TAG, "update: Registry changed!");
        if (updatedDevice != null)
        {
            Log.d(TAG, "update: Device change: " + updatedDevice);
        }
        this.hasStrips = true;
    }

    /**
     *
     * @return si el pixer pusher tiene "tiras de leds" --> Strips
     */
    public boolean isHasStrips() {
        return hasStrips;
    }

    /**
     *
     * @param hasStrips
     */
    public void setHasStrips(boolean hasStrips) {
        this.hasStrips = hasStrips;
    }
}

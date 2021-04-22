package com.example.ledsgo;

import android.util.Log;


import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class ConnectPP implements Runnable{
    private static final String TAG ="TAG" ;

    DeviceRegistry registry;
    TestObserver testObserver;


    public ConnectPP(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;
        Log.d(TAG, "ConnectPP: Conexio creada");
    }

    @Override
    public void run() {
        Log.d(TAG, "run: dins run");
        registry = new DeviceRegistry();
        testObserver = new TestObserver();
        registry.addObserver(testObserver);
    }
}

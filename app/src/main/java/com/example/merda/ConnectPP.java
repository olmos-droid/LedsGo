package com.example.merda;


import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class ConnectPP implements Runnable {
    private static final String TAG = "ConnectPP";

    private DeviceRegistry registry;
    private TestObserver testObserver;


    public ConnectPP(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;

    }

    public static String getTAG() {
        return TAG;
    }

    public DeviceRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(DeviceRegistry registry) {
        this.registry = registry;
    }

    public TestObserver getTestObserver() {
        return testObserver;
    }

    public void setTestObserver(TestObserver testObserver) {
        this.testObserver = testObserver;
    }

    @Override
    public void run() {
        registry = new DeviceRegistry();
        testObserver = new TestObserver();
        registry.addObserver(testObserver);

    }
}

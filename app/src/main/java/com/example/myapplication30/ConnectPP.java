package com.example.myapplication30;


import com.heroicrobot.dropbit.registry.DeviceRegistry;

public class ConnectPP implements Runnable {
    private static final String TAG = "ConnectPP";

    private DeviceRegistry registry;
    private TestObserver testObserver;

    /**
     * @param registry
     * @param testObserver
     */
    public ConnectPP(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;

    }

    /**
     * @return
     */
    public static String getTAG() {
        return TAG;
    }

    /**
     * @return
     */
    public DeviceRegistry getRegistry() {
        return registry;
    }

    /**
     * @param registry
     */
    public void setRegistry(DeviceRegistry registry) {
        this.registry = registry;
    }

    /**
     * @return
     */
    public TestObserver getTestObserver() {
        return testObserver;
    }

    /**
     * @param testObserver
     */
    public void setTestObserver(TestObserver testObserver) {
        this.testObserver = testObserver;
    }

    /**
     *
     */
    @Override
    public void run() {
        registry = new DeviceRegistry();
        testObserver = new TestObserver();
        registry.addObserver(testObserver);

    }
}

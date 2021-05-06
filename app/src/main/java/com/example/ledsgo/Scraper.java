package com.example.ledsgo;


import android.util.Log;


import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "Scraper";



    private int nStrip;
    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorByte colorByte;
    int preset;
    int speed;
    private volatile Thread blinker = null;
    Thread thisThread;


    public Scraper(int nStrip, DeviceRegistry registry, TestObserver testObserver) {
        this.nStrip = nStrip;
        this.registry = registry;
        this.testObserver = testObserver;
    }

    public Scraper(DeviceRegistry registry, TestObserver testObserver) {
        this.registry = registry;
        this.testObserver = testObserver;
    }

    public Scraper(DeviceRegistry registry, TestObserver testObserver, int preset) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.preset = preset;
        thisThread = Thread.currentThread();

    }

    public Scraper(int nStrip, DeviceRegistry registry, TestObserver testObserver, ColorByte colorByte) {
        this.colorByte = colorByte;
        this.nStrip = nStrip;
        this.registry = registry;
        this.testObserver = testObserver;
    }

    @Override
    public void run() {


        blinker =thisThread;
        while (!registry.hasChanged() && blinker == thisThread)
        {


            List<PixelPusher> pushers = registry.getPushers(0);
            List<Strip> strips = pushers.get(0).getStrips();
            prepareExitHandler(registry);

            switch (preset)
            {
                case 1:
                    pattern1(strips);
                    break;
                case 2:
                    pattern2(strips);
                    break;
                case 8:
                    pattern8(strips);
                    break;
            }
        }
    }

    public void stop() {
        blinker = null;
    }

    public static String getTAG() {
        return TAG;
    }


    public int getnStrip() {
        return nStrip;
    }

    public void setnStrip(int nStrip) {
        this.nStrip = nStrip;
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

    public ColorByte getColorByte() {
        return colorByte;
    }

    public void setColorByte(ColorByte colorByte) {
        this.colorByte = colorByte;
    }

    public int getPreset() {
        return preset;
    }

    public void setPreset(int preset) {
        this.preset = preset;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void pattern8(List<Strip> strips) {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)

            {
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);

            }
        }


    }


    void pattern2(List<Strip> strips) {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 24; j++)
            {
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 255, (byte) 255), j);
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    private void pattern1(List<Strip> strips) {
//        for (int i = 0; i < strips.size(); i++)
        for (int i = 0; i < 2; i++)

//        {
            for (int j = 0; j < 24; j++)
            {
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 255), j);
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
            }
    }
//    }


    private void prepareExitHandler(DeviceRegistry registry) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {

                Log.d(TAG, "run: Shutdown hook running");

                List<Strip> strips = registry.getStrips();
//                for (Strip strip : strips)

//                {
                for (int i = 0; i < 1; i++)
                {
                    strips.get(i).setPixel(0, i);
                }
            }

//            }
        }
        ));
    }
}

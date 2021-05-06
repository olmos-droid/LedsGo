package com.example.ledsgo;


import android.util.Log;


import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "Scraper";

    int nStrip;
    DeviceRegistry registry;
    TestObserver testObserver;
    ColorByte colorByte;
    int preset;
    int speed;


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
    }

    public Scraper(int nStrip, DeviceRegistry registry, TestObserver testObserver, ColorByte colorByte) {
        this.colorByte = colorByte;
        this.nStrip = nStrip;
        this.registry = registry;
        this.testObserver = testObserver;
    }

    @Override
    public void run() {
        while (!registry.hasChanged())
        {
            registry.setExtraDelay(0);
            registry.setAutoThrottle(true);
            registry.startPushing();
            List<PixelPusher> pushers = registry.getPushers(0);
            List<Strip> strips = pushers.get(0).getStrips();
            prepareExitHandler(registry);
            Log.d(TAG, "run:================================" + strips.get(0).getLength());

            switch (preset)
            {
                case 1:

                    allLeds(strips);
                    break;
                case 2:
                    ratita(strips);
                    break;


            }
        }
    }


    void allLeds(List<Strip> strips) {
        for (int i = 0; i < strips.size(); i++)
        {
            for (int j = 0; j < 24; j++)
            {
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 255, (byte) 255), j);
                try
                {
                    Thread.sleep(speed);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    private void ratita(List<Strip> strips) {
        for (int i = 0; i < strips.size(); i++)
        {
            for (int j = 0; j < 24; j++)
            {
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 255), j);
                try
                {
                    Thread.sleep(speed);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
            }
        }
    }

    private void prepareExitHandler(DeviceRegistry registry) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {

                Log.d(TAG, "run: Shutdown hook running");

                List<Strip> strips = registry.getStrips();
                for (Strip strip : strips)
                {
                    for (int i = 0; i < strip.getLength(); i++)
                    {
                        strip.setPixel(0, i);
                    }
                }

            }
        }
        ));
    }
}

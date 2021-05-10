package com.example.merda;
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
    boolean stopthread;


//    public Scraper(int nStrip, DeviceRegistry registry, TestObserver testObserver) {
//        this.nStrip = nStrip;
//        this.registry = registry;
//        this.testObserver = testObserver;
//    }


    public Scraper(int nStrip, DeviceRegistry registry, TestObserver testObserver, boolean stopthread) {
        this.nStrip = nStrip;
        this.registry = registry;
        this.testObserver = testObserver;
        this.stopthread = stopthread;
    }

    @Override
    public void run() {
        while (!registry.hasChanged() || stopthread)
        {
            registry.setExtraDelay(0);
            registry.setAutoThrottle(true);
            registry.startPushing();
            List<PixelPusher> pushers = registry.getPushers(0);
            List<Strip> strips = pushers.get(0).getStrips();
            prepareExitHandler(registry);
            Log.d(TAG, "run:================================" + strips.get(0).getLength());

//            ratita_AllLeds(strips);
            ratita(strips);
//            allLeds(strips);

        }
    }

    private void ratita_AllLeds(List<Strip> strips) {
        ratita(strips);
        allLeds(strips);

    }

    private void allLeds(List<Strip> strips) {
        for (int j = 0; j < 24; j++)
        {
            strips.get(this.nStrip).setPixel(new Pixel((byte) 0, (byte) 255, (byte) 255), j);
            try
            {
                Thread.sleep(0);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    private void ratita(List<Strip> strips) {
        for (int j = 0; j < 24; j++)
        {
            strips.get(this.nStrip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            strips.get(this.nStrip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
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

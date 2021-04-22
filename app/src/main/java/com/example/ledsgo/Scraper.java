package com.example.ledsgo;

import android.util.Log;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "scraper";
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

    public boolean isStopthread() {
        return stopthread;
    }

    public void setStopthread(boolean stopthread) {
        this.stopthread = stopthread;
    }

    @Override
    public void run() {
//        while (!registry.hasChanged() && !isStopthread())
        while (!registry.hasChanged())
        {
            registry.setExtraDelay(0);
            registry.setAutoThrottle(true);
            registry.startPushing();
            List<PixelPusher> pushers = registry.getPushers(0);
            List<Strip> strips = pushers.get(0).getStrips();
            prepareExitHandler(registry);


                for (int j = 0; j < 24; j++)
                {
                    strips.get(this.nStrip).setPixel(new Pixel((byte) 0, (byte) 255, (byte) 0), j);
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

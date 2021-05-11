package com.example.ledsgo;


import android.util.Log;

import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "Scraper";

    private volatile boolean done = false;


    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorLed colorLed;
    private int preset;
    private int speed;




    public Scraper(DeviceRegistry registry, TestObserver testObserver, int preset, int speed,ColorLed colorLed) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.preset = preset;
        this.colorLed = colorLed;


    }



    public Scraper(DeviceRegistry registry, TestObserver testObserver, ColorLed colorLed) {
        this.colorLed = colorLed;
        this.registry = registry;
        this.testObserver = testObserver;
    }

    @Override
    public void run() {

        // todo yo intentaria meterlo en el contrsuctor y cuando el PP estuviera conectado(testobserver.hastrip)
        List<PixelPusher> pushers = registry.getPushers(0);
        List<Strip> strips = pushers.get(0).getStrips();


        while (!done)

        {
            switch (preset)
            {

                case 1:
                    pattern1(strips);
                    break;
                case 2:
                    pattern2(strips);
                    break;
                case 3:
                    pattern3(strips);
                    break;
                case 8:

                    //intento cambiar a true para que el while pare pero sigue
                    pattern8(strips);
                    break;

                default:
                    break;
            }

        }
    }


    //  este es el patron que quiero que haga el stop

    void pattern8(List<Strip> strips) {

        this.setDone(true);

        for (int i = 0; i < strips.size(); i++)

            for (int j = 0; j < 24; j++)
            {
                try
                {
                    Thread.sleep(this.getSpeed());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                strips.get(i).setPixel(0, j);

            }

    }


    /**
     * @param strips Pinta un pixel i luego lo borra, hace un efecto "ratita"
     */
    void pattern1(List<Strip> strips) {
        this.setDone(false);

        for (int i = 0; i < 2; i++)

            for (int j = 0; j < 24; j++)
            {
                try
                {
                    strips.get(i).setPixel(new Pixel(colorLed.getRed(),colorLed.getGreen(),colorLed.getBlue()), j);
                    Thread.sleep(50);
                    strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);


                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
    }


    //pinta un pixel de la tira en rojo "ratita" al reves del pattern1
    void pattern3(List<Strip> strips) {

    }

    //    }

    //pinta de azul
    void pattern2(List<Strip> strips) {



        this.setDone(false);

        for (int i = 0; i < strips.size(); i++)
        {
            for (int j = 0; j < 24; j++)
            {

                strips.get(i).setPixel(new Pixel(colorLed.getRed(),colorLed.getGreen(),colorLed.getBlue()), j);

//                try
//                {
//                    Thread.sleep(this.getSpeed());
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }

            }
        }
    }


    public ColorLed getColorLed() {
        return colorLed;
    }

    public void setColorLed(ColorLed colorLed) {
        this.colorLed = colorLed;
    }


    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
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

    public ColorLed getColorByte() {
        return colorLed;
    }

    public void setColorByte(ColorLed colorLed) {
        this.colorLed = colorLed;
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

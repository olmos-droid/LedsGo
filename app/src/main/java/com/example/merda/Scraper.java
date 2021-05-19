package com.example.merda;


import android.util.Log;

import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "Scraper";

    private volatile boolean stoplighting = false;


    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorLed colorLed;
    private int preset;
    private int speed;




    public Scraper(DeviceRegistry registry, TestObserver testObserver, int preset, int speed, ColorLed colorLed) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.preset = preset;
        this.colorLed = colorLed;
        this.speed = speed;
    }

    @Override
    public void run() {
while(true) {
    // todo yo intentaria meterlo en el contrsuctor y cuando el PP estuviera conectado(testobserver.hastrip)
    if (testObserver.isHasStrips()) {
        List<PixelPusher> pushers = registry.getPushers(0);
        List<Strip> strips = pushers.get(0).getStrips();
        int frameLimit= registry.getFrameLimit();
        Log.d(TAG, "Scraper: frameLimit "+frameLimit);
        if (!stoplighting) {
            switch (preset) {

                case 1:
                    pattern1(strips);
                    break;
                case 2:
//                    registry.setFrameLimit(speed);
                    pattern2(strips);
                    break;
                case 3:
//                    registry.setFrameLimit(speed);
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
    } else {
        Log.d(TAG, "Scraper: no strips ");
        Log.d(TAG, registry.toString());
    }
}
    }


    //  este es el patron que quiero que haga el stop

    void pattern8(List<Strip> strips) {
        Log.d(TAG, "Scraper: pattern8 ");

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

        this.setStoplighting(true);
    }


    /**
     * @param strips Pinta un pixel i luego lo borra, hace un efecto "ratita"
     */
    void pattern1(List<Strip> strips) {
        Log.d(TAG, "Scraper: pattern1 ");

        for (int i = 0; i < 2; i++)

            for (int j = 0; j < 24; j++)
            {
                try
                {
                    strips.get(i).setPixel(new Pixel(colorLed.getRed(),colorLed.getGreen(),colorLed.getBlue()), j);
                    Thread.sleep(speed*3);
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
        Log.d(TAG, "Scraper: pattern2 ");

        for (int i = 0; i < strips.size(); i++)

            for (int j = 0; j < 24; j++) {

                strips.get(i).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);

                try {
                    Thread.sleep(this.getSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
    }


    public ColorLed getColorLed() {
        return colorLed;
    }

    public void setColorLed(ColorLed colorLed) {
        this.colorLed = colorLed;
    }


    public void setStoplighting(boolean stoplighting) {
        this.stoplighting = stoplighting;
    }

    public boolean isStoplighting() {
        return stoplighting;
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


}

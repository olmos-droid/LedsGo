package com.example.ledsgo;


import android.util.Log;
import android.widget.SeekBar;

import com.heroicrobot.dropbit.devices.pixelpusher.Pixel;
import com.heroicrobot.dropbit.devices.pixelpusher.PixelPusher;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;

public class Scraper implements Runnable {
    public static final String TAG = "Scraper";


    private volatile boolean stopLighting = false;


    private DeviceRegistry registry;
    private TestObserver testObserver;
    private ColorLed colorLed;
    private int preset;
    private SeekBar speed;

    private int strip;


    public Scraper(DeviceRegistry registry, TestObserver testObserver, int preset, SeekBar speed, ColorLed colorLed, int strip) {
        this.registry = registry;
        this.testObserver = testObserver;
        this.preset = preset;
        this.colorLed = colorLed;
        this.speed = speed;
        this.strip = strip;
    }


    public SeekBar getSpeed() {
        return speed;
    }


    @Override
    public void run() {
        while (true) {
            // todo yo intentaria meterlo en el contrsuctor y cuando el PP estuviera conectado(testobserver.hastrip)
            if (testObserver.isHasStrips()) {
                List<PixelPusher> pushers = registry.getPushers(0);
                List<Strip> strips = registry.getStrips();
                int frameLimit = registry.getFrameLimit();


                if (!stopLighting) {
                    switch (preset) {
                        case 1:
                            pattern1(strips);
                            break;
                        case 2:
                            pattern2(strips);
                            break;
                        case 3:
                            pattern3(strips);
                            break;
                        case 4:
                            pattern4(strips);
                            break;
                        case 5:
                            pattern5(strips);
                            break;
                        case 6:
                            pattern6(strips);
                            break;
                        case 7:
                            pattern7(strips);
                            break;
                        case 8:
                            pattern8(strips);
                            break;

                        default:
                            break;
                    }
                }
            } else {
                // eso no puede pasar pq el usuario cuando se logea para que aparezcan los
                // fragments es que hay un pixcel puxer por consiguiente hay strips

                Log.d(TAG, "Scraper: no strips ");
                Log.d(TAG, registry.toString());
            }
        }
    }


    void pattern1(List<Strip> strips) {

        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            try {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                //                Thread.sleep(registry.getFrameLimit());
                Thread.sleep(speed.getProgress());
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j =strips.get(strip).getLength()-1; j>0; j--) {
            try {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                //                Thread.sleep(registry.getFrameLimit());
                Thread.sleep(speed.getProgress());
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param strips all leds turn on
     */
    void pattern2(List<Strip> strips) {

        for (int j = 0; j < strips.get(strip).getLength(); j++) {

            strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
            strips.get(strip);

        }
    }


    /**
     * @param strips
     */
    void pattern3(List<Strip> strips) {
        try {
            for (int j = 0; j < strips.get(strip).getLength(); j++) {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
            }

            Thread.sleep(getSpeed().getProgress()*4);

            for (int j = 0; j < strips.get(strip).getLength(); j++) {
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
            }

            Thread.sleep(getSpeed().getProgress()*4);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    void pattern4(List<Strip> strips) {
        int i = strips.get(strip).getLength() / 2;
        for (int j = strips.get(strip).getLength() / 2 - 1; j < strips.get(strip).getLength(); j++) {
            try {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), i);

                Thread.sleep(speed.getProgress());
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), i);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }

    void pattern5(List<Strip> strips) {
        int point = strips.get(strip).getLength() / 4;
        int i = point;
        for (int j = 0; j < point; j++) {
            try {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), i + point);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j + point * 2);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), i + point * 3 - 1);
                //                Thread.sleep(registry.getFrameLimit());

                Thread.sleep(speed.getProgress());
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), i + point);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j + point * 2);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), i + point * 3 - 1);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
        for (int j = point; j < 0; j--) {
            try {
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j + 1
                );
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), i + point);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j + point * 2);
                strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), i + point * 3 - 1);
                //                Thread.sleep(registry.getFrameLimit());

                Thread.sleep(speed.getProgress());
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j+1);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), i + point);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j + point * 2);
                strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), i + point * 3 - 1);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    void pattern6(List<Strip> strips) {
        int i=strips.get(strip).getLength()-1;
        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            try {
                if(j%2==0) {

                    strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                    //                Thread.sleep(registry.getFrameLimit());
                    Thread.sleep(speed.getProgress());
//                    strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            try {
                if(j%2==0) {

         //           strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                    //                Thread.sleep(registry.getFrameLimit());
                    Thread.sleep(speed.getProgress());
                    strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            try {
                if(j%2!=0) {

                    strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                    //                Thread.sleep(registry.getFrameLimit());
                    Thread.sleep(speed.getProgress());
//                    strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            try {
                if(j%2!=0) {

                    //           strips.get(strip).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                    //                Thread.sleep(registry.getFrameLimit());
                    Thread.sleep(speed.getProgress());
                    strips.get(strip).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void pattern7(List<Strip> strips) {
        for (int i = 0; i < strips.size(); i++)
            for (int j = strips.get(strip).getLength(); j < 0; j--) {
                strips.get(i).setPixel(new Pixel(colorLed.getRed(), colorLed.getGreen(), colorLed.getBlue()), j);
                strips.get(i).setPixel(new Pixel((byte) 0, (byte) 0, (byte) 0), j);
            }
    }

    /**
     * paint all pixels black
     *
     * @param strips
     */
    void pattern8(List<Strip> strips) {


        for (int j = 0; j < strips.get(strip).getLength(); j++) {
            strips.get(strip).setPixel(0, j);
        }

        this.setStopLighting(true);
    }


    public ColorLed getColorLed() {
        return colorLed;
    }

    public void setColorLed(ColorLed colorLed) {
        this.colorLed = colorLed;
    }


    public void setStopLighting(boolean stoplighting) {
        this.stopLighting = stoplighting;
    }

    public boolean isStopLighting() {
        return stopLighting;
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


    public void setSpeed(int progress) {
    }
}

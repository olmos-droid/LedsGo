package com.example.myapplication30;

import android.graphics.Color;

import java.util.Observable;

public class ColorLed {
    private byte red, green, blue;


    /**
     * Contrusctor
     */
    public ColorLed() {
    }

    /**
     * Constructor
     *
     * @param red
     * @param green
     * @param blue
     */
    public ColorLed(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * @return
     */
    public byte getRed() {
        return red;
    }

    /**
     * @param red
     */
    public void setRed(byte red) {
        this.red = red;
    }

    /**
     * @return
     */
    public byte getGreen() {
        return green;
    }

    /**
     * @param green
     */
    public void setGreen(byte green) {
        this.green = green;
    }

    /**
     * @return
     */

    public byte getBlue() {
        return blue;
    }

    /**
     * @param blue
     */

    public void setBlue(byte blue) {
        this.blue = blue;
    }

    /**
     * @return srting
     */
    @Override
    public String toString() {
        return "\nColorLed{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}

package com.example.ledsgo;

import java.util.Observable;

public class ColorByte extends Observable {
    private byte[] color = new byte[2];

    public ColorByte(byte[] color) {
        this.color = color;
    }

    public byte[] getColor() {
        return color;
    }

    public void setColor(byte[] color) {
        this.color = color;
    }
}

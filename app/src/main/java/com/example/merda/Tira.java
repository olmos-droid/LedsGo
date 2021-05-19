package com.example.merda;

public class Tira {
    String numtira, numleds;

    public Tira() {
    }

    public Tira(String numtira, String numleds) {
        this.numtira = numtira;
        this.numleds = numleds;
    }

    public String getNumtira() {
        return numtira;
    }

    public void setNumtira(String numtira) {
        this.numtira = numtira;
    }

    public String getNumleds() {
        return numleds;
    }

    public void setNumleds(String numleds) {
        this.numleds = numleds;
    }

    @Override
    public String toString() {
        return "Tira{" +
                "numtira='" + numtira + '\'' +
                ", numleds='" + numleds + '\'' +
                '}';
    }
}

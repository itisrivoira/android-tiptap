package com.example.studente4c.imggioco;

public class Tap {

    private int valore;
    private long durataTap, momentoTap;

    public int getValore() {
        return valore;
    }

    public long getMomentoTap() {
        return momentoTap;
    }

    public Tap(int valore, long momentoTap) {
        this.valore = valore;
        this.momentoTap = momentoTap;
    }

}

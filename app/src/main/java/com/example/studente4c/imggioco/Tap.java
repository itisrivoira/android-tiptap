package com.example.studente4c.imggioco;

public class Tap {
//singolo valore della sequenza, tempo
    private int valore;
    private long durataTap, momentoTap;

    public int getValore() { //ritrona il valore
        return valore;
    }

    public long getMomentoTap() {
        return momentoTap;
    }

    public Tap(int valore, long momentoTap) { //costruttore, creo il valore e momento tap
        this.valore = valore;
        this.momentoTap = momentoTap;
    }

}

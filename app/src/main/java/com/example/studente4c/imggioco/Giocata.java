package com.example.studente4c.imggioco;

import android.content.Context;
import android.widget.ImageView;
import java.util.ArrayList;

public class Giocata {

    private long start;
    private ArrayList<Tap> seguenza=new ArrayList<>(); //ArrayList per salvare la sequenza
    private MainActivity activity=new MainActivity();

    public ArrayList<Tap> getSeguenza() { //return della sequenza
        return seguenza;
    }

    public Giocata(long start) { //Costruttore
        this.start = start;
    }

    public void aggiungi(Context c, int nImg){ //Aggiunge un elemento alla sequenza
        seguenza.add(new Tap(nImg,System.currentTimeMillis()));
    }

    public long momentoTap(){ // Serve per controllare il tempo
        return seguenza.get(seguenza.size()-1).getMomentoTap()-start;
    }

    @Override
    public String toString() { //Converte la sequenza in una stringa
        super.toString();
        String stringa="";
        int i=0;
        for (Tap x : seguenza){
            i++;
            stringa+=String.valueOf(x.getValore());
            if(i<seguenza.size()) stringa+=", ";
        }
        return stringa;
    }

    public void ripeti(ImageView img1,ImageView img2,ImageView img3,ImageView img4, int attesa){ //fa vedere all'utente la sequenza cliccata
        final int id1 = img1.getId();
        for(Tap t : seguenza){
            switch (t.getValore()){
                case R.id.img1:
                    ripetiImg(img1, attesa);
                    break;

                case R.id.img2:
                    ripetiImg(img2, attesa);
                    break;

                case R.id.img3:
                    ripetiImg(img3, attesa);
                    break;

                case R.id.img4:
                    ripetiImg(img4, attesa);
                    break;
            }
        }
    }

    private void ripetiImg(final ImageView img, int attesa){
        try { //Attesa
            Thread.sleep(attesa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() { // Il suo funzionamento è quello di pressare l'img
            @Override
            public void run() {
                img.setPressed(true);
            }
        });
        try { //attesa
            Thread.sleep(attesa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() { // Il suo funzionamento è quello di non pressare l'img
            @Override
            public void run() {
                img.setPressed(false);
            }
        });
    }


}
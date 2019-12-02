package com.example.studente4c.imggioco;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Giocata {

    private long start;
    private ArrayList<Tap> seguenza=new ArrayList<>();
    private MainActivity activity=new MainActivity();
    private int cont=0;
    private Boolean flag=true;

    public ArrayList<Tap> getSeguenza() {
        return seguenza;
    }

    public Giocata(long start) {
        this.start = start;
    }

    public void aggiungi(Context c, int nImg){
        seguenza.add(new Tap(nImg,System.currentTimeMillis()));
    }

    public long momentoTap(){
        return seguenza.get(seguenza.size()-1).getMomentoTap()-start;
    }

    @Override
    public String toString() {
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

    public void ripeti(ImageView img1,ImageView img2,ImageView img3,ImageView img4, int attesa){
        for(Tap t : seguenza){
            switch (t.getValore()){
                case 1:
                    ripetiImg(img1, attesa);
                    break;

                case 2:
                    ripetiImg(img2, attesa);
                    break;

                case 3:
                    ripetiImg(img3, attesa);
                    break;

                case 4:
                    ripetiImg(img4, attesa);
                    break;
            }
        }
    }

    private void ripetiImg(final ImageView img, int attesa){
        try {
            Thread.sleep(attesa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                img.setPressed(true);
            }
        });
        try {
            Thread.sleep(attesa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                img.setPressed(false);
            }
        });
    }


}
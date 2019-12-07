package com.example.studente4c.imggioco;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img1,img2,img3,img4, imgSetting;
    private Button btnInizia,btnFine, btnRipeti, btnSfida;
    private ArrayList<Giocata> giocate=new ArrayList<>();
    private long start;
    private Boolean iniziasfida=false;
    private int cont=0, indice=0, vite=3;
    private SharedPreferences pref;
    private String stringa; //Stringa per salvare la sequenza

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start= System.currentTimeMillis();
        setContentView(R.layout.activity_main);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        btnRipeti=findViewById(R.id.btnRipeti);
        btnFine=findViewById(R.id.btnFine);
        imgSetting=findViewById(R.id.imgSetting);
        btnInizia=findViewById(R.id.btnInizia);
        btnSfida=findViewById(R.id.btnSfida);
        btnInizia.setOnClickListener(this);
        btnFine.setOnClickListener(this);
        btnRipeti.setOnClickListener(this);
        btnSfida.setOnClickListener(this);
        imgSetting.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        imposta(110,false,true,false, false, false);

    }


    @Override
    protected void onResume() {
        super.onResume();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        vite =  pref.getInt("nVite", 3);
    }

    @Override
    public void onClick(View v) {
        int mioId=v.getId();
        switch (mioId){
            case R.id.btnInizia:
                imposta(255,true,false,true, false, false);
                giocate.add(new Giocata(start));
                break;

            case R.id.img1:
                if(iniziasfida) sfida(1);
                else immagineCLick(1);
                break;

            case R.id.img2:
                if(iniziasfida) sfida(2);
                else immagineCLick(2);
                break;

            case R.id.img3:
                if(iniziasfida) sfida(3);
                else immagineCLick(3);
                break;

            case R.id.img4:
                if(iniziasfida) sfida(4);
                else immagineCLick(4);
                break;

            case R.id.btnFine:
                imposta(110,false,true,false, true, true);
                stringa=giocate.get(indice).toString();
                if(stringa.isEmpty()){
                    Toast.makeText(this,"nessuna giocata",Toast.LENGTH_LONG).show();
                    giocate.remove(indice);
                    if (indice>0) indice--;
                } else{
                    //Toast.makeText(this,stringa,Toast.LENGTH_LONG).show();
                    indice++;
                }
                break;

            case R.id.btnRipeti:
                if(controlloSequenza()) {
                    Thread th = new Thread() {
                        @Override
                        public void run() {
                            imposta(255, false, true, false, true, true);
                            btnInizia.setClickable(false);
                            btnRipeti.setClickable(false);
                            btnSfida.setClickable(false);
                            giocate.get(indice - 1).ripeti(img1, img2, img3, img4, 500);
                            imposta(110, false, true, false, true, true);
                            btnInizia.setClickable(true);
                            btnRipeti.setClickable(true);
                            btnSfida.setClickable(true);
                        }
                    };
                    th.start();
                }
                break;

            case R.id.btnSfida:
                if(controlloSequenza()) {
                    iniziasfida = true;
                    imposta(255, true, false, false, false, false);
                    cont = 0;
                }
                break;

            case R.id.imgSetting:
                Intent avviaSetting= new Intent(this,SettingsActivity.class);
                startActivity(avviaSetting);
                break;

        }
    }



    private void immagineCLick(int num){
        Giocata g=giocate.get(indice);
        g.aggiungi(this,num);
    }

    private void imposta(int trasparenza, boolean clickImg, boolean clickInizia, boolean clickFine, boolean clickRipeti, boolean clickSfida){
        img1.setImageAlpha(trasparenza);
        img1.setClickable(clickImg);
        img2.setImageAlpha(trasparenza);
        img2.setClickable(clickImg);
        img3.setImageAlpha(trasparenza);
        img3.setClickable(clickImg);
        img4.setImageAlpha(trasparenza);
        img4.setClickable(clickImg);
        btnInizia.setEnabled(clickInizia);
        btnFine.setEnabled(clickFine);
        btnRipeti.setEnabled(clickRipeti);
        btnSfida.setEnabled(clickSfida);
    }

    private void sfida(int n){
        if(n!=giocate.get(indice-1).getSeguenza().get(cont).getValore()){
            if(pref.getBoolean("vite",true)) {
                vite--;
                if(vite>=0 && pref.getBoolean("errori",true)) Toast.makeText(this,"ERRORE. "+ vite +" vite",Toast.LENGTH_LONG).show();
            }
        }
        else {
            if (cont==giocate.get(indice - 1).getSeguenza().size()-1){
                vite = pref.getInt("nVite", 3);;
                stampaAlert("WIN","HAI INDOVINATO LA SEGUENZA",R.drawable.v);
            }
            cont++;
        }
        if(vite==-1){
            stampaAlert("GAME OVER","HAI SBAGLIATO LA SEGUENZA",R.drawable.x);
            vite =  pref.getInt("nVite", 3);
        }
    }

    private void stampaAlert(String titolo, String messaggio, int icona){
        AlertDialog.Builder finestra = new AlertDialog.Builder(this);
        finestra.setTitle(titolo);
        finestra.setMessage(messaggio);
        finestra.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cont=0;
                iniziasfida=false;
                imposta(255,false,true,false, true, true);
            }
        });
        finestra.setIcon(icona);
        finestra.create();
        finestra.show();

    }

    private boolean controlloSequenza(){
        if(stringa.isEmpty()){
            Toast.makeText(this, "nessuna sequenza inserita", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

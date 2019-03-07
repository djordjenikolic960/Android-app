package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Meni extends AppCompatActivity {
    private Button test;
    private Button video;
    private Button pogledajvideo;
    private Button obrisivideo;
    private Button azurirajvideo;
    private Button igrica;
    private Button dodajtest;
    private Button lista;
    private Button profil;
    private Button dodajKorisnika;
    private Button obrisiKorisnika;
    private Button arhivirani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meni);


        arhivirani=findViewById(R.id.arhiviraninalozi);
        obrisiKorisnika=findViewById(R.id.obrisikorisnika);
        dodajKorisnika=findViewById(R.id.dodajkorisnika);
        lista=findViewById(R.id.listapitanja);
        profil=findViewById(R.id.profilkorisnika);
        dodajtest=findViewById(R.id.dodajtest);
        pogledajvideo=findViewById(R.id.pogledajvideo);
        test=findViewById(R.id.test);
        video=findViewById(R.id.video);
        igrica=findViewById(R.id.igrica);
        obrisivideo=findViewById(R.id.obrisivideo);
        azurirajvideo=findViewById(R.id.azurirajvideo);

        Animation animation1= AnimationUtils.loadAnimation(Meni.this,R.anim.fade_in_2sec);
       final Animation animation2= AnimationUtils.loadAnimation(Meni.this,R.anim.fade_in_2sec);
       final Animation animation3= AnimationUtils.loadAnimation(Meni.this,R.anim.fade_in_2sec);


        azurirajvideo.startAnimation(animation1);
        lista.startAnimation(animation1);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
              obrisivideo.startAnimation(animation2);
              dodajtest.startAnimation(animation2);
              igrica.startAnimation(animation2);
              profil.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
             video.startAnimation(animation3);
             test.startAnimation(animation3);
             pogledajvideo.startAnimation(animation3);
             dodajKorisnika.startAnimation(animation3);
             obrisiKorisnika.startAnimation(animation3);
             arhivirani.startAnimation(animation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        obrisivideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, obrisi_video.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });
        azurirajvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, azurirajVideo.class);
                i.putExtra("br",3);
               startActivity(i);
            }
        });
        pogledajvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, listaVidea.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==video)
                {
                    finish();
                    Intent i=new Intent(Meni.this, URL.class);
                    i.putExtra("br",3);
                    startActivity(i);
                }
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==test)
                {
                    finish();
                    Intent i=new Intent(Meni.this, lista_testova.class);
                    i.putExtra("br",3);
                  startActivity(i);
                }
            }
        });
        igrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, Igrica.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });
        dodajtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, dodajtest.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(Meni.this, obrisitest.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i=new Intent(Meni.this, Profil.class);
                i.putExtra("br",3);
                startActivity(i);
            }
        });

        dodajKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Meni.this,dodaj_korisnika.class));
            }
        });
        obrisiKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Meni.this, obrisi_korisnika.class));
            }
        });

        arhivirani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Meni.this,arhivirani_nalozi.class));
            }
        });
        }
}
package com.example.suzana.svetokonas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class profesor_meni extends AppCompatActivity {

    Button profaTest;
    Button profaDodajTest;
    Button profaObrisiTest;
    Button profaLekcije;
    Button profaDodajLekcije;
    Button profaObrisiLekcije;
    Button profaAzurirajLekcije;
    Button profaProfil;
    Button profaIgrica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_meni);

        profaTest=findViewById(R.id.profa_test);
        profaDodajTest=findViewById(R.id.profa_dodaj_test);
        profaObrisiTest=findViewById(R.id.profa_obrisi_test);
        profaLekcije=findViewById(R.id.profa_lekcije);
        profaDodajLekcije=findViewById(R.id.profa_dodaj_lekciju);
        profaObrisiLekcije=findViewById(R.id.profa_obrisi_lekciju);
        profaAzurirajLekcije=findViewById(R.id.profa_azuriraj_lekciju);
        profaProfil=findViewById(R.id.profa_profil);
        profaIgrica=findViewById(R.id.profa_igrica);

        Animation animation1= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
       final Animation animation2= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation3= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation4= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation5= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation6= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation7= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation8= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);
        final Animation animation9= AnimationUtils.loadAnimation(profesor_meni.this,R.anim.fade_in_2sec);


        profaTest.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
             profaLekcije.startAnimation(animation2);
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
                profaProfil.startAnimation(animation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                profaObrisiTest.startAnimation(animation4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            profaDodajLekcije.startAnimation(animation5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            profaDodajTest.startAnimation(animation6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                profaIgrica.startAnimation(animation7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            profaAzurirajLekcije.startAnimation(animation8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
             profaObrisiLekcije.startAnimation(animation9);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        profaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i= new Intent(profesor_meni.this,lista_testova.class);
                i.putExtra("br",2);
                startActivity(i);
            }
        });
        profaProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i= new Intent(profesor_meni.this,Profil.class);
                i.putExtra("br",2);
                startActivity(i);
            }
        });
        profaIgrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i= new Intent(profesor_meni.this,Igrica.class);
                i.putExtra("br",2);
                startActivity(i);
            }
        });
       profaDodajTest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this,dodajtest.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });

       profaDodajLekcije.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this,URL.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });
       profaAzurirajLekcije.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this,azurirajVideo.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });

       profaObrisiLekcije.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this,obrisi_video.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });
       profaObrisiTest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this,obrisitest.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });
       profaLekcije.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
               Intent i= new Intent(profesor_meni.this, listaVidea.class);
               i.putExtra("br",2);
               startActivity(i);
           }
       });
    }
}

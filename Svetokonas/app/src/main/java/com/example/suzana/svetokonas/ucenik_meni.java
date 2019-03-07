package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class ucenik_meni extends AppCompatActivity {

    Button ucenikTest;
    Button ucenikVideo;
    Button ucenikProfil;
    Button ucenikIgrica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucenik_meni);

        ucenikTest=findViewById(R.id.ucenik_test);
        ucenikVideo=findViewById(R.id.ucenik_video);
        ucenikProfil=findViewById(R.id.ucenik_profil);
        ucenikIgrica=findViewById(R.id.ucenik_igrica);
        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.fade_in);

        ucenikTest.startAnimation(animation1);
        ucenikVideo.startAnimation(animation1);
        ucenikProfil.startAnimation(animation1);
        ucenikIgrica.startAnimation(animation1);

        ucenikTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(ucenik_meni.this,lista_testova.class);
                i.putExtra("br",1);
                startActivity(i);
            }
        });

        ucenikVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(ucenik_meni.this,listaVidea.class);
                i.putExtra("br",1);
                startActivity(i);
            }
        });

        ucenikProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
                Intent i=new Intent(ucenik_meni.this,Profil.class);
                i.putExtra("br",1);
                startActivity(i);
            }
        });

        ucenikIgrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(ucenik_meni.this,Igrica.class);
                i.putExtra("br",1);
                startActivity(i);
            }
        });
    }
}

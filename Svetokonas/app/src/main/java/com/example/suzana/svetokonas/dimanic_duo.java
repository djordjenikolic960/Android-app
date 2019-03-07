package com.example.suzana.svetokonas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class dimanic_duo extends AppCompatActivity {
     ImageView dinamicduologo;
     TextView dinamicduotekst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimanic_duo);

        dinamicduotekst=findViewById(R.id.dinamic_duo_tekst);
        dinamicduologo=findViewById(R.id.dinamic_duo_logo);

        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        dinamicduologo.startAnimation(animation1);
        dinamicduotekst.startAnimation(animation1);

        final Animation animation2=AnimationUtils.loadAnimation(this,R.anim.fade_out);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               dinamicduologo.startAnimation(animation2);
               dinamicduotekst.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(dimanic_duo.this,LogIn.class));
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }

                }, 4000);
            }

            @Override
            public void onAnimationEnd(final Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

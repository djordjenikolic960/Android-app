package com.example.suzana.svetokonas;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
public class NapraviTest extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS=30000;//koliko ce da broji timer
    public int highscore;
    private Button dugme1;
    private Button dugme2;
    private Button dugme3;
    private Button dugme4;
    private TextView pitanje;
    private TextView score;
    private TextView timer;
    private TextView brPitanja;
    private int brojacPitanja=0;
    private int ukupanBrPitanja;
    private Pitanje trenutnoPitanje;
    private int trenutniScore;
    private int i = 0;
    private int broj;
    private Test t1=new Test();
    private ColorStateList textColorDefaultCd;//da promenimo boju timeru kada dodje ispod 10
    private CountDownTimer countDownTimer;
    private long timeLeftInMilis;//vreme koje je preostalo u milisekundama
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napravi_test);
        Intent i=getIntent();
        t1=(Test)i.getSerializableExtra("test");
         broj=i.getIntExtra("br",0);

        ukupanBrPitanja = t1.getTest().size();//ovde sada imamo ukupan broj pitanja
        Collections.shuffle(t1.getTest());//ovo ce da promesa pitanja da ne idu istim redosledom
        timer = (TextView) findViewById(R.id.timer);
        brPitanja = (TextView) findViewById(R.id.br_pitanja);
        dugme1 = (Button) findViewById(R.id.odgovor1);
        dugme2 = (Button) findViewById(R.id.odgovor2);
        dugme3 = (Button) findViewById(R.id.odgovor3);
        dugme4 = (Button) findViewById(R.id.odgovor4);
        pitanje = (TextView) findViewById(R.id.pitanje);
        score = (TextView) findViewById(R.id.score);
        timer=findViewById(R.id.timer);
        textColorDefaultCd=timer.getTextColors();//boja timera na pocetku

        prikaziSledecePitanje();
        dugme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer!=null)
                {
                    countDownTimer.cancel();
                }
                if(v==dugme1)
                {
                    if(dugme1.getText().equals(trenutnoPitanje.getTacanOdgovor()))
                    {
                        trenutniScore+=2;
                        score.setText("Score:" + trenutniScore);
                    }
                    prikaziSledecePitanje();
                }
            }
        });
        dugme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer!=null)
                {
                    countDownTimer.cancel();
                }
                if(v==dugme2)
                {
                    if(dugme2.getText().equals(trenutnoPitanje.getTacanOdgovor()))
                    {
                        trenutniScore++;
                        score.setText("Score:" + trenutniScore);
                    }
                    prikaziSledecePitanje();
                }
            }
        });
        dugme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer!=null)
                {
                    countDownTimer.cancel();
                }
                if(v==dugme3)
                {
                    if(dugme3.getText().equals(trenutnoPitanje.getTacanOdgovor()))
                    {
                        trenutniScore++;
                        score.setText("Score:" + trenutniScore);
                    }
                    prikaziSledecePitanje();
                }
            }
        });
        dugme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownTimer!=null)
                {
                    countDownTimer.cancel();
                }
                if(v==dugme4)
                {
                    if(dugme4.getText().equals(trenutnoPitanje.getTacanOdgovor()))
                    {
                        trenutniScore++;
                        score.setText("Score:" + trenutniScore);
                    }
                    prikaziSledecePitanje();
                }
            }
        });
    }
    private void prikaziSledecePitanje()
    {
        if(brojacPitanja<ukupanBrPitanja)
        {
            trenutnoPitanje=t1.getTest().get(brojacPitanja);//sada smo pribavili trenutno pitanje iz liste
            pitanje.setText(trenutnoPitanje.getTekstPitanja());
            dugme1.setText(trenutnoPitanje.getOdgovor1());
            dugme2.setText(trenutnoPitanje.getOdgovor2());
            dugme3.setText(trenutnoPitanje.getOdgovor3());
            dugme4.setText(trenutnoPitanje.getOdgovor4());
            brojacPitanja++;
            brPitanja.setText("Pitanje:" + brojacPitanja + "/" + ukupanBrPitanja);
            timeLeftInMilis=COUNTDOWN_IN_MILLIS;//reset na 30sec
            startujTimer();
        }
        else
        {
            zavrsiTest();
        }
    }
    public void zavrsiTest()
    {
        highscore=trenutniScore;
       Intent i=new Intent(NapraviTest.this, Highscore.class);
       i.putExtra("highscore1", highscore);
        if(broj==1) {
            i.putExtra("br", 1);
        }
        else if(broj==2)
        {
            i.putExtra("br",2);
        }
        else
        {
            i.putExtra("br",3);
        }
       //prosledjivanje atributa iz jednog activitija u drugi
       startActivity(i);
    }
    private void startujTimer()//on tic svake sekunde se poziva(1000)
    {
        countDownTimer=new CountDownTimer(timeLeftInMilis,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeftInMilis=millisUntilFinished;
                osveziTimerText();
            }
            @Override
            public void onFinish()
            {
             timeLeftInMilis=0;
             osveziTimerText();
            prikaziSledecePitanje();
            }
        }.start();
    }
    private void osveziTimerText()
    {
        int minuts= (int) (timeLeftInMilis/1000)/60;
        int second=(int)(timeLeftInMilis/1000)%60;
        String timeFormated=String.format(Locale.getDefault(), "%02d:%02d", minuts, second);
        timer.setText(timeFormated);
        if(timeLeftInMilis<10000)
        {
            timer.setTextColor(Color.RED);
        }
        else
        {
            timer.setTextColor(textColorDefaultCd);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }
}




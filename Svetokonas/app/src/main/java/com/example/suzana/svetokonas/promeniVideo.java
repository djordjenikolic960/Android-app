package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.StringTokenizer;

public class promeniVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promeni_video);

        Intent i=getIntent();
        final int br=i.getIntExtra("br",0);
        final VideoUrl video=(VideoUrl)i.getSerializableExtra("video");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LekcijeUrl");


        final EditText ime=(EditText)findViewById(R.id.imevidea);
        final EditText url=(EditText)findViewById(R.id.url1);
        final EditText broj=(EditText)findViewById(R.id.broj1);
        Button nazad=(Button)findViewById(R.id.nazad1);
        final Button sacuvaj=(Button)findViewById(R.id.sacuvaj);

        ime.setText(video.getIme());
        url.setText(video.getUrl());
        broj.setText(String.valueOf(video.getBr()));

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i=new Intent(promeniVideo.this, azurirajVideo.class);
                if(br==3) {
                    i.putExtra("br", 3);
                    startActivity(i);
                }
                else
                {
                    i.putExtra("br",2);
                    startActivity(i);
                }
            }
        });

        sacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlvidea=url.getText().toString();
                String noviUrl="";
                StringTokenizer tokenizer=new StringTokenizer(urlvidea,"/");
                while(tokenizer.hasMoreTokens())
                {
                    noviUrl=tokenizer.nextToken();
                }
                VideoUrl novivideo=new VideoUrl(video.getId(),noviUrl,ime.getText().toString(),Integer.parseInt(broj.getText().toString()));
                myRef.child(video.getId()).setValue(novivideo);
            }
        });
    }
}

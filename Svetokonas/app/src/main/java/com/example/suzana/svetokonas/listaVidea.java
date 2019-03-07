package com.example.suzana.svetokonas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class listaVidea extends AppCompatActivity {
String url;
Button listaVideaNazad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_videa);
        Intent intent=getIntent();
        final int broj=intent.getIntExtra("br", 0);
        listaVideaNazad=findViewById(R.id.lista_videa_nazad);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LekcijeUrl");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot artist: dataSnapshot.getChildren())
               {
                  VideoUrl video=artist.getValue(VideoUrl.class);
                  final String url1=video.getUrl();
                  Button dugme=new Button(listaVidea.this);
                  dugme.setBackgroundResource(R.drawable.dugme_crno);
                  dugme.setTextColor(Color.WHITE);
                  dugme.setText(video.getIme());
                  LinearLayout li=(LinearLayout)findViewById(R.id.linear);
                   LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                   li.addView(dugme,lp);
                   dugme.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Video.VIDEO_ID=url1;
                            finish();//zatvori ovaj aktivity
                           if(broj==3)
                           {
                           Intent i=new Intent(listaVidea.this, Video.class);
                           i.putExtra("br",3);
                           startActivity(i);
                           }
                           else if(broj==2)
                           {
                               Intent i=new Intent(listaVidea.this, Video.class);
                               i.putExtra("br",2);
                               startActivity(i);
                           }
                           else
                           {
                               Intent i=new Intent(listaVidea.this, Video.class);
                               i.putExtra("br",1);
                               startActivity(i);
                           }
                       }
                   });
               }

                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(listaVidea.this,"doslo je do greske",Toast.LENGTH_LONG).show();
            }
        });

        listaVideaNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (broj == 1) {
                    Intent i = new Intent(listaVidea.this, ucenik_meni.class);
                    startActivity(i);
                }
                else if(broj==2)
                {
                    Intent i=new Intent(listaVidea.this,profesor_meni.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(listaVidea.this,Meni.class);
                    startActivity(i);
                }
            }
        });
    }
}

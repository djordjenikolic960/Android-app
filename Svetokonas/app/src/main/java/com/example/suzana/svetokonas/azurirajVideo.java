package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class azurirajVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriraj_video);

        Intent intent=getIntent();
        final int br=intent.getIntExtra("br",0);

        final LinearLayout linear=(LinearLayout)findViewById(R.id.azurirajlinear);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LekcijeUrl");
        Button nazad=(Button)findViewById(R.id.nazad);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(br==3) {
                    startActivity(new Intent(azurirajVideo.this, Meni.class));
                }
                else
                {
                    startActivity(new Intent(azurirajVideo.this,profesor_meni.class));
                }
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot lekcija: dataSnapshot.getChildren())
                {
                   final VideoUrl video=lekcija.getValue(VideoUrl.class);
                    String ime=video.getIme();
                    Button btn=new Button(azurirajVideo.this);
                    btn.setText(ime);
                    btn.setBackgroundResource(R.drawable.dugme);
                    LinearLayout.LayoutParams pr=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    linear.addView(btn, pr);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(br==3) {
                                Intent i = new Intent(azurirajVideo.this, promeniVideo.class);
                                i.putExtra("video", video);
                                i.putExtra("br", 3);
                                startActivity(i);
                            }else
                            {
                                Intent i = new Intent(azurirajVideo.this, promeniVideo.class);
                                i.putExtra("video", video);
                                i.putExtra("br", 2);
                                startActivity(i);
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(azurirajVideo.this,"doslo je do greske",Toast.LENGTH_LONG).show();
            }
        });
    }
}
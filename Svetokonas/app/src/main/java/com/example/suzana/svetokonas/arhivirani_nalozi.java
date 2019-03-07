package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.StringTokenizer;

public class arhivirani_nalozi extends AppCompatActivity {

    LinearLayout arhivirajNaloglinear;
    Button arhivirajNazad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivirani_nalozi);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Arhivirano");

        arhivirajNazad=findViewById(R.id.arhivirajNalogNazad);
        arhivirajNaloglinear=findViewById(R.id.arhivirajNalog);

        arhivirajNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(arhivirani_nalozi.this,Meni.class));
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    Korisnik k=data.getValue(Korisnik.class);
                    LinearLayout l=new LinearLayout(arhivirani_nalozi.this);
                    TextView prikazi=new TextView(arhivirani_nalozi.this);
                    prikazi.setText(k.getIme() + " " + k.getPrezime());
                    l.setOrientation(LinearLayout.HORIZONTAL);
                    prikazi.setTextSize(20);
                    prikazi.setPadding(10,10,10,30);
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    l.addView(prikazi,lp);
                    View linija=new View(arhivirani_nalozi.this);
                    l.addView(linija);
                    arhivirajNaloglinear.addView(l);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}

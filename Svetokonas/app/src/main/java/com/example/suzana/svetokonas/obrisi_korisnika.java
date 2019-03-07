package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class obrisi_korisnika extends AppCompatActivity {
LinearLayout obrisiKorisnikaLinear;
Button obrisiKorisnikaNazad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrisi_korisnika);

        obrisiKorisnikaNazad=findViewById(R.id.obrisikorisnika_nazad);
        obrisiKorisnikaLinear=findViewById(R.id.obrisi_korisnika_linear);

        obrisiKorisnikaNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(obrisi_korisnika.this, Meni.class));
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");
        final DatabaseReference myRef1 = database.getReference("Arhivirano");


        final FirebaseAuth auth=FirebaseAuth.getInstance();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    final Korisnik k=data.getValue(Korisnik.class);
                    String mail=k.getMail();
                     String email="";
                    StringTokenizer tokenizer=new StringTokenizer(mail,"@");
                    while(tokenizer.hasMoreTokens())
                    {
                        email=tokenizer.nextToken();
                    }
                    if(email.matches("svetisava.com") || email.matches("admin.com"))
                    {}
                    else
                    {
                      LinearLayout l=new LinearLayout(obrisi_korisnika.this);
                      TextView mail2=new TextView(obrisi_korisnika.this);
                      mail2.setText(mail);
                      l.setOrientation(LinearLayout.HORIZONTAL);
                      mail2.setTextSize(30);
                      mail2.setPadding(10,10,10,30);
                      LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                      l.addView(mail2,lp);
                      l.setId(k.hashCode());
                      obrisiKorisnikaLinear.addView(l);
                      l.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              myRef1.child(k.getId()).setValue(k);
                              myRef.child(k.getId()).removeValue();
                              finish();
                              startActivity(new Intent(obrisi_korisnika.this, obrisi_korisnika.class));
                          }
                      });
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

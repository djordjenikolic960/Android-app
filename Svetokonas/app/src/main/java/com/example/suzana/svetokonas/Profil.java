package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.internal.k;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profil extends AppCompatActivity {

    LinearLayout prlinear;
    EditText prIme;
    EditText prPrezime;
    EditText prRazred;
    Button prNazad;
    Button prSacuvaj;
    ImageView prSlika;
    Korisnik korisnik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Intent i=getIntent();
        final int br=i.getIntExtra("br",0);
        prSlika=findViewById(R.id.pr_slika);
        prlinear=findViewById(R.id.pr_linear);
        prIme=findViewById(R.id.pr_ime);
        prPrezime=findViewById(R.id.pr_prezime);
        prRazred=findViewById(R.id.pr_razred);
        prNazad=findViewById(R.id.pr_nazad);
        prSacuvaj=findViewById(R.id.pr_sacuvaj);
        final Animation animation= AnimationUtils.loadAnimation(Profil.this,R.anim.fade_in);


        final String idusera= FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");

                   prSlika.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           prlinear.startAnimation(animation);
                       }
                   });
        prNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(br==3) {
                    startActivity(new Intent(Profil.this, Meni.class));
                }
                else if(br==2)
                {
                    startActivity(new Intent(Profil.this,profesor_meni.class));
                }
                else
                {
                    startActivity(new Intent(Profil.this,ucenik_meni.class));
                }
            }
        });

        prSacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prIme.getText().toString().isEmpty() || prPrezime.getText().toString().isEmpty() || prRazred.getText().toString().isEmpty())
                {
                    Toast.makeText(Profil.this,"sva polja moraju biti popunjena",Toast.LENGTH_SHORT).show();
                }
                else {
                    myRef.child(idusera).child("ime").setValue(prIme.getText().toString());
                    myRef.child(idusera).child("prezime").setValue(prPrezime.getText().toString());
                    myRef.child(idusera).child("razred").setValue(Integer.parseInt(prRazred.getText().toString()));
                    Toast.makeText(Profil.this, "uspesno ste izmenili svoje podatke", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

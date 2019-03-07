package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dodaj_korisnika extends AppCompatActivity {

    LinearLayout dodajKorisnikaForma;
    LinearLayout dodajKorisnikaDugmici;
    EditText dodajKorisnikaIme;
    EditText dodajKorisnikPrezime;
    EditText dodajKorisnikaRazred;
    EditText dodajKorisnikaMail;
    EditText getDodajKorisnikaSifra;
    Button dodajKorisnikaNazad;
    Button dodajKorisnikaSacuvaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_korisnika);

        final FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");

        dodajKorisnikaForma=findViewById(R.id.dodajkorisnika_forma);
        dodajKorisnikaDugmici=findViewById(R.id.dodajkorisnika_dugmici);
        dodajKorisnikaIme=findViewById(R.id.dodajkorisnika_ime);
        dodajKorisnikPrezime=findViewById(R.id.dodajkorisnika_prezime);
        dodajKorisnikaRazred=findViewById(R.id.dodajkorisnika_razred);
        dodajKorisnikaMail=findViewById(R.id.dodajkorisnika_mail);
        getDodajKorisnikaSifra=findViewById(R.id.dodajkorisnika_sifra);
        dodajKorisnikaNazad=findViewById(R.id.dodajkorisnika_nazad);
        dodajKorisnikaSacuvaj=findViewById(R.id.dodajkorisnika_sacuvaj);


        Animation animation= AnimationUtils.loadAnimation(dodaj_korisnika.this,R.anim.fade_in);
        dodajKorisnikaForma.startAnimation(animation);
        dodajKorisnikaDugmici.startAnimation(animation);

        dodajKorisnikaNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(dodaj_korisnika.this, Meni.class));
            }
        });

        dodajKorisnikaSacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ime = dodajKorisnikaIme.getText().toString();
                final String prezime = dodajKorisnikPrezime.getText().toString();
                final String razred = dodajKorisnikaRazred.getText().toString();
                final String mail = dodajKorisnikaMail.getText().toString();
                String sifra = getDodajKorisnikaSifra.getText().toString();
                if (Provera(ime) && Provera(prezime) && Provera(razred) && Provera(mail) && Provera(sifra)) {
                    auth.createUserWithEmailAndPassword(mail, sifra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = auth.getCurrentUser().getUid();
                                Korisnik k = new Korisnik(ime, prezime, Integer.parseInt(razred), mail, uid);
                                myRef.child(uid).setValue(k);
                                Toast.makeText(dodaj_korisnika.this, "uspesno ste dodali korisnika", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(dodaj_korisnika.this, "korisnik sa ovom mail adresom vec postoji!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(dodaj_korisnika.this, "sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean Provera(String s)
    {
        if(s.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}

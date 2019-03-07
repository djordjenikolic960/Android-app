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

import java.util.StringTokenizer;

public class SignIn2 extends AppCompatActivity {
     LinearLayout profillinear;
     EditText profilime;
     EditText profilprezime;
     EditText profilrazred;
     Button profildgm;
     String uid;
     private Korisnik korisnik=new Korisnik();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);
        Intent i=getIntent();
        final String email=i.getStringExtra("email");
        final String password=i.getStringExtra("password");

        final FirebaseAuth mAuth=FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");

        profillinear=findViewById(R.id.profil_linear);
        profilime=findViewById(R.id.profil_ime);
        profilprezime=findViewById(R.id.profil_prezime);
        profilrazred=findViewById(R.id.profil_razred);
        profildgm=findViewById(R.id.profil_dmg);

        if(korisnik!=null)
        {
            profilime.setText(korisnik.getIme());
        }

        Animation animation=AnimationUtils.loadAnimation(SignIn2.this,R.anim.fade_in);
        profillinear.startAnimation(animation);

        profildgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ime = profilime.getText().toString();
                final String prezime = profilprezime.getText().toString();
                final int razred = Integer.parseInt(profilrazred.getText().toString());

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Korisnik novikorisnik = new Korisnik(ime, prezime, razred, email, uid);
                            String mail=email;
                            String provera="";
                            StringTokenizer tokenizer=new StringTokenizer(mail,"@");
                            while(tokenizer.hasMoreTokens())
                            {
                                provera=tokenizer.nextToken();
                            }
                            if(provera.matches("svetisava.com"))
                            {
                                myRef.child(uid).setValue(novikorisnik);
                                Toast.makeText(SignIn2.this, "uspesno ste se registovali", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignIn2.this, profesor_meni.class));
                            }
                            else if(provera.matches("admin.com"))
                            {
                                myRef.child(uid).setValue(novikorisnik);
                                Toast.makeText(SignIn2.this, "uspesno ste se registovali", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignIn2.this, Meni.class));
                            }
                            else {

                                myRef.child(uid).setValue(novikorisnik);
                                Toast.makeText(SignIn2.this, "uspesno ste se registovali", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignIn2.this,ucenik_meni.class));
                            }
                        }
                        else
                        {
                            finish();
                            startActivity(new Intent(SignIn2.this,SignIn.class));
                            Toast.makeText(SignIn2.this,"ovaka email adresa vec postoji",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}

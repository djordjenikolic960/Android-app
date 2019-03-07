package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LogIn extends AppCompatActivity {

    EditText loginuser;
    EditText loginpassword;
    Button log_in;
    FirebaseAuth auth;
    TextView loginsignin;
     Korisnik k1=new Korisnik();
     boolean postoji=false;
     ProgressBar progres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        progres=findViewById(R.id.log_in_progress);

        final List<Korisnik> korisnici=new ArrayList<Korisnik>();
        final List<Korisnik> arhivirani=new ArrayList<Korisnik>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");
        final DatabaseReference myRef1 = database.getReference("Arhivirano");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user: dataSnapshot.getChildren())
                {
                    Korisnik u=user.getValue(Korisnik.class);
                    arhivirani.add(u);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user: dataSnapshot.getChildren())
                {
                  Korisnik u=user.getValue(Korisnik.class);
                  korisnici.add(u);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        auth=FirebaseAuth.getInstance();
        loginuser=findViewById(R.id.loginemail);
        loginpassword=findViewById(R.id.loginpassword);
        log_in=findViewById(R.id.login);
        loginsignin=findViewById(R.id.log_in_signin);

        loginsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LogIn.this,SignIn.class));
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progres.setVisibility(View.VISIBLE);
                if(!loginuser.getText().toString().trim().isEmpty() && !loginpassword.getText().toString().trim().isEmpty()) {
                    for (Korisnik k : arhivirani) {
                        if (k.getMail().matches(loginuser.getText().toString())) {
                            postoji = true;
                        }
                    }
                    if (!postoji) {
                        auth.signInWithEmailAndPassword(loginuser.getText().toString(), loginpassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progres.setVisibility(View.GONE);
                                    String mail = loginuser.getText().toString();
                                    String provera = "";
                                    StringTokenizer tokenizer = new StringTokenizer(mail, "@");
                                    while (tokenizer.hasMoreTokens()) {
                                        provera = tokenizer.nextToken();
                                    }
                                    if (provera.matches("svetisava.com")) {
                                        Toast.makeText(LogIn.this, "uspesno ste se logovali", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(LogIn.this, profesor_meni.class));
                                    } else if (provera.matches("admin.com")) {
                                        Toast.makeText(LogIn.this, "uspesno ste se logovali", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(LogIn.this, Meni.class));
                                    } else {
                                        Toast.makeText(LogIn.this, "uspesno ste se logovali", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(LogIn.this, ucenik_meni.class));
                                    }
                                } else {
                                    progres.setVisibility(View.GONE);
                                    Toast.makeText(LogIn.this, "pogresna email adresa ili lozinka", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                    }
                    else
                    {
                        Toast.makeText(LogIn.this,"vas nalog je obrisan! Molimo vas napravite drugi",Toast.LENGTH_SHORT).show();
                    }

                    }
                    else
                {
                    Toast.makeText(LogIn.this, "sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

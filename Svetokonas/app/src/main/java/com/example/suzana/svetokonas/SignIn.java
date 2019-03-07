package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    TextView log;
    EditText username;
    EditText pas;
    Button dgm;
    boolean provera;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Korisnici");
        log = findViewById(R.id.login);
        username = findViewById(R.id.email);
        pas = findViewById(R.id.password);
        dgm = findViewById(R.id.signin);
        auth = FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignIn.this,LogIn.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        dgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            finish();
                            Intent i = new Intent(SignIn.this, SignIn2.class);
                            i.putExtra("email", username.getText().toString());
                            i.putExtra("password", pas.getText().toString());
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                        }


        });
    }

}

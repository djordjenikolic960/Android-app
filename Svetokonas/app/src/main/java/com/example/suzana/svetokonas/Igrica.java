package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Igrica extends AppCompatActivity {

    TextView rec;
    EditText provera;
    Button povratak;
    Button proveri;
    String recKojaSeTrazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igrica);
        Intent intent=getIntent();
        final int br=intent.getIntExtra("br",0);


        rec=findViewById(R.id.rec);
        provera=findViewById(R.id.pokusaj);
        povratak=findViewById(R.id.povratak);
        proveri=findViewById(R.id.igricaproveri);
        Pocetak();
        proveri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pok=provera.getText().toString();
                if(recKojaSeTrazi.equals(pok))
                {
                    Toast.makeText(Igrica.this, "bravo", Toast.LENGTH_SHORT).show();
                    Pocetak();
                }
                else
                {
                    Toast.makeText(Igrica.this,"pokusajte ponovo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(br==3) {
                    startActivity(new Intent(Igrica.this, Meni.class));
                }
                else if(br==2)
                {
                    startActivity(new Intent(Igrica.this,profesor_meni.class));
                }
                else
                {
                    startActivity(new Intent(Igrica.this,ucenik_meni.class));
                }
            }
        });
    }
    private void Pocetak()
    {
        recKojaSeTrazi=Anagram.randomWord();
        rec.setText(Anagram.shuffleWord(recKojaSeTrazi));
        provera.setText("");
    }
}

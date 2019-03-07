package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class dodajtest extends AppCompatActivity {

    TextView dodajTestBrPitanja;
    EditText dodajTestTekst;
    EditText dodajTestTacan;
    EditText dodajTestNetacan1;
    EditText dodajTestNetacan2;
    EditText dodajTestNetacan3;
    EditText dodajTestImeTesta;
    Button dodajTestNazad;
    Button dodajTestDalje;

    private Test test = new Test();
    private int brojacPitanja = 0;
    private int UkupanBrPitanja = 4;
    private String imetesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodajtest);

        Intent intent=getIntent();
        final int br=intent.getIntExtra("br",0);

        dodajTestImeTesta=findViewById(R.id.dodaj_ime_testa);
        dodajTestBrPitanja = findViewById(R.id.dodaj_test_br_pitanja);
        dodajTestTekst = findViewById(R.id.dodaj_test_tekst_pitanja);
        dodajTestTacan = findViewById(R.id.dodaj_test_tacan_odgovor);
        dodajTestNetacan1 = findViewById(R.id.dodaj_test_odgovor1);
        dodajTestNetacan2 = findViewById(R.id.dodaj_test_odgovor2);
        dodajTestNetacan3 = findViewById(R.id.dodaj_test_odgovor3);
        dodajTestNazad = findViewById(R.id.dodaj_test_nazad);
        dodajTestDalje = findViewById(R.id.dodaj_test_dalje);
        //Sledece();

        dodajTestBrPitanja.setText("Pitanje:" + (brojacPitanja+1) + "/" + (UkupanBrPitanja+1));
        dodajTestNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(br==3) {
                    startActivity(new Intent(dodajtest.this, Meni.class));
                }
                else
                {
                   startActivity(new Intent(dodajtest.this,profesor_meni.class));
                }
            }
        });
        dodajTestDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dodajTestDalje.getText()=="Zapamti")
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("Testovi");
                    String id=myRef.push().getKey();
                    Test t1=new Test(id,imetesta);
                    t1.setTest(test.getTest());
                    myRef.child(id).setValue(t1);
                    Toast.makeText(dodajtest.this,"test je zapamcen", Toast.LENGTH_SHORT).show();
                }
                else {
                    Sledece();
                }
            }
        });
    }

    public void Sledece() {
        if(!Provera(dodajTestTekst.getText().toString())&&  !Provera(dodajTestTacan.getText().toString())&&!Provera(dodajTestNetacan1.getText().toString()) && !Provera(dodajTestNetacan2.getText().toString())&& !Provera(dodajTestNetacan3.getText().toString())) {
            if (brojacPitanja < UkupanBrPitanja && test.getTest().size() < 5) {
                if (brojacPitanja == 0) {
                    imetesta = dodajTestImeTesta.getText().toString();
                    dodajTestImeTesta.setVisibility(View.INVISIBLE);
                }
                Random rand = new Random();
                int broj = rand.nextInt(4) + 1;

                switch (broj) {
                    case (1):
                        Pitanje p = new Pitanje(dodajTestTekst.getText().toString(), dodajTestTacan.getText().toString(), dodajTestNetacan1.getText().toString(), dodajTestNetacan2.getText().toString(), dodajTestNetacan3.getText().toString(), dodajTestTacan.getText().toString());
                        test.dodajPitanje(p);
                        break;
                    case (2):
                        Pitanje p1 = new Pitanje(dodajTestTekst.getText().toString(), dodajTestNetacan1.getText().toString(), dodajTestTacan.getText().toString(), dodajTestNetacan2.getText().toString(), dodajTestNetacan3.getText().toString(), dodajTestTacan.getText().toString());
                        test.dodajPitanje(p1);
                        break;
                    case (3):
                        Pitanje p2 = new Pitanje(dodajTestTekst.getText().toString(), dodajTestNetacan1.getText().toString(), dodajTestNetacan2.getText().toString(), dodajTestTacan.getText().toString(), dodajTestNetacan3.getText().toString(), dodajTestTacan.getText().toString());
                        test.dodajPitanje(p2);
                        break;
                    case (4):
                        Pitanje p3 = new Pitanje(dodajTestTekst.getText().toString(), dodajTestNetacan1.getText().toString(), dodajTestNetacan2.getText().toString(), dodajTestNetacan3.getText().toString(), dodajTestTacan.getText().toString(), dodajTestTacan.getText().toString());
                        test.dodajPitanje(p3);
                        break;
                }
                brojacPitanja++;
                String novibr = "Pitanje:" + (brojacPitanja + 1) + "/" + (UkupanBrPitanja + 1);
                dodajTestBrPitanja.setText(novibr);
                dodajTestTekst.setText("");
                dodajTestTacan.setText("");
                dodajTestNetacan1.setText("");
                dodajTestNetacan2.setText("");
                dodajTestNetacan3.setText("");
            } else {
                dodajTestDalje.setText("Zapamti");
                Pitanje p3 = new Pitanje(dodajTestTekst.getText().toString(), dodajTestNetacan1.getText().toString(), dodajTestNetacan2.getText().toString(), dodajTestNetacan3.getText().toString(), dodajTestTacan.getText().toString(), dodajTestTacan.getText().toString());
                test.dodajPitanje(p3);
            }
        }else
        {
            Toast.makeText(dodajtest.this, "sva polja moraju biti popunjena!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean Provera(String s)
    {
        if(s.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

package com.example.suzana.svetokonas;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class obrisitest extends AppCompatActivity {
    private LinearLayout obrisitestliner;
    private Button obrisiTestNazad;
    private Button obrisiTestDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrisitest);

        Intent i=getIntent();
        final int broj=i.getIntExtra("br",0);
        obrisitestliner = findViewById(R.id.obrisitestlinear);
        obrisiTestNazad=findViewById(R.id.obrisi_test_back);
        obrisiTestDelete=findViewById(R.id.obrisi_test_delete);

        obrisiTestNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(broj==3) {
                    startActivity(new Intent(obrisitest.this, Meni.class));
                }
                else
                {
                    startActivity(new Intent(obrisitest.this,profesor_meni.class));
                }
            }
        });

        final List<Test> cekiranitestovi=new ArrayList<Test>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Testovi");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    final Test test=dataSnapshot1.getValue(Test.class);
                    final LinearLayout l=new LinearLayout(obrisitest.this);
                    l.setOrientation(LinearLayout.HORIZONTAL);
                    TextView prikaz=new TextView(obrisitest.this);
                    String ime=test.getIme();
                    prikaz.setText(ime);
                    prikaz.setTextSize(30);
                    prikaz.setPadding(10,10,270,10);
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    l.addView(prikaz,lp);
                    CheckBox checkBox=new CheckBox(obrisitest.this);
                    checkBox.setId(test.hashCode());
                    l.addView(checkBox);
                    obrisitestliner.addView(l);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                            {
                                cekiranitestovi.add(test);
                                obrisiTestDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        for(Test t:cekiranitestovi)
                                        {
                                        String id=t.getId();
                                         obrisiTest(id);
                                         finish();
                                         startActivity(new Intent(obrisitest.this,obrisitest.class));
                                        }
                                    }
                                });
                            }
                            else
                            {
                                cekiranitestovi.remove(test);
                                obrisiTestDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(cekiranitestovi.isEmpty())
                                        {
                                            Toast.makeText(obrisitest.this, "Nista izabrali test za brisanje", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void obrisiTest(String TestId)
    {
        DatabaseReference dvideo=FirebaseDatabase.getInstance().getReference("Testovi").child(TestId);
        dvideo.removeValue();
    }
}

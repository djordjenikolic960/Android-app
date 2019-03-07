package com.example.suzana.svetokonas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class lista_testova extends AppCompatActivity {
        private LinearLayout listatestovalinear;
        private Button listatestovaNazad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_testova);
        Intent intent=getIntent();
        final int br=intent.getIntExtra("br",0);

        listatestovaNazad=findViewById(R.id.lista_testova_nazad);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Testovi");

        listatestovalinear=findViewById(R.id.lista_testova_linear);

        listatestovaNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(br==3)
                {
                    Intent i=new Intent(lista_testova.this, Meni.class);
                    startActivity(i);
                }
                else if(br==2)
                {
                    Intent i=new Intent(lista_testova.this, profesor_meni.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(lista_testova.this, ucenik_meni.class);
                    startActivity(i);
                }
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot test:dataSnapshot.getChildren())
                {
                    Button b=new Button(lista_testova.this);
                    b.setBackgroundResource(R.drawable.dugme);
                    b.setTextColor(Color.WHITE);
                    b.setPadding(0,10,0,20);
                    final Test t=test.getValue(Test.class);
                    b.setText(t.getIme());
                    LinearLayout.LayoutParams pr=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    listatestovalinear.addView(b,pr);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(lista_testova.this, NapraviTest.class);
                            i.putExtra("test",t);
                            if(br==1) {
                                i.putExtra("br", 1);
                            }
                            else if(br==2)
                            {
                                i.putExtra("br",2);
                            }
                            else
                            {
                                i.putExtra("br",3);
                            }
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

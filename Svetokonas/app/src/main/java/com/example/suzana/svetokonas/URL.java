package com.example.suzana.svetokonas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class URL extends AppCompatActivity {
    private EditText imelekcije;
    private EditText editText;
    private Button button;
    private Button back;
    private EditText broj;
    private boolean postoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LekcijeUrl");
        final List<VideoUrl> listaVideoUrl=new ArrayList<VideoUrl>();

        Intent i=getIntent();
        final int br=i.getIntExtra("br",0);
        postoji=false;
        editText = (EditText) findViewById(R.id.url);
        imelekcije=findViewById(R.id.imelekcije);
        button = (Button) findViewById(R.id.ubaci);
        broj=findViewById(R.id.brlekcije);
        back=findViewById(R.id.back);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot video:dataSnapshot.getChildren())
                {
                    VideoUrl vid=video.getValue(VideoUrl.class);
                    listaVideoUrl.add(vid);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Provera(editText.getText().toString()) && Provera(imelekcije.getText().toString()) && Provera(broj.getText().toString())) {
                    Toast.makeText(URL.this, "sva polja moraju biti popunjena", Toast.LENGTH_LONG).show();
                }
                 else{
                    for (VideoUrl video : listaVideoUrl) {
                        if (video.getBr() == Integer.parseInt(broj.getText().toString())) {
                            postoji = true;
                            Toast.makeText(URL.this, "Lekcija sa tim brojem vec postoji", Toast.LENGTH_SHORT).show();
                        }
                    }
                        if (!postoji) {
                            //VideoUrl videourl=new VideoUrl(editText.getText().toString(),imelekcije.getText().toString());
                            String ceoUrl = editText.getText().toString();
                            String noviUrl = "";
                            StringTokenizer tokenizer = new StringTokenizer(ceoUrl, "/");
                            while (tokenizer.hasMoreTokens()) {
                                noviUrl = tokenizer.nextToken();
                            }
                            String id = myRef.push().getKey();//generisace novi id za svaki video
                            VideoUrl novi = new VideoUrl(id, noviUrl, imelekcije.getText().toString(), Integer.parseInt(broj.getText().toString()));
                            myRef.child(id).setValue(novi).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(URL.this,"uspesno ste dodali lekciju",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==back)
                {
                    finish();
                    if(br==3) {
                        startActivity(new Intent(URL.this, Meni.class));
                    }
                    else
                    {
                        startActivity(new Intent(URL.this,profesor_meni.class));

                    }

                }
            }
        });
    }
    private boolean Provera(String s) {
        if (s.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
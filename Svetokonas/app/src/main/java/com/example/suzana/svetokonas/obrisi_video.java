package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class obrisi_video extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<VideoUrl> lista=new ArrayList<VideoUrl>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrisi_video);

        Intent i=getIntent();
        final int broj=i.getIntExtra("br",0);
        final LinearLayout linear=(LinearLayout)findViewById(R.id.linearobrisi);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LekcijeUrl");
        TextView text=new TextView(obrisi_video.this);
        text.setText("Izaberite test koji zelite da obrisete:");
        text.setPadding(5,10,0,10);
        text.setTextSize(20);
        linear.addView(text);
        final Button obrisi=(Button)findViewById(R.id.delete);
        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(broj==3) {
                    startActivity(new Intent(obrisi_video.this, Meni.class));
                }
                else
                {
                    startActivity(new Intent(obrisi_video.this, profesor_meni.class));
                }
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot lekcija: dataSnapshot.getChildren())
                {
                    final LinearLayout l=new LinearLayout(obrisi_video.this);
                    l.setOrientation(LinearLayout.HORIZONTAL);
                    TextView prikaz=new TextView(obrisi_video.this);
                    final VideoUrl video=lekcija.getValue(VideoUrl.class);
                    String ime=video.getIme();
                    prikaz.setText(ime);
                    prikaz.setTextSize(20);
                    prikaz.setPadding(10,10,50,10);
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    l.addView(prikaz,lp);
                    CheckBox checkBox=new CheckBox(obrisi_video.this);
                    checkBox.setId(video.getBr());
                    l.addView(checkBox);
                    checkBox.setGravity(Gravity.RIGHT);
                    linear.addView(l);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                            {
                                lista.add(video);
                                obrisi.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for(VideoUrl vid:lista)
                                       obrisiVideo(vid.getId());
                                        finish();
                                        if(broj==3) {
                                            Intent i=new Intent(obrisi_video.this,obrisi_video.class);
                                            i.putExtra("br",3);
                                            startActivity(i);
                                        }
                                        else
                                        {
                                            Intent i=new Intent(obrisi_video.this,obrisi_video.class);
                                            i.putExtra("br",2);
                                            startActivity(i);
                                        }

                                        }

                                });
                            }
                            else
                            {
                                lista.remove(video);
                                obrisi.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (lista.isEmpty()) {
                                            Toast.makeText(obrisi_video.this, "nista niste izabrali za brisanje", Toast.LENGTH_SHORT).show();
                                        } else {
                                            for (VideoUrl vid : lista)
                                                Toast.makeText(obrisi_video.this, vid.getIme(), Toast.LENGTH_SHORT).show();
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
    private void obrisiVideo(String videoId)
    {
        DatabaseReference dvideo=FirebaseDatabase.getInstance().getReference("LekcijeUrl").child(videoId);
        dvideo.removeValue();
    }
}

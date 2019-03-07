package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Highscore extends AppCompatActivity {
    private TextView highscore;
    private TextView pohvalnica;
    private Button highscoreMeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        highscoreMeni=findViewById(R.id.highscore_meni);
        pohvalnica=findViewById(R.id.pohvalnica);
        highscore = findViewById(R.id.highscore);
        Intent i = getIntent();
        int hs = i.getIntExtra("highscore1", 0);
        final int broj=i.getIntExtra("br",0);
        switch (hs) {
            case 1:
                pohvalnica.setText("Nedovoljno!");
            highscore.setText("Score:" + hs);
            break;
            case 2:
                pohvalnica.setText("Dovoljan");
                highscore.setText("Score:" + hs);
                break;
            case 3:
                pohvalnica.setText("Dobar");
                highscore.setText("Score:" + hs);
                break;
            case 4:
                pohvalnica.setText("Vrlo dobar");
                highscore.setText("Score:" + hs);
                break;
            case 5:
                pohvalnica.setText("Odlican");
                highscore.setText("Score:" + hs);
                break;
                default:pohvalnica.setText("Vise srece drugi put");
                    highscore.setText("Score:" + hs);
        }
        highscoreMeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(broj==1)
                {
                    finish();
                    startActivity(new Intent(Highscore.this,ucenik_meni.class));
                }
                else if(broj==2)
                {
                    finish();
                    startActivity(new Intent(Highscore.this,profesor_meni.class));
                }
                else
                {
                    finish();
                    startActivity(new Intent(Highscore.this,Meni.class));
                }
            }
        });
    }
}

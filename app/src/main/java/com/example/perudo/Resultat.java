package com.example.perudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        ArrayList<String> classement = new ArrayList<>();
        Intent intent = getIntent();
        int n  = intent.getIntExtra("nbJoueurs",1);
        System.out.println(n);

        for (int i=1 ; i<=n; i++){
            classement.add(intent.getStringExtra("place"+i));
        }


        TextView[] text1 = new TextView[] {
                (TextView) findViewById(R.id.textViewT1),
                (TextView) findViewById(R.id.textViewT2),
                (TextView) findViewById(R.id.textViewT3),
                (TextView) findViewById(R.id.textViewT4),
                (TextView) findViewById(R.id.textViewT5),
                (TextView) findViewById(R.id.textViewT6),
        };

        TextView[] text11 = new TextView[] {
                (TextView) findViewById(R.id.textViewT11),
                (TextView) findViewById(R.id.textViewT22),
                (TextView) findViewById(R.id.textViewT33),
                (TextView) findViewById(R.id.textViewT44),
                (TextView) findViewById(R.id.textViewT55),
                (TextView) findViewById(R.id.textViewT66),
        };


        int v = 0;
        for (String element : classement){
            TextView classe1= text11[v];
            classe1.setText(element);
            classe1.setVisibility(View.VISIBLE);
            TextView classe2= text1[v];
            classe2.setVisibility(View.VISIBLE);
            v++;

        }


        final Button button = findViewById(R.id.buttonAccueil);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();

            }});

    }
}

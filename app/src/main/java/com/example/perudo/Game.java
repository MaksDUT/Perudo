package com.example.perudo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.perudo.de.De;
import com.example.perudo.joueur.Joueur;

import org.w3c.dom.Text;

import java.util.Scanner;

public class Game extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Game activity;
    private static final int DURATION_REQUEST_CODE = 1;
    int nbDe;
    int n = 0;
    int i;
    Joueur jActif;
    ImageView[] img;
    int[] tableImage;
    Party p;
    int max;
    int nbJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.activity =this;

        Intent intent = getIntent();
        n = intent.getIntExtra("nbJoueurs",1);
        nbJoueur = n;

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.de, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);


        //entrez le nombre de dé ( edit text )
        EditText nombres = (EditText) findViewById(R.id.editText);



        tableImage= new int[]{
                getResources().getIdentifier("@drawable/fond",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_1",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_2",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_3",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_4",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_5",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_6",null,this.getPackageName()),
                getResources().getIdentifier("@drawable/de_7",null,this.getPackageName())
        };

        img = new ImageView[] {
                (ImageView) findViewById(R.id.imageView1),
                (ImageView) findViewById(R.id.imageView2),
                (ImageView) findViewById(R.id.imageView3),
                (ImageView) findViewById(R.id.imageView4),
                (ImageView) findViewById(R.id.imageView5)
        };



        //initialisation du jeu

        //variable de partie (nombre de joueurs ) scan

        i=0;
        //initialisation de la partie
        p = new Party(n);




        initialisationManche1(p);

        final Button buttonProchainTour = findViewById(R.id.btFinTour);
        buttonProchainTour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText nombres = (EditText) findViewById(R.id.editText);
                String nombre = nombres.getText().toString();
                if(!nombre.isEmpty()){
                    //executer code verification pari
                    De de = new De(nbDe);
                    int nb = Integer.parseInt(nombre);
                    if(p.getPari().ready(de,nb, max, p.getJoueurs().get(i))){
                        i=(i+1)%n;
                        initialisationMancheAfter1(p);

                    }else{
                        //afficher modifier valeur > a pari
                        AlertDialog.Builder popupPariIncorrect = new AlertDialog.Builder(activity);
                        popupPariIncorrect.setTitle("Erreur");
                        popupPariIncorrect.setMessage("Tu as mal remplis ton pari, il doit etre superieur au pari actuelle !");
                        popupPariIncorrect.show();
                    }
                }else {
                    //afficher message erreur
                    AlertDialog.Builder popupPariVide = new AlertDialog.Builder(activity);
                    popupPariVide.setTitle("Erreur");
                    popupPariVide.setMessage("Tu n'as pas remplis ton pari ! ");
                    popupPariVide.show();
                }

            }});

        final Button buttonPerudo = findViewById(R.id.btPerudo);
        buttonPerudo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                p.finManche(p.getPari().getValeur() ,p.getPari().getDe() ,p.getPari().getJoueur(),jActif);
                n = p.getMaxJoueurs();
                i=0;

                if(p.endGame()){

                    Intent intent = new Intent(getApplicationContext(), Resultat.class);
                    int c = 1;
                    intent.putExtra("nbJoueurs",nbJoueur);
                    for (Joueur joueur : p.resultat().values()){
                        intent.putExtra("place"+c,joueur.getName()); // put the chosen duration
                        c++;
                    }
                    setResult(RESULT_OK,intent);
                    startActivityForResult(intent, DURATION_REQUEST_CODE);
                    finish();

                }else{
                    initialisationManche1(p);
                }
            }});

    }

    public void initialisationManche1(Party p){
        i=0;
        p.shuffle();
        p.addTotalDes();
        max = p.getMaxValeur();
        //affichage dé
        p.refreshPari();
        imageReset(img);

        jActif= p.getJoueurs().get(i);

        TextView joueurActif = (TextView) findViewById(R.id.textViewJoueur);
        joueurActif.setText(jActif.getName());

        for(int y=0; y<jActif.getDes().size();y++){
            ImageView  im= img[y];
            int valeurDe = jActif.getDes().get(y).getDe();
            im.setImageResource(tableImage[valeurDe]);
            im.setVisibility(View.INVISIBLE);
        }
        System.out.println(jActif);


        final Button button = findViewById(R.id.buttonDe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for(int y=0; y<jActif.getDes().size();y++){
                    ImageView  im= img[y];
                    im.setVisibility(View.VISIBLE);
                }

            }});


        //affichage pari
        TextView pariValeur = (TextView) findViewById(R.id.textViewPariValeur);
        pariValeur.setText(""+p.getPari().getValeur());

        ImageView pariDe = (ImageView) findViewById(R.id.imageViewPariDe);
        pariDe.setImageResource(tableImage[p.getPari().getDe().getDe()]);


        //disable bouton perudo
        final Button perudo =findViewById(R.id.btPerudo);
        perudo.setEnabled(false);
    }



    public void initialisationMancheAfter1(Party p){
        //affichage dé


        jActif= p.getJoueurs().get(i);

        TextView joueurActif = (TextView) findViewById(R.id.textViewJoueur);
        joueurActif.setText(jActif.getName());

        imageReset(img);

        for(int y=0; y<jActif.getDes().size();y++){
            ImageView  im= img[y];
            int valeurDe = jActif.getDes().get(y).getDe();
            im.setImageResource(tableImage[valeurDe]);
            im.setVisibility(View.INVISIBLE);
        }
        System.out.println(jActif);


        final Button button = findViewById(R.id.buttonDe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for(int y=0; y<jActif.getDes().size();y++){
                    ImageView  im= img[y];
                    im.setVisibility(View.VISIBLE);
                }

            }});


        //affichage pari
        TextView pariValeur = (TextView) findViewById(R.id.textViewPariValeur);
        pariValeur.setText(""+p.getPari().getValeur());

        ImageView pariDe = (ImageView) findViewById(R.id.imageViewPariDe);
        pariDe.setImageResource(tableImage[p.getPari().getDe().getDe()]);



        final Button perudo =findViewById(R.id.btPerudo);
        perudo.setEnabled(true);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        nbDe = Integer.parseInt(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public static void perudo(int nbJoueur , int[] tableDe,ImageView[] img) {



    }

    public void imageReset(ImageView[] img){
        for(int y=0; y<img.length;y++){
            ImageView  im= img[y];

            im.setImageResource(tableImage[7]);
            im.setVisibility(View.VISIBLE);
        }
    }
}

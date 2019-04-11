package com.example.perudo;

import com.example.perudo.de.De;
import com.example.perudo.joueur.Joueur;
import com.example.perudo.pari.Pari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Party {
	
	private final ArrayList<Joueur> joueurs;
	private ArrayList<Integer> totalDes;
	private Map<Integer, Joueur> resultat;
	private int maxJoueurs;
	private int maxDe;
	private Pari pa;
	
	public Party(int maxJoueurs) {
		//constructeur créé la partie
		resultat = new HashMap<Integer,Joueur>();
		
		joueurs= new ArrayList<Joueur>();
 		for(int i=0; i<maxJoueurs;i++){
 			joueurs.add(new Joueur("joueur"+(i+1)));
 		}
 		totalDes= new ArrayList<Integer>(6);
 		for(int i=0; i<6;i++){
 			totalDes.add(0);
 		}
 		this.maxJoueurs=maxJoueurs;
 		this.maxDe=this.maxJoueurs*5;
 		pa= new Pari();
 		
 		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Liste de Joueurs : \n"+ joueurs;
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public void shuffle(){
		//melange les dés de chaque joueurs
 		for(int i=0 ; i<maxJoueurs; i++){
 			joueurs.get(i).shuffle();
 		}
	}
	
	
	public Pari getPari() {
		return pa;
	}
	
	public int getMaxValeur() {
		return maxDe;
	}
	
	public void addTotalDes() {
		// ajout dans le tableau de dé le nombre de chaque dé dans la manche 
		
		totalDes.clear();
		for(int i=0; i<6;i++){
 			totalDes.add(0);
 		}
		for ( Joueur joueur : joueurs) {
			
			
			for(De de : joueur.getDes()) {
				Integer value=totalDes.get(de.getDe()-1);
				value=value +1;
				totalDes.set(de.getDe()-1,value);
				if(de.getDe()==1) {
				for(int i=1; i<6;i++) {
					Integer valu=totalDes.get(i);
					valu+=1;
					totalDes.set(i, valu);
				}
			}}
		}
	}
	
	public int getMaxJoueurs(){
		return this.maxJoueurs;
	}


	public void finManche(int valeur, De de, Joueur jPari, Joueur jStop) {
		if(totalDes.get(de.getDe()-1)<valeur){
			jPari.perdreVie();

			if(!jPari.isAlive()){
				joueurs.remove(jPari);
				resultat.put(maxJoueurs,jPari);
				this.maxJoueurs--;
			}

		}else{
			jStop.perdreVie();
			if(!jStop.isAlive()){
				joueurs.remove(jStop);
				resultat.put(maxJoueurs,jStop);
				this.maxJoueurs--;
			}
		}
		maxDe=maxDe-1;
	}
	public Map<Integer, Joueur> resultat(){
		return resultat;
	}

	public boolean endGame(){
		if(maxJoueurs==1){
			this.resultat.put(maxJoueurs,joueurs.get(0));
			return true;
		}
		return false;
		}


	public void refreshPari(){
		this.pa = new Pari();
	}


	public Joueur winner(){
		if(endGame()){
			
			for(Joueur joueur : joueurs) {
				if(joueur.isAlive()) {
					return joueur;
				}
			}
			
			
		}
		throw new IllegalStateException("winner ne peut pas etre utiliser si la partie n'est pas fini");
	}
	
	public void afficheTabDes() {
		
		System.out.println(totalDes);
	}
}

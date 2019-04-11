package com.example.perudo.pari;

import com.example.perudo.de.De;
import com.example.perudo.joueur.Joueur;



public class Pari {
	
	private De de;
	private int valeur;
	private Joueur j0;
	
	
	public Pari() {
		de=new De();
		valeur=0;
		j0=null;
	}



	public boolean ready(De de, int valeur, int max, Joueur j0) {
		// il est pas possible de rentrer une valeur superieur aux nombre maximum de dé
		if(valeur>max) {
			return false;
		}
		// on verifie si le pari est bien une surenchère
		if (this.de.getDe() >= de.getDe() && this.valeur>=valeur) {
			return false;
		}
		if (this.de.getDe() <= de.getDe() && this.valeur >valeur) {
			return false;
		}
		if(this.de.getDe() > de.getDe() && this.valeur <=valeur) {
			return false;
		}


		//on ne peut pas mettre de valeur negative
		if(valeur<0) {
			return false;
		}
		this.de=de;
		this.valeur=valeur;
		this.j0=j0;
		return true;
	}

	public int getValeur(){
		return valeur;
	}

	public De getDe(){
		return de;
	}

	public Joueur getJoueur(){
		return j0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "Pari "+this.de +" X "+this.valeur ;
	}
	
}

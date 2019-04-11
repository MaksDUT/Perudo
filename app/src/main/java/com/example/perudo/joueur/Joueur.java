package com.example.perudo.joueur;

import com.example.perudo.de.De;

import java.util.ArrayList;



 public class Joueur {


 	private int vie=5;
 	private final ArrayList<De> des;
 	private final String name;


 	public Joueur(String name){

 		des= new ArrayList<De>();
 		for(int i=0; i<vie;i++){
 			des.add( new De());

 		}
 		this.name=name;
 	}


 	public void shuffle(){
 		
 		//melange les dé du joueur
 		for(int i=0 ; i<vie; i++){
 			des.get(i).shuffle();
 		}

 	}
 	
 
 	
 	public int getVie() {
 		return vie;
 	}
 	
 	public ArrayList<De> getDes() {
 		return des;
 	}
 	
 	public boolean perdreVie() {
 		if(vie>0) {
 			this.vie--;
 			this.des.remove(0);
 			return true;
 		}
 		return false;
 	}

 	public boolean isAlive(){
 		return vie>0;
 		
 	}
 	
 	public String getName(){
 		return name;
	}

 	@Override

 	public String toString(){
 		return "\n nom :"+name + " Des : "+ des;
 	}


 }
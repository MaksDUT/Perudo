package com.example.perudo;

import com.example.perudo.de.De;
import com.example.perudo.joueur.Joueur;

import java.util.Scanner;




public class Perudo {

	public static void perudo() {
		
		//initialisation du jeu 
		
		//variable de partie (nombre de joueurs ) scan 
		int n= 6;
		int i=0;
		
		
		//initialisation de la partie 
		Party p = new Party(n);

		//Scanner scde = new Scanner(System.in);
		//Scanner scva = new Scanner(System.in);
		//boucle party 
		while(true) {
			Scanner scde = new Scanner(System.in);
			Scanner scva = new Scanner(System.in);
			i=0;
			p.shuffle();
			p.addTotalDes();
			int max = p.getMaxValeur();
			
			//boucle de manche 
			while (true) {
				
				//affichage dé 
				Joueur jActif= p.getJoueurs().get(i);
				System.out.println(jActif);
				//affichage pari 
				System.out.println(p.getPari());
				
				 
				String str = "";
				De de = new De();
				int int2 =0;

				do {
    
					
					while(!(str.equals("o") || str.equals("n"))) {
						System.out.println("Pari ( o = oui / n = non ) :");
						str = scde.nextLine();
					}
					if(str.equals("n")) {
						break;
					}
					
					if ( str.equals("o")) {
						
						//selection pari
						int int1 = 0;
						
						
						
						while(int1<1 || int1>6) {
							
							try {
								System.out.println("Veuillez saisir valeur de dé :");
								int1 = scde.nextInt();
							}catch(Exception e){
								
							}
							scde.nextLine();
						}
					
						de= new De(int1);
					
						int2 = 0;
						while(int2<1 || int2 > max) {
							System.out.println("Veuillez saisir le nombre de dé :");
							try {
								int2 = scva.nextInt();
							}catch(Exception e) {
								
							}
							
						}
						
						
						//button validé
					}
				}while(!p.getPari().ready(de,int2, max, p.getJoueurs().get(i)) );
					
					
				if(str.equals("n")){
					// verifier le pari 
					
					p.finManche(p.getPari().getValeur() ,p.getPari().getDe() ,p.getPari().getJoueur(),jActif);
					break;
				}


				
				
				i=(i+1)%n;
			}
			//condition de fin de partie 
			System.out.println("coucou");
			if(p.endGame()){
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		perudo();
		
	}
	
	
}


package game;
import game.joueur.Joueur;

public class DeTest{

	 public static void main(String[] args) {
		

		Joueur j1 = new Joueur("Max");
		j1.shuffle();
		Party game= new Party(3);
		game.shuffle();
		
		System.out.println(game);
		
		
		System.out.println(j1);
		
		game.addTotalDes();
		game.afficheTabDes();

	}

}
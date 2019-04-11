package com.example.perudo.de;
import java.util.Random;

public class De {

	private int de;


	public De(){
	this(0);
}
	
	public De(int num){
		de=num;
	}

	public void shuffle(){
		// melange un dé
		
		Random r = new Random();
		
		de=r.nextInt(6 )+1 ;

	}
	
	public int getDe() {
		return de;
	}

	@Override 

	public String toString(){
		return " de = "+de ;
	}

}

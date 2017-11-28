package juniorMatador;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	ArrayList<ChanceCards> deck = new ArrayList<ChanceCards>(); 
	
	public Deck() {		
		createDeck();		
	}
	
	public void createDeck() {
		
		deck.add(new ChanceCards("Go to START and receive 2 money", 0, "", 0, 0));
		deck.add(new ChanceCards("Move 5 fields forward", 0, "", 0, 5));
		deck.add(new ChanceCards("Free Field! Move to the next Orange field. If it's available you get it for free, if not pay rent to the owner", 0, "Orange", -1, 0));
		deck.add(new ChanceCards("Move 1 forward", 0, "", 0, 1));
		deck.add(new ChanceCards("Free Field! Move to the next Green field. If it's available you get it for free, if not pay rent to the owner", 0, "Green", -1, 0));
		deck.add(new ChanceCards("Free Field! Move to the next light Blue field. If it's available you get it for free, if not pay rent to the owner", 0, "Light Blue", -1, 0));
		deck.add(new ChanceCards("Move to 'Strandpromenade'", 0, "", 23, 0));
		deck.add(new ChanceCards("Free Field! Move to the next Blue field. If it's available you get it for free, if not pay rent to the owner", 0, "Blue", -1, 0));
		deck.add(new ChanceCards("Free Field! Move to the next Pink field. If it's available you get it for free, if not pay rent to the owner", 0, "Pink", -1, 0));
		deck.add(new ChanceCards("Wow you're doing a good job! You reveive 2 money", 2, "", -1, 0));
		deck.add(new ChanceCards("Free Field! Move to the next Red field. If it's available you get it for free, if not pay rent to the owner", 0, "Red", -1, 0));
		deck.add(new ChanceCards("Free Field! Move to the next Brown field. If it's available you get it for free, if not pay rent to the owner", 0, "Brown", -1, 0));
		deck.add(new ChanceCards("Free Field! Move to the next Yellow field. If it's available you get it for free, if not pay rent to the owner", 0, "Yello", -1, 0));

	}


	public ChanceCards drawCard() {
		Random rand = new Random();
	    int i = rand.nextInt(24);
		return deck.get(i); 
	}		
}

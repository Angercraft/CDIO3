package juniorMatador;

import java.util.ArrayList;
import java.util.Random;

public class ChanceCards {
	protected String message;
	protected int transaction;
	protected String moveToColorField;
	protected int moveToFieldName;
	protected int movePiece;
		
		ChanceCards (String message, int transaction, String moveToColorField, int moveToFieldName, int movePiece) {
			this.movePiece = movePiece;
			this.transaction = transaction;
			this.message = message;
			this.moveToColorField = moveToColorField;
			this.moveToFieldName = moveToFieldName;
		};
		


		ArrayList<ChanceCards> deck = new ArrayList<ChanceCards>();

		public ArrayList<ChanceCards> deck() {
		deck.add(new ChanceCards("Go to START and receive 2 money", 2, moveToColorField , 0, movePiece));
		deck.add(new ChanceCards("Move 5 fields forward", transaction, moveToColorField, moveToFieldName, 5));
		deck.add(new ChanceCards("Free Field! Move to the next Orange field. If it's available you get it for free, if not pay rent to the owner", transaction, "Orange" , moveToFieldName, movePiece));
		deck.add(new ChanceCards("Move 1 forward", transaction, moveToColorField, moveToFieldName, 1));
		deck.add(new ChanceCards("Free Field! Move to the next Green field. If it's available you get it for free, if not pay rent to the owner", transaction, "Green", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next light Blue field. If it's available you get it for free, if not pay rent to the owner", transaction, "Light Blue", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Move to 'Strandpromenade'", transaction, moveToColorField, 23, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next Blue field. If it's available you get it for free, if not pay rent to the owner", transaction, "Blue", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next Pink field. If it's available you get it for free, if not pay rent to the owner", transaction, "Pink", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Wow you're doing a good job! You reveive 2 money", 2, moveToColorField, moveToFieldName, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next Red field. If it's available you get it for free, if not pay rent to the owner", transaction, "Red", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next Brown field. If it's available you get it for free, if not pay rent to the owner", transaction, "Brown", moveToFieldName, movePiece));
		deck.add(new ChanceCards("Free Field! Move to the next Yellow field. If it's available you get it for free, if not pay rent to the owner", transaction, "Yello", moveToFieldName, movePiece));
		return deck;
		}
		
		public void drawCard() {
			deck();
			Random rand = new Random();
	        int i = rand.nextInt(24)+1;
			this.deck.get(i).message = message;
			this.deck.get(i).movePiece = movePiece;
			this.deck.get(i).moveToColorField = moveToColorField;
			this.deck.get(i).moveToFieldName = moveToFieldName;
			this.deck.get(i).transaction = transaction;
		}
		
		public void cardEffect() {
			
			if (!message.isEmpty()) {
				
			}
		}
	
		
		/*
    public void main(String[] args) {
    	
    	System.out.println(drawCard());
    }  */

   
}


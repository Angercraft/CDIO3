package juniorMatador;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ChanceCards {
	private int movePiece;
	private int transaction;

		public ChanceCards (int movePiece, int transaction) {
			this.movePiece = movePiece;
			this.transaction = transaction;
		};
		
		
			/**
			 * 
			 * @override 
			 *
			public String toString() {
				String string = ChanceCards.card.movePiece + " " + ChanceCards.card.transaction;
				return string;	
			}
			*/
		static ArrayList<ChanceCards> deck = new ArrayList<ChanceCards>();
		public static ArrayList<ChanceCards> fillArray() {
		deck.add(new ChanceCards(5,6));
		deck.add(new ChanceCards(2,5));
		return deck;
		}
    public static void main(String[] args) {
    	fillArray();
    	deck.get(1);
    System.out.println(deck.get(1).movePiece);
    
    }

   
}
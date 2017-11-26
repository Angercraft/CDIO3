package juniorMatador;

import java.util.ArrayList;

public class ChanceCards {
	private int movePiece;
	private int transaction;
	
	ChanceCards(int movePiece, int transaction) {
		this.movePiece = movePiece;
		this.transaction = transaction;
		getMovePiece();
		getTransaction();
	}
	
	static ArrayList<ChanceCards> allChanceCards = new ArrayList<ChanceCards>();
	
	
	private static String deck() {
		ChanceCards card = new ChanceCards(0, 2);
		allChanceCards.add(card);
		
		card = new ChanceCards(5, 0);
		allChanceCards.add(card);
		
		card = new ChanceCards(5, 0);
		
		
		return allChanceCards.toString();
	}
    public static void main(String[] args) {
    	
    	System.out.println(deck());
    }
	
	private int getMovePiece() {
		return movePiece;
	}
	
	private int getTransaction() {
		return transaction;
	}
	
	

}

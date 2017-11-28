package juniorMatador;


public class ChanceCards {
	protected String message;
	protected int transaction;
	protected String moveToColorField;
	protected int moveToFieldNumber;
	protected int movePiece;
		
	public ChanceCards(String message, int transaction, String moveToColorField, int moveToFieldNumber, int movePiece) {
		this.movePiece = movePiece;
		this.transaction = transaction;
		this.message = message;
		this.moveToColorField = moveToColorField;
		this.moveToFieldNumber = moveToFieldNumber;
	}
   
}


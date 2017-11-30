package juniorMatador;

public class ChanceMoveTo extends ChanceBase {

    private int moveAmount;

    ChanceMoveTo(String message, String type, int moveAmount) {
        super(message, type);
        this.moveAmount = moveAmount;
    }

    public int getMoveAmount() {
        return moveAmount;
    }
}

package juniorMatador;

public class ChanceMoney extends ChanceBase {

    private int amount;
    private boolean othersPay = false;

    ChanceMoney(String message, String type, int amount) {
        super(message, type);
        this.amount = amount;
    }

    ChanceMoney(String message, String type, int amount, boolean othersPay) {
        super(message, type);
        this.amount = amount;
        this.othersPay = othersPay;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getOthersPay() {
        return othersPay;
    }
}

package juniorMatador;

public class ChanceMoney extends ChanceBase {

    private int amount;
    private boolean payCard = false;
    private boolean othersPay = false;

    ChanceMoney(String message, String type, int amount) {
        super(message, type);
        this.amount = amount;
    }

    ChanceMoney(String message, String type, int amount, boolean payCard, boolean othersPay) {
        super(message, type);
        this.amount = amount;
        this.payCard = payCard;
        this.othersPay = othersPay;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getPayCard() {
        return payCard;
    }

    public boolean getOthersPay() {
        return othersPay;
    }
}

package juniorMatador;

public class Buildings {

    private int amount;

    Buildings() {
        amount = 10;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        if ((this.amount + amount) < 0) {
            this.amount = 0;
        } else {
            this.amount += amount;
        }
    }

    public void reset() {
        amount = 10;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

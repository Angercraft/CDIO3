package juniorMatador;

public class Money {

    private int amount;

    Money() {
        this.amount = 31;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Method to ensure a player can't have a negative balance.
     * @param amount the value which the amount should change. Can be positive or negative.
     */
    public void addAmount(int amount){
        if ((this.amount + amount) < 0) {
            this.amount = 0;
        } else {
            this.amount += amount;
        }
    }

    /**
     * Used when starting a new game.
     */
    public void resetMoney() {
        this.amount = 31;
    }
}

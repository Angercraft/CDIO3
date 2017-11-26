package juniorMatador;

public class Buildings {

    private int amount;
    private int startAmount;

    /**
     * Creates an object Buildings, to keep track of player buildings.
     * @param amount the amount of buildings the player should start with.
     */
    Buildings(int amount) {
        this.amount = amount;
        this.startAmount = amount;
    }

    /**
     * @return Returns the amount of buildings.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Adds to the amount of buildings already present. Can be negative to subtract.
     * @param amount The amount to add or subtract.
     */
    public void addAmount(int amount) {
        if ((this.amount + amount) < 0) {
            this.amount = 0;
        } else {
            this.amount += amount;
        }
    }

    /**
     * Resets the amount to the original value.
     */
    public void reset() {
        amount = startAmount;
    }

    @Override
    public String toString() {
        return "Current value: "+amount+" Original value: "+startAmount;
    }
}

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

    public void addAmount(int amount){
        if ((this.amount + amount) < 0) {
            this.amount=0;
        } else {
            this.amount+=amount;
        }
    }

    public void resetMoney() {
        this.amount = 31;
    }
}

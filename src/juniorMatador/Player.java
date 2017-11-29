package juniorMatador;

public class Player {
    private String name;
    private boolean playerTurn = false;
    private int playerNumber;
    private int playerPos = 0;
    private boolean skipJail = false;
    private Money money;

    Player(String name, int playerNumber, int moneyAmount) {
        this.name = name;
        this.playerNumber = playerNumber;
        money = new Money(moneyAmount);
    }

    public String getName() {
        return this.name;
    }

    public void reset() {
        money.resetMoney();
        playerPos = 0;
        playerTurn = false;
    }

    public Money getMoney() {
        return money;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerTurn (boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean getPlayerTurn () {
        return this.playerTurn;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public void setSkipJail(boolean skipJail) {
        this.skipJail = skipJail;
    }

    public boolean getSkipJail() {
        return skipJail;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

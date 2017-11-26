package juniorMatador;

public class Player {
    private String name;
    private boolean playerTurn = false;
    private int playerNumber;
    private int playerPos = 0;
    private Money money;
    private Buildings buildings;

    Player(String name, int playerNumber, int moneyAmount, int buildingsAmount) {
        this.name = name;
        this.playerNumber = playerNumber;
        money = new Money(moneyAmount);
        buildings = new Buildings(buildingsAmount);
    }

    public String getName() {
        return this.name;
    }

    public void reset() {
        money.resetMoney();
        buildings.reset();
        playerPos = 0;
        playerTurn = false;
    }

    public Money getMoney() {
        return money;
    }

    public Buildings getBuildings() {
        return buildings;
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

    @Override
    public String toString() {
        return super.toString();
    }
}

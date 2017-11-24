package juniorMatador;

public class Player {
    private String name;
    private boolean playerTurn = false;
    private int playerNumber;
    private int playerPos = 0;
    Money money;
    Buildings buildings;
    private boolean isPrisoned = false;

    Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        money = new Money();
        buildings = new Buildings();
    }
    public boolean getPrisoned() {
        return isPrisoned;
    }

    public void setPrisoned(boolean prisoned) {
        isPrisoned = prisoned;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
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

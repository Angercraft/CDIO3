package juniorMatador;

public class Player {
    private String name;
    private boolean playerTurn = false;
    Money money;
    Buildings buildings;

    Player(String name) {
        this.name = name;
        money = new Money();
        buildings = new Buildings();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPlayerTurn (boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean getPlayerTurn () {
        return this.playerTurn;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

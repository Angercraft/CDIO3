package juniorMatador;

public class MoveToCafe extends Field {

    public MoveToCafe(String name, int fieldNo) {
        super(name,fieldNo);
    }

    public void move(Player player){
        player.setPlayerPos(10);
        player.setPrisoned(true);
    }

    public void payBail(Player player){
        player.money.addAmount(-1);
        player.setPrisoned(false);
    }
}

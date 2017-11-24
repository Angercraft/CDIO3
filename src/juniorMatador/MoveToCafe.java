package juniorMatador;

import java.awt.*;

public class MoveToCafe extends Field {

    public MoveToCafe(Color color, String name, int fieldNo) {
        super(color, name,fieldNo);
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

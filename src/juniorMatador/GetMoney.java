package juniorMatador;

import java.awt.*;

public class GetMoney extends Field {

    private int moneyPrice;

    public GetMoney(Color color, String name, int fieldNo) {
        super(color, name,fieldNo);
    }

    public void addMoney(int money){
        moneyPrice+=money;
    }

    public void giveMoney(Player player){
        player.money.addAmount(moneyPrice);
        moneyPrice=0;
    }


}

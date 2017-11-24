package juniorMatador;

import java.awt.*;

public class Attraction extends Field {

    private int price;

    public Attraction(Color color, String name, int fieldNo, int price) {
        super(color, name, fieldNo);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void LandOnField(Player land){
        land.money.addAmount(-price);

    }





}

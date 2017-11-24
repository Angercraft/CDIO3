package juniorMatador;

import java.awt.*;

public class PlayerOwnedAttraction extends Field {

    private Player owner;
    private int price;
    private int rent;

    public PlayerOwnedAttraction(Color color, String name, int price, int fieldNo) {
        super(color, name, fieldNo);
        this.price=price;
        owner = null;
    }

    public void setOwner(Player owner){
        this.owner=owner;
    }

    public Player getOwner() {
        return owner;
    }

    public int getPrice(){
        return price;
    }

    public int getRent(){
        return rent;
    }

    public void buy(Player buyer){
        if(owner!=null){
            System.out.println("Already Owned");
        }
        else {
            buyer.money.addAmount(-price);
            owner=buyer;
        }
    }

    public void LandOnField(Player land){
        if(owner!=null){
            if(land==owner){
                System.out.println("You own this property");
            }
            else {
                land.money.addAmount(-rent);

            }
        }
        else {
            buy(land);
        }

    }
}

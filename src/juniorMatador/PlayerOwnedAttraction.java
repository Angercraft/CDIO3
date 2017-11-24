package juniorMatador;

public class PlayerOwnedAttraction extends Field {

    private Player owner;
    private String color;
    private int price;
    private int rent;

    public PlayerOwnedAttraction(String name, int price, String color, int fieldNo) {
        super(name, fieldNo);
        this.price=price;
        this.color=color;
        owner = null;
    }

    public void setOwner(Player owner){
        this.owner=owner;
    }

    public Player getOwner() {
        return owner;
    }

    public String getColor(){
        return color;
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

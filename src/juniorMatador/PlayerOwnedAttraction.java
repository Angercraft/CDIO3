package juniorMatador;

public abstract class PlayerOwnedAttraction extends Attraction {

    private Player owner;
    private String color;

    public PlayerOwnedAttraction(String name, int price, String color) {
        super(name);
        super(price);
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

    public void buy(Player buyer){
        if(owner!=null){
            System.out.println("Already Owned");
        }
        else {
            buyer.changeAmount(-price);
            owner=buyer;
        }

    }
}

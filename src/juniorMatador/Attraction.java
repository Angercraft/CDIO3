package juniorMatador;

public class Attraction extends Field {

    public int price;

    public Attraction(String name, int fieldNo,int price) {
        super(name, fieldNo);
        this.price=price;
    }

    public int getPrice(){
        return price;
    }

    public void LandOnField(Player land){
        land.money.addAmount(-price);

    }





}

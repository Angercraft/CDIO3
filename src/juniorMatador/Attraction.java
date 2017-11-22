package juniorMatador;

public class Attraction extends Field {

    public int price;

    public Attraction(String name,int price) {
        super(name);
        this.price=price;
    }

    public int getPrice(){
        return price;
    }


}

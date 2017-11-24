package juniorMatador;

public class GetMoney extends Field {
    public GetMoney(String name,int fieldNo) {
        super(name,fieldNo);
    }
    private int moneyPrice;

    public void addMoney(int money){
        moneyPrice+=money;
    }

    public void giveMoney(Player player){
        player.money.addAmount(moneyPrice);
        moneyPrice=0;
    }


}

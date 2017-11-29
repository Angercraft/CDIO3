package juniorMatador;

public class ChanceStart extends ChanceBase {

    private int recieve;

    ChanceStart(String message, String type, int recieve) {
        super(message, type);
        this.recieve = recieve;
    }

    public int getRecieve() {
        return recieve;
    }
}

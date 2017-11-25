package juniorMatador;

public class LogicParking extends LogicField {

    private int value;

    public LogicParking(int fieldNo) {
        super(fieldNo, "PARKING");
    }

    public void setValue(int value) {
        if ((this.value + value) < 0) {
            this.value = 0;
        } else {
            this.value += value;
        }
    }

    public int getValue() {
        return value;
    }
}

package juniorMatador;

import java.awt.*;

public class LogicStreet extends LogicField {

    private int value;
    private Color color;
    private Player owner;
    private int buildings;

    public LogicStreet(int fieldNo, int value, Color color) {
        super(fieldNo, "STREET");
        this.value = value;
        this.color = color;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public Color getColor() {
        return color;
    }

    public int getRent() {
        return value*buildings;
    }

    public void setBuildings(int buildings) {
        if (buildings > 0 && buildings < 4) {
            this.buildings = buildings;
        } else {
            System.out.println("Illegal input. You can only have between 1 and 3 buildings on a field.");
        }

    }

    public int getBuildings() {
        return buildings;
    }
}

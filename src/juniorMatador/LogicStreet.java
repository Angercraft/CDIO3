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
        if (buildings > 0) {
            return value * buildings;
        }
        return value;
    }

    public void addBuilding() {
        buildings++;
    }

    public int getBuildings() {
        return buildings;
    }
}

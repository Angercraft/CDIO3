package juniorMatador;

import java.awt.*;

public class LogicStreet extends LogicField {

    private int value;
    private Color color;
    private String owner;
    private int buildings;

    public LogicStreet(int fieldNo, int value, Color color) {
        super(fieldNo, "STREET");
        this.value = value;
        this.color = color;
    }

    /**
     * Sets an object Player as the owner of the field.
     * @param owner an object of Player, which is connected as the owner.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
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

    public void setBuildings(int number) {
        if (number >= 0) {
            buildings = number;
        } else {
            buildings = 0;
        }
    }

    public void addBuilding() {
        buildings++;
    }

    public int getBuildings() {
        return buildings;
    }
}

package juniorMatador;

import java.awt.*;

public class ChanceFreeField extends ChanceBase {

    private Color color1;
    private Color color2;

    ChanceFreeField(String message, String type, Color color) {
        super(message, type);
        this.color1 = color;
    }

    ChanceFreeField(String message, String type, Color color1, Color color2) {
        super(message, type);
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }
}

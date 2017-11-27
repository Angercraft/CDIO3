package juniorMatador;

import java.awt.*;
import java.util.ArrayList;

public class FieldController {

    private LogicField[] fields;

    FieldController() {
        fields = new LogicField[24];
        createFields();
        ((LogicGoToJail)fields[18]).findJail(fields);
    }

    private void createFields() {
        fields[0] = new LogicStart(0);
        fields[1] = new LogicStreet(1,1,Color.ORANGE);
        fields[2] = new LogicStreet(2,1,Color.ORANGE);
        fields[3] = new LogicChance(3);
        fields[4] = new LogicStreet(4,1,Color.BLUE.brighter());
        fields[5] = new LogicStreet(5,1,Color.BLUE.brighter());
        fields[6] = new LogicVisitJail(6);
        fields[7] = new LogicStreet(7,2,Color.PINK);
        fields[8] = new LogicStreet(8,2,Color.PINK);
        fields[9] = new LogicChance(9);
        fields[10] = new LogicStreet(10,2,Color.YELLOW.brighter());
        fields[11] = new LogicStreet(11,2,Color.YELLOW.brighter());
        fields[12] = new LogicParking(12);
        fields[13] = new LogicStreet(13,3,Color.RED);
        fields[14] = new LogicStreet(14,3,Color.RED);
        fields[15] = new LogicChance(15);
        fields[16] = new LogicStreet(16,3,Color.YELLOW);
        fields[17] = new LogicStreet(17,3,Color.YELLOW);
        fields[18] = new LogicGoToJail(18);
        fields[19] = new LogicStreet(19,4,Color.GREEN);
        fields[20] = new LogicStreet(20,4,Color.GREEN);
        fields[21] = new LogicChance(21);
        fields[22] = new LogicStreet(22,5,Color.BLUE.darker());
        fields[23] = new LogicStreet(23,5,Color.BLUE.darker());
    }

    public LogicField getField(int value) {
        return fields[value];
    }

}

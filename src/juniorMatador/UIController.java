package juniorMatador;

import gui_fields.GUI_Field;
import gui_fields.GUI_Car;
import gui_fields.*;
import gui_main.GUI;
import java.awt.Color;

public class UIController {

    private GUI_Field[] uifields;
    private GUI gui;
    private GUI_Player[] uiPlayers;

    UIController() {
        uifields = fields();
        gui = new GUI(uifields);
    }

    /**
     * Prepares the GUI board. Adds all GUI_Players to the first field and sets the dice to show them on the GUI.
     */
    public void setupUI() {
        for (GUI_Player uiPlayer : uiPlayers) {
            uifields[0].setCar(uiPlayer, true);
        }
        System.out.println("Done setting players.");
        gui.setDice(3, 5);
    }

    /**
     * Creates a GUI_Player objects, which is a visual on the GUI, from an object of type Player.
     * @param player an object of type Player. Will be added as GUI_Player.
     * @param amountOfPlayers the number of players, which is the size of the array. Only used if the array is not initialized.
     */
    public void addUIPlayer(Player player, int amountOfPlayers) {
        if (uiPlayers == null) {
            uiPlayers = new GUI_Player[amountOfPlayers];
        }
        uiPlayers[player.getPlayerNumber()] = new GUI_Player(player.getName(), player.money.getAmount(), requestVehicleType());
        gui.addPlayer(uiPlayers[player.getPlayerNumber()]);
        updatePlayerBalance(player);
        System.out.println("Player added to UI. At: "+player.getPlayerNumber());
    }

    /**
     * Changes the visual balance for the player on the GUI.
     * @param player an object of type Player.
     */
    public void updatePlayerBalance(Player player) {
        uiPlayers[player.getPlayerNumber()].setBalance(player.money.getAmount());
    }

    /**
     * Changes the players vehicle position on the GUI.
     * @param player an object of the type Player.
     * @param sum the number of fields the player moves forward on the board.
     */
    public void updatePlayerPosition(Player player, int sum) {
        int currentField = player.getPlayerPos();
        System.out.println("currentField: "+currentField);
        int newField;
        if (currentField + sum > 39) {
            newField = currentField + sum - uifields.length;
        } else {
            newField = currentField+sum;
            System.out.println("New field is: "+newField);
        }
        uifields[currentField].setCar(uiPlayers[player.getPlayerNumber()], false);
        uifields[newField].setCar(uiPlayers[player.getPlayerNumber()], true);
        player.setPlayerPos(newField);
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Creates the button for dice rolls and sets new dice.
     * @param value1 value for the first dice face.
     * @param value2 value for the second dice face.
     */
    public void setUIDice(int value1, int value2) {
        gui.getUserButtonPressed("", "Roll dice");
        gui.setDice(value1, value2);
    }

    /**
     * Lets the user input an integer the system can interprit. A pre defined message will be displayed alongside the text field.
     * @return int
     */
    public int requestNumberOfPlayers() {
        return gui.getUserInteger("Choose number of players.", 2, 4);
    }

    /**
     * Lets the user input a String message the system can interpret.
     * @param message - Message the GUI will display along with the text field.
     * @return String
     */
    public String requestStringInput(String message) {
        return gui.getUserString(message);
    }

    /**
     * Lets user use the GUI, to select one of four GUI_Car types.
     * @return GUI_Car with defined Type
     */
    public GUI_Car requestVehicleType() {
        GUI_Car car;
        String vehicle = gui.getUserSelection("What vehicle would you like?", "Car", "Racecar", "Tractor", "UFO");
        Color color = requestVehicleColor();
        switch (vehicle) {
            case "car":
                return car = new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            case "Racecar":
                return car = new GUI_Car(color, color, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);
            case "Tractor":
                return car = new GUI_Car(color, color, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.FILL);
            case "UFO":
                return car = new GUI_Car(color, color, GUI_Car.Type.UFO, GUI_Car.Pattern.FILL);
            default:
                return car = new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        }
    }

    /**
     * Lets the user choose one of four colors through the GUI.
     * @return Color
     */
    public Color requestVehicleColor() {
        String selection = gui.getUserSelection("What color would you like the vehicle in?", "Blue", "Red", "Green", "Yellow");
        switch (selection) {
            case "Blue":
                return Color.BLUE;
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.BLUE;
        }
    }

    public GUI_Field[] fields() {
        GUI_Field[] fields = new GUI_Field[31];
        fields[0]= new GUI_Start();
        fields[1]= new Chance(Color.WHITE, "Chance",1);
        fields[2]= new PlayerOwnedAttraction(Color.MAGENTA, "Ballonboden",1,2);
        fields[3]= new PlayerOwnedAttraction(Color.MAGENTA, "Candy Floss",1,3);
        fields[4]= new Chance(Color.WHITE, "Chance",4);
        fields[5]= new XtraTurnField(Color.YELLOW, "Tog",5);
        fields[6]= new PlayerOwnedAttraction(Color.BLUE, "Dukkeforestillingen",2,6);
        fields[7]= new PlayerOwnedAttraction(Color.BLUE,"Trylleshowet",2,7);
        fields[8]= new Attraction(Color.WHITE, "Betal 2kr for at se Fyrværkeriet",8,2);
        fields[9]= new Chance(Color.WHITE, "Chance",9);
        fields[10]= new Field(Color.WHITE, "På besøg i Caféen",10);
        fields[11]= new PlayerOwnedAttraction(Color.PINK, "Karrusellen",2,11);
        fields[12]= new PlayerOwnedAttraction(Color.PINK, "Robådene",2,12);
        fields[13]= new XtraTurnField(Color.GREEN, "Tog",13);
        fields[14]= new PlayerOwnedAttraction(Color.ORANGE, "Vandrutschebanen",3,14);
        fields[15]= new PlayerOwnedAttraction(Color.ORANGE, "Minigolf Banen",3,15);
        fields[16]= new GetMoney(Color.WHITE, "Onkel Mangepenges byttepenge",16);
        fields[17]= new Chance(Color.WHITE, "Chance",17);
        fields[18]= new PlayerOwnedAttraction(Color.RED, "Videoarkaden",3,18);
        fields[19]= new PlayerOwnedAttraction(Color.RED, "Spøgelsestoget",3,19);
        fields[20]= new Chance(Color.WHITE, "Chance",20);
        fields[21]= new XtraTurnField(Color.BLUE.brighter(), "Tog",21);
        fields[22]= new PlayerOwnedAttraction(Color.YELLOW.darker(), "Helikoptertur",4,22);
        fields[23]= new PlayerOwnedAttraction(Color.YELLOW.darker(),"Ponyridning",4,23);
        fields[24]= new Attraction(Color.WHITE, "Betal 2kr for at delfinerne",24,2);
        fields[25]= new Chance(Color.WHITE, "Chance",25);
        fields[26]= new PlayerOwnedAttraction(Color.GREEN, "Radiobilerne",4,26);
        fields[27]= new PlayerOwnedAttraction(Color.GREEN, "Pariserhjulet",4,27);
        fields[28]= new XtraTurnField(Color.RED, "Tog",28);
        fields[29]= new PlayerOwnedAttraction(Color.BLUE.darker(),"Svingkarusellen",5, 29);
        fields[30]= new PlayerOwnedAttraction(Color.BLUE.darker(), "Rutschebanene",5,30);
        return fields;
    }
}
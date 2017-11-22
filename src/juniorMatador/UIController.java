package juniorMatador;

import com.sun.xml.internal.bind.v2.TODO;
import gui_fields.GUI_Field;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.awt.Color;

public class UIController {

    private GUI gui = new GUI();
    private GUI_Field[] fields;
    private GUI_Player[] uiPlayers;

    //Method for initial setup.
    public void setupUI() {
        System.out.println("Setting up UI. GUI_Player length is: "+uiPlayers.length);
        fields = gui.getFields();
        System.out.println("Fields length is: "+fields.length);
        for (int i = 0 ; i < uiPlayers.length ; i++) {
            fields[0].setCar(uiPlayers[i], true);
        }
        System.out.println("Done setting players.");
        gui.setDice(3, 5);
    }

    public void addUIPlayer(Player player, int amountOfPlayers) {
        if (uiPlayers == null) {
            uiPlayers = new GUI_Player[amountOfPlayers];
        }
        uiPlayers[player.getPlayerNumber()] = new GUI_Player(player.getName(), player.money.getAmount(), requestVehicleType());
        gui.addPlayer(uiPlayers[player.getPlayerNumber()]);
        System.out.println("Player added to UI. At: "+player.getPlayerNumber());
    }

    /*
    public void setupUIPlayers(Player[] player) {
        String playername;
        int playerMoney;
        uiPlayers = new GUI_Player[player.length];
        for (int i = 0 ; i < uiPlayers.length ; i++) {
            playername = player[i].getName();
            playerMoney = player[i].money.getAmount();
            uiPlayers[i] = new GUI_Player(playername, playerMoney, requestVehicleType());
            gui.addPlayer(uiPlayers[i]);
        }
    }
    */

    //TODO Lav det her om. Ikke array, men kun Player player.
    public void updatePlayerBalance(Player[] player) {
        for(int i = 0 ; i < player.length ; i++) {
            uiPlayers[i].setBalance(player[i].money.getAmount());
        }

    }

    public void updatePlayerPosition(Player player, int sum) {
        int currentField = player.getPlayerPos();
        System.out.println("currentField: "+currentField);
        int newField;
        if (currentField + sum > 39) {
            newField = currentField + sum - fields.length;
        } else {
            newField = currentField+sum;
            System.out.println("New field is: "+newField);
        }
        fields[currentField].setCar(uiPlayers[player.getPlayerNumber()], false);
        fields[newField].setCar(uiPlayers[player.getPlayerNumber()], true);
        player.setPlayerPos(newField);
        System.out.println("-------------------------------------------------------------------------");


    }

    //Creates the button for dicerolls and sets new dice.
    public void setUIlDice(int value1, int value2) {
        gui.getUserButtonPressed("", "Roll dice");
        gui.setDice(value1, value2);
    }

    public int requestNumberOfPlayers() {
        return gui.getUserInteger("Choose number of players.", 2, 4);
    }

    public String requestStringInput(String message) {
        return gui.getUserString(message);
    }

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
}
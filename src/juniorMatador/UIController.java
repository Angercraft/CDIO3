package juniorMatador;

import gui_fields.GUI_Field;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.awt.Color;

public class UIController {

    private GUI gui = new GUI();
    private GUI_Field[] fields;
    //private GUI_Player player1 = new GUI_Player("John Rambo", 1000);
    private GUI_Player[] uiPlayers;

    //Method for initial setup.
    public void setupUI() {
        fields = gui.getFields();
        for (int i = 0 ; i < uiPlayers.length ; i++) {
            fields[0].setCar(uiPlayers[i], true);
        }
        gui.setDice(3, 5);
    }

    public void setupuiPlayers(Player[] player) {
        String playername;
        int playerMoney;
        System.out.println(player.length);
        uiPlayers = new GUI_Player[player.length];
        for (int i = 0 ; i < uiPlayers.length ; i++) {
            playername = player[i].getName();
            playerMoney = player[i].money.getAmount();
            uiPlayers[i] = new GUI_Player(playername, playerMoney, requestVehicleType());
            gui.addPlayer(uiPlayers[i]);
        }
    }

    public void updatePlayers(Player[] player) {
        for(int i = 0 ; i < player.length ; i++) {
            uiPlayers[i].setBalance(player[i].money.getAmount());

        }

    }

    public void updatePlayerPosition(Player player, int sum) {
        int currentField = checkPlayerPosition();
        System.out.println("currentField: "+currentField);
        int newField;
        if (currentField + sum > 39) {
            newField = currentField + sum - fields.length;
        } else {
            newField = currentField+sum;
        }
        System.out.println("newField: "+newField);
        removeCar(currentField);
        fields[newField].setCar(uiPlayers[player.getPlayerNumber()], true);


    }

    public int checkPlayerPosition() {
        int playerPos;
        for (GUI_Field field : fields) {
            if (field.hasCar(uiPlayers[0])) {
                playerPos = field.getNumber();
                return playerPos;
            }
        }
        return 0;
    }



    //Creates the button for dicerolls and sets new dice.
    public void rollDice(int value1, int value2) {
        gui.getUserButtonPressed("", "Roll dice");
        gui.setDice(value1, value2);
    }

    //Removes car from its current field and sets the car in a new specified location.
    public void setCar(int field) {

    }

    /*
    //Removes car from its current field and sets the car in a new specified location.
    //TODO fix this!
    public void setCar(int field) {
        int fieldnumb = field;
        //System.out.println("Arraylocation1 "+fieldnumb);
        removeCar();

        if (fieldnumb>39) {
            fieldnumb = fieldnumb-40;
        }

        fields[fieldnumb].setCar(uiPlayers[0], true);
        //DEBUG PRINT
        System.out.println("Arraylocation2 "+fieldnumb);
    }
    */

    //Remove cars
    public void removeCar(int fieldnumb) {
        fields[fieldnumb].setCar(uiPlayers[0], false);
    }

    /*
    //Checks if there is currently a car placed on a field and returns
    //the array number if a car is found.
    //TODO needs fixing.
    public int checkForCar() {
        int arrayNumber = 0;
        for(int i = 0 ; i < fields.length ; i++) {
            if (fields[i].hasCar(uiPlayers[0])) {
                arrayNumber = fields[i].getNumber()-1;
            }
        }
        //DEBUG PRINT
        System.out.println("Car found:"+(arrayNumber));

        return arrayNumber;
    }
    */

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

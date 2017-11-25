package juniorMatador;

import gui_fields.GUI_Field;
import gui_fields.GUI_Car;
import gui_fields.*;
import gui_main.GUI;
import java.awt.Color;
import java.util.Random;

public class UIController {

    private GUI_Field[] uiFields;
    private GUI gui;
    private GUI_Player[] uiPlayers;

    UIController() {
        uiFields = fields();
        gui = new GUI(uiFields);
    }

    /**
     * Prepares the GUI board. Adds all GUI_Players to the first field and sets the dice to show them on the GUI.
     */
    public void setupUI() {
        Random rand = new Random();
        for (GUI_Player uiPlayer : uiPlayers) {
            uiFields[0].setCar(uiPlayer, true);
        }
        System.out.println("Done setting players.");
        gui.setDie(rand.nextInt(6)+1);
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
     * @param value the number of fields the player moves forward on the board.
     */
    public void updatePlayerPosition(Player player, int value) {
        int currentField = player.getPlayerPos();
        System.out.println("currentField: "+currentField);
        int newField;
        if (currentField + value > uiFields.length-1) {
            newField = currentField + value - uiFields.length;
        } else {
            newField = currentField+value;
            System.out.println("New field is: "+newField);
        }
        uiFields[currentField].setCar(uiPlayers[player.getPlayerNumber()], false);
        uiFields[newField].setCar(uiPlayers[player.getPlayerNumber()], true);
        player.setPlayerPos(newField);
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Creates the button for die roll and sets new die.
     * @param value value for the die face.
     */
    public void setUIDie(int value) {
        gui.getUserButtonPressed("", "Roll dice");
        gui.setDie(value);
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

    public String playerCanBuy() {
        return gui.getUserSelection("Would you like to buy this property?", "Yes", "No");
    }

    public void writeMessage(String message) {
        gui.showMessage(message);
    }

    /**
     * Lets user use the GUI, to select one of four GUI_Car types.
     * @return GUI_Car with defined Type
     */
    public GUI_Car requestVehicleType() {
        String vehicle = gui.getUserSelection("What vehicle would you like?", "Car", "Racecar", "Tractor", "UFO");
        Color color = requestVehicleColor();
        switch (vehicle) {
            case "car":
                return new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            case "Racecar":
                return new GUI_Car(color, color, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);
            case "Tractor":
                return new GUI_Car(color, color, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.FILL);
            case "UFO":
                return new GUI_Car(color, color, GUI_Car.Type.UFO, GUI_Car.Pattern.FILL);
            default:
                return new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
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
        GUI_Field[] fields = new GUI_Field[24];
        fields[0] = new GUI_Start("Start", "", "Every time you pass Start, you recieve 2 kr.", Color.GREEN, Color.BLACK);
        fields[1] = new GUI_Street("Burgerbaren", "", "Få en burger på parkens bedste burgerbar. ('Bedste' nomineret af egen resturant.)", "1", Color.YELLOW.darker(), Color.BLACK);
        fields[2] = new GUI_Street("Pizzariaet", "", "Hvis munden nu ikke er stor nok til den burger.", "1", Color.YELLOW.darker(), Color.BLACK);
        fields[3] = new GUI_Chance("?", "", "Træk et chancekort og følg dets anvisninger.", Color.WHITE, Color.BLACK);
        fields[4] = new GUI_Street("Slikbutikken", "", "Til den lille slikmund.", "1", Color.BLUE.brighter(), Color.BLACK);
        fields[5] = new GUI_Street("Iskiosken", "", "Hjælper når solen er fremme.", "1", Color.BLUE.brighter(), Color.BLACK);
        fields[6] = new GUI_Jail(); fields[6].setSubText("");
        fields[7] = new GUI_Street("Museet", "", "Både sjovt og lærerigt.", "2", Color.PINK, Color.BLACK);
        fields[8] = new GUI_Street("Biblioteket", "", "Lærerigt, men ikke så sjovt som museet.", "2", Color.PINK, Color.BLACK);
        fields[9] = new GUI_Chance("?", "", "Træk et chancekort og følg dets anvisninger.", Color.WHITE, Color.BLACK);
        fields[10] = new GUI_Street("Skaterparken", "", "Husk nu hjelmen.", "2", Color.YELLOW.brighter(), Color.BLACK);
        fields[11] = new GUI_Street("Svømmingpoolen", "", "Ikke løbe ved poolen!.", "2", Color.YELLOW.brighter(), Color.BLACK);
        fields[12] = new GUI_Refuge(); fields[12].setTitle("Gratis parkering"); fields[12].setSubText("");
        fields[13] = new GUI_Street("Spillehallen", "", "Forældrenes undskyldning for deres ludomani.", "3", Color.RED, Color.BLACK);
        fields[14] = new GUI_Street("Biografen", "", "Brug tid sammen med familien, uden at snakke med dem.", "3", Color.RED, Color.BLACK);
        fields[15] = new GUI_Chance("?", "", "Træk et chancekort og følg dets anvisninger.", Color.WHITE, Color.BLACK);
        fields[16] = new GUI_Street("Legetøjsbutikken", "", "Hvem siger man ikke kan købe kærlighed.", "3", Color.YELLOW, Color.BLACK);
        fields[17] = new GUI_Street("Dyrehandlen", "", "Hvis familien nu ikke er stor nok.", "3", Color.YELLOW, Color.BLACK);
        fields[18] = new GUI_Shipping(); fields[18].setTitle("Gå i fængsel"); fields[18].setSubText("");
        fields[19] = new GUI_Street("Bowlinghallen", "", "Når far skal vise sig.", "4", Color.GREEN.darker(), Color.BLACK);
        fields[20] = new GUI_Street("Zoo", "", "Det eneste sted dine forældre tillader dyr.", "4", Color.GREEN.darker(), Color.BLACK);
        fields[21] = new GUI_Chance("?", "", "Træk et chancekort og følg dets anvisninger.", Color.WHITE, Color.BLACK);
        fields[22] = new GUI_Street("Vandlandet", "", "Samme som poolen, bare dyrer.", "5", Color.BLUE.darker(), Color.WHITE);
        fields[23] = new GUI_Street("Strandpromenaden", "", "Sand. Alle. Steder.", "5", Color.BLUE.darker(), Color.WHITE);
        return fields;
    }
}
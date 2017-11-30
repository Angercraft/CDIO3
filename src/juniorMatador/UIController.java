package juniorMatador;

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
        uiPlayers[player.getPlayerNumber()] = new GUI_Player(player.getName(), player.getMoney().getAmount(), requestVehicleType(player.getPlayerNumber()));
        gui.addPlayer(uiPlayers[player.getPlayerNumber()]);
        updatePlayerBalance(player);
    }

    /**
     * Changes the visual balance for the player on the GUI.
     * @param player an object of type Player.
     */
    public void updatePlayerBalance(Player player) {
        uiPlayers[player.getPlayerNumber()].setBalance(player.getMoney().getAmount());
    }

    /**
     * Removes the car from the players old field, and sets it to the players new field.
     * @param playerNum the player number of the player you want moved.
     * @param newPlayerPos the position to set the player to.
     * @param oldPlayerPos the position to remove the player from.
     */
    public void setPlayerPos(int playerNum, int newPlayerPos, int oldPlayerPos) {
        uiFields[oldPlayerPos].setCar(uiPlayers[playerNum], false);
        uiFields[newPlayerPos].setCar(uiPlayers[playerNum], true);
    }

    /**
     * Used when starting new game. Will reset all players position to start and set all their money to the start money.
     */
    public void resetUIPlayers(int startBalance) {
        for (GUI_Field field : uiFields) {
            field.removeAllCars();
        }
        for (GUI_Player player : uiPlayers) {
            uiFields[0].setCar(player, true);
            player.setBalance(startBalance);
        }
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
     * Moves the players vehicle from the GUI to the jail field.
     * @param player the player who will be moved.
     * @param jail the field which functions as the jail.
     */
    public void jailPlayer(Player player, int jail) {
        int current = player.getPlayerPos();
        uiFields[current].setCar(uiPlayers[player.getPlayerNumber()],false);
        uiFields[jail].setCar(uiPlayers[player.getPlayerNumber()],true);
    }

    /**
     * Displays a message in the middle of the board, with black text on white background.
     * @param message the message you would like displayed on the card.
     */
    public void displayChanceCard(String message) {
        gui.displayChanceCard(message);
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
     * Creates a bottom and dropdown menu on the board, with the message provided, and the options provided. Returns a String for the users choice.
     * @param message the message which will be written to the players.
     * @param options the list of options for the dropdown menu.
     * @return a String with the players choice.
     */
    public String requestPlayerChoice(String message, String... options) {
        return gui.getUserSelection(message, options);
    }

    /**
     * Writes a message on the GUI.
     * @param message the String which will be written.
     */
    public void writeMessage(String message) {
        gui.showMessage(message);
    }

    /**
     * Lets user use the GUI, to select one of four GUI_Car types.
     * @return GUI_Car with defined Type
     */
    public GUI_Car requestVehicleType(int playerNum) {
        String vehicle = gui.getUserSelection("Hvad køretøj ønsker du?", "Bil", "Racerbil", "Traktor", "UFO");
        Color color = getVehicleColor(playerNum);
        switch (vehicle) {
            case "Bil":
                return new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            case "Racerbil":
                return new GUI_Car(color, color, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);
            case "Traktor":
                return new GUI_Car(color, color, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.FILL);
            case "UFO":
                return new GUI_Car(color, color, GUI_Car.Type.UFO, GUI_Car.Pattern.FILL);
            default:
                return new GUI_Car(color, color, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        }
    }

    /**
     * Returns the color depending on what number the player is.
     * @return Color
     */
    public Color getVehicleColor(int playerNum) {
        switch (playerNum) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            default:
                return Color.BLUE;
        }
    }

    /**
     * Closes the GUI and exits the program.
     */
    public void closeUI() {
        System.exit(0);
    }

    /**
     * Creates all the fields on the board from hard coded information.
     * @return Returns an array of GUI_Field objects, size 24.
     */
    public GUI_Field[] fields() {
        GUI_Field[] fields = new GUI_Field[24];
        fields[0] = new GUI_Start("Start", "", "Hver gang du passerer start, modtager du 2 kr.", Color.GREEN.darker(), Color.BLACK);
        fields[1] = new GUI_Street("Burgerbaren", "", "Få en burger på parkens bedste burgerbar. ('Bedste' nomineret af egen resturant.)", "1", Color.ORANGE, Color.BLACK);
        fields[2] = new GUI_Street("Pizzariaet", "", "Hvis munden nu ikke er stor nok til den burger.", "1", Color.ORANGE, Color.BLACK);
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
        fields[19] = new GUI_Street("Bowlinghallen", "", "Når far skal vise sig.", "4", Color.GREEN, Color.BLACK);
        fields[20] = new GUI_Street("Zoo", "", "Det eneste sted dine forældre tillader dyr.", "4", Color.GREEN, Color.BLACK);
        fields[21] = new GUI_Chance("?", "", "Træk et chancekort og følg dets anvisninger.", Color.WHITE, Color.BLACK);
        fields[22] = new GUI_Street("Vandlandet", "", "Samme som poolen, bare dyrer.", "5", Color.BLUE.darker(), Color.WHITE);
        fields[23] = new GUI_Street("Strandpromenaden", "", "Sand. Alle. Steder.", "5", Color.BLUE.darker(), Color.WHITE);
        return fields;
    }
}
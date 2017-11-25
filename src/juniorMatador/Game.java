package juniorMatador;

import java.util.Random;

public class Game {

    UIController uiController = new UIController();
    Player[] player;
    Die die = new Die();
    FieldController fields = new FieldController();

    /**
     * Method to start it all. Prepares the activePlayer, runs the setupGame method and runs a loop which uses rollDice method for the active player and changes the active player to the next in line.
     */
    public void playGame() {
        Player activePlayer;
        setupGame();
        activePlayer = startPlayer();
        loop: while (true) {
            rollDice(activePlayer);
            updatePlayer(activePlayer);
            activePlayer = changePlayer();
        }
    }

    /**
     * Runs the setupPlayers method and setupUI from the UIController object.
     */
    private void setupGame() {
        setupPlayers();
        uiController.setupUI();
    }

    /**
     * Creates an object of the type Player and adds it to an array of Player objects.
     */
    private void setupPlayers() {
        String playerName;
        int number = uiController.requestNumberOfPlayers();
        player = new Player[number];
        for (int i = 0 ; i < number ; i++) {
            playerName = uiController.requestStringInput("Player "+(i+1)+" please input name.");
            if (playerName.equals("")) {
                playerName = "Player"+(i+1);
            }
            player[i] = new Player(playerName, i);
            uiController.addUIPlayer(player[i], number);
        }
        System.out.println("SetupPlayers completed.");
    }

    /**
     * Rolls the dice from the object die. Runs setUIDice with the two face values from object die, and updates the player position on the GUI, with the sum of the face values.
     * @param player an object of the type Player. The player which you want to play for.
     */
    private void rollDice(Player player) {
        uiController.setUIDie(die.roll());
        uiController.updatePlayerPosition(player, die.getFace());
    }

    public void updatePlayer(Player player) {
        LogicField field;
        field = fields.getField(player.getPlayerPos());
        String type = field.getType();
        switch (type) {
            case "STREET":
                streetFieldEffect(player, (LogicStreet) field);
                break;
            case "GOTOJAIL":
                goToJailFieldEffect(player, (LogicGoToJail) field);
                break;
            case "CHANCE":
                chanceFieldEffect(player, (LogicChance) field);
                break;
            case "PARKING":
                parkingFieldEffect(player, (LogicParking) field);
                break;
        }

    }

    private void parkingFieldEffect(Player player, LogicParking field) {
        player.money.addAmount(field.getValue());
        field.addValue(-field.getValue());
    }

    private void chanceFieldEffect(Player player, LogicChance field) {
    }

    private void goToJailFieldEffect(Player player, LogicGoToJail field) {
        uiController.writeMessage("You go to jail and pays a ticket of 3 kr.");
        uiController.jailPlayer(player, field.getJailLocation());
        player.setPlayerPos(field.getJailLocation());
        player.money.addAmount(-field.getTicket());
        ((LogicParking)fields.getField(12)).addValue(field.getTicket());
        uiController.updatePlayerBalance(player);
    }

    public void streetFieldEffect(Player player, LogicStreet field) {
        if (field.getOwner() == null) {
            if (uiController.requestPlayerChoice("Would you like to buy this property?").equals("Yes")) {
                player.money.addAmount(-field.getRent());
                field.setOwner(player);
            }
        } else if (field.getOwner() == player) {
            if (uiController.requestPlayerChoice("You have "+field.getBuildings()+" buildings on this field. Would you like to buy some for 2 kr. a piece?", "No", "Yes").equals("Yes")) {
                int choice = Integer.parseInt(uiController.requestPlayerChoice("How many would you like to have on the field?", "1", "2", "3"));
                field.setBuildings(choice);
            }
        } else {
            uiController.writeMessage(field.getOwner().getName()+" already owns that place. You pay "+field.getRent()+" in rent to them.");
            player.money.addAmount(-field.getRent());
            field.getOwner().money.addAmount(field.getRent());
        }
    }

    /**
     * Determines at random which player will go first.
     * @return Returns an object of Player for which player is the first to move.
     */
    public Player startPlayer() {
        Random rand = new Random();
        int numOfPlayers = player.length;
        int startPlayer = rand.nextInt(numOfPlayers);
        System.out.println("Player "+(startPlayer+1)+" is first.");
        player[startPlayer].setPlayerTurn(true);
        return player[startPlayer];
    }

    /**
     * Changes the active players playerTurn to false, and selects the next player in line to be the active player.
     * @return the next Player in the array of Player objects.
     */
    public Player changePlayer() {
        for (int i = 0 ; i < player.length ; i++) {
            if (player[i].getPlayerTurn()) {
                player[i].setPlayerTurn(false);
                if (i == player.length-1) {
                    player[0].setPlayerTurn(true);
                    return player[0];
                } else {
                    player[i+1].setPlayerTurn(true);
                    return player[i+1];
                }
            }
        }
        System.out.println("ERROR: No active player was found.");
        return null;
    }
}

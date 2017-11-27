package juniorMatador;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private UIController uiController = new UIController();
    private Player[] player;
    private Die die = new Die();
    private FieldController fields = new FieldController();

    /**
     * Method to start it all. Prepares the activePlayer, runs the setupGame method and runs a loop which uses rollDice method for the active player and changes the active player to the next in line.
     */
    public void playGame() {
        Player activePlayer;
        setupGame();
        activePlayer = startPlayer();
        while (true) {
            rollDice(activePlayer);
            updatePlayer(activePlayer);
            if (checkWinner()) {
                if (!checkNewGame()) {
                    break;
                }
                newGame();
                activePlayer = startPlayer();
                uiController.writeMessage("First player is " + activePlayer.getName());
            }
            activePlayer = changePlayer();
        }
        uiController.closeUI();
    }

    public boolean checkWinner() {
        for (Player player: player) {
            if (player.getMoney().getAmount() == 0) {
                Player winner = getWinner();
                uiController.writeMessage(player.getName()+" is broke. The winner is "+winner.getName()+" with "+winner.getMoney().getAmount()+" kr.");
                return true;
            }
        }
        return false;
    }

    public Player getWinner() {
        Player[] sortByWinner = new Player[player.length];
        int[] scores = new int[player.length];
        for (int i = 0 ; i < scores.length ; i++) {
            scores[i] = player[i].getMoney().getAmount();
        }
        Arrays.sort(scores);
        for (int i = 0 ; i < scores.length ; i++) {
            for (int j = 0 ; j < player.length ; j++) {
                if (player[j].getMoney().getAmount() == scores[i]) {
                    sortByWinner[i] = player[j];
                }
            }
        }
        if (scores[0] == scores[1]) {
            Random rand = new Random();
            if (scores.length > 2) {
                if (scores[1] == scores[2]) {
                    uiController.writeMessage("3 winners found. Picking winner by random.");
                    return sortByWinner[rand.nextInt(3)+1];
                }
            }
            uiController.writeMessage("Both "+sortByWinner[0].getName()+" and "+sortByWinner[1].getName()+" has the same score. Picking one of them by random.");
            return sortByWinner[rand.nextInt(2)+1];
        }
        return sortByWinner[0];
    }

    public void newGame() {
        for (Player aPlayer : player) {
            aPlayer.reset();
            uiController.resetUIPlayers(aPlayer.getMoney().getAmount());
        }
    }

    public boolean checkNewGame() {
        String choice = uiController.requestPlayerChoice("Would you like to play again?", "No", "Yes");
        switch (choice) {
            case "Yes":
                return true;
            case "No":
                return false;
            default:
                return false;
        }
    }

    /**
     * Runs the setupPlayers method and setupUI from the UIController object.
     */
    public void setupGame() {
        setupPlayers();
        uiController.setupUI();
    }

    /**
     * Creates an object of the type Player and adds it to an array of Player objects.
     */
    public void setupPlayers() {
        String playerName;
        int number = uiController.requestNumberOfPlayers();
        player = new Player[number];
        for (int i = 0 ; i < number ; i++) {
            playerName = uiController.requestStringInput("Player "+(i+1)+" please input name.");
            if (playerName.equals("")) {
                playerName = "Player"+(i+1);
            }
            player[i] = new Player(playerName, i, 31, 10);
            uiController.addUIPlayer(player[i], number);
        }
        System.out.println("SetupPlayers completed.");
    }

    /**
     * Rolls the dice from the object die. Runs setUIDice with the two face values from object die, and updates the player position on the GUI, with the sum of the face values.
     * @param player an object of the type Player. The player which you want to play for.
     */
    public void rollDice(Player player) {
        uiController.setUIDie(die.roll());
        updatePlayerPos(player, die.getFace());
    }

    public void updatePlayer(Player player) {
        if (startPassed(player, die.getFace())) {
            player.getMoney().addAmount(3);
        }
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
        uiController.updatePlayerBalance(player);
    }

    public void updatePlayerPos(Player player, int sum) {
        int oldPos = player.getPlayerPos();
        int newPos = player.getPlayerPos() + sum;
        if (newPos > uiController.fields().length-1) newPos -= uiController.fields().length;
        player.setPlayerPos(newPos);
        uiController.setPlayerPos(player.getPlayerNumber(), newPos, oldPos);
    }

    public boolean startPassed(Player player, int value) {
        return player.getPlayerPos() + value > uiController.fields().length - 1;
    }

    public void parkingFieldEffect(Player player, LogicParking field) {
        if (field.getValue() > 0) {
            uiController.writeMessage("You got free parking and found some money. You recieve "+field.getValue()+" kr.");
            player.getMoney().addAmount(field.getValue());
            field.addValue(-field.getValue());
        }
    }

    public void chanceFieldEffect(Player player, LogicChance field) {
    }

    /**
     * Logic for what happens when a player lands on a field type object LogicGoToJail.
     * @param player the player which lands on the object field.
     * @param field the specific object of LogicGoToJail, the player lands on.
     */
    public void goToJailFieldEffect(Player player, LogicGoToJail field) {
        uiController.writeMessage("You go to jail and pays a ticket of 3 kr.");
        uiController.jailPlayer(player, field.getJailLocation());
        player.setPlayerPos(field.getJailLocation());
        player.getMoney().addAmount(-field.getTicket());
        ((LogicParking)fields.getField(12)).addValue(field.getTicket());
    }

    /**
     * Logic for what happens when a player lands on a field type object LogicStreet.
     * @param player the player which lands on the object field.
     * @param field the specific object of LogicStreet, the player lands on.
     */
    public void streetFieldEffect(Player player, LogicStreet field) {
        if (field.getOwner() == null) {
            if (uiController.requestPlayerChoice("Would you like to buy this property?", "No", "Yes").equals("Yes")) {
                player.getMoney().addAmount(-field.getRent());
                field.setOwner(player);
            }
        } else if (field.getOwner() == player && field.getBuildings() < 3) {
            if (uiController.requestPlayerChoice("You have "+field.getBuildings()+" buildings on this field. Would you like to buy one for 2 kr?", "No", "Yes").equals("Yes")) {
                if (player.getBuildings().getAmount() > 0) {
                    field.addBuilding();
                    player.getMoney().addAmount(-2);
                    player.getBuildings().addAmount(-2);
                } else {
                    uiController.writeMessage("You have "+player.getBuildings().getAmount()+" buildings and can't build.");
                }
            }
        } else {
            uiController.writeMessage(field.getOwner().getName()+" already owns that place. You pay "+field.getRent()+" in rent to them.");
            player.getMoney().addAmount(-field.getRent());
            field.getOwner().getMoney().addAmount(field.getRent());
            uiController.updatePlayerBalance(field.getOwner());
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
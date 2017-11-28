package juniorMatador;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Arrays;
import java.util.Random;

public class Game {

    private UIController uiController = new UIController();
    private Player[] players;
    private Die die = new Die();
    private FieldController fields = new FieldController();
    private ChanceController chanceCards = new ChanceController();

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
                uiController.writeMessage("Første spiller er " + activePlayer.getName());
            }
            activePlayer = changePlayer();
        }
        uiController.closeUI();
    }

    public boolean checkWinner() {
        for (Player player: players) {
            if (player.getMoney().getAmount() == 0) {
                Player winner = getWinner();
                uiController.writeMessage(player.getName()+" har ikke flere penge. Vinderen er "+winner.getName()+" med "+winner.getMoney().getAmount()+" kr.");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks each players score to find the one with the highest score. If more than one winner is found, one of them will be choosen at random.
     * @return object of type Player, who is the winner.
     */
    public Player getWinner() {
        Player[] sortByWinner = new Player[players.length];
        int[] scores = new int[players.length];
        for (int i = 0 ; i < scores.length ; i++) {
            scores[i] = players[i].getMoney().getAmount();
        }
        Arrays.sort(scores);
        for (int i = 0 ; i < scores.length ; i++) {
            for (int j = 0 ; j < players.length ; j++) {
                if (players[j].getMoney().getAmount() == scores[i]) {
                    sortByWinner[i] = players[j];
                }
            }
        }
        if (scores[0] == scores[1]) {
            Random rand = new Random();
            if (scores.length > 2) {
                if (scores[1] == scores[2]) {
                    uiController.writeMessage("3 vindere fundet. Vinderen vælges ved lodtrækning.");
                    return sortByWinner[rand.nextInt(3)+1];
                }
            }
            uiController.writeMessage("Både "+sortByWinner[0].getName()+" og "+sortByWinner[1].getName()+" har samme score. Vinderen bliver valgt ved lodtrækning.");
            return sortByWinner[rand.nextInt(2)+1];
        }
        return sortByWinner[0];
    }

    /**
     * Resets the game values to prepare for a new game.
     */
    public void newGame() {
        for (Player aPlayer : players) {
            aPlayer.reset();
            uiController.resetUIPlayers(aPlayer.getMoney().getAmount());
            for (int i = 0 ; i < fields.getFields().length ; i++) {
                String type = fields.getFields()[i].getType();
                if (type.equals("STREET")) {
                    ((LogicStreet)fields.getField(i)).setOwner(null);
                    ((LogicStreet)fields.getField(i)).setBuildings(0);
                }
            }
        }
    }

    /**
     * Checks if the players want to start a new game.
     * @return boolean value, true for new game, false to quit.
     */
    public boolean checkNewGame() {
        String choice = uiController.requestPlayerChoice("Vil i gerne spille igen?", "Nej", "Ja");
        switch (choice) {
            case "Ja":
                return true;
            case "Nej":
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
        players = new Player[number];
        for (int i = 0 ; i < number ; i++) {
            playerName = uiController.requestStringInput("Spiller "+(i+1)+", angiv venligst dit navn.");
            if (playerName.equals("")) {
                playerName = "Player"+(i+1);
            }
            players[i] = new Player(playerName, i, 31);
            uiController.addUIPlayer(players[i], number);
        }
    }

    /**
     * Rolls the dice from the object die. Runs setUIDice with the two face values from object die, and updates the player position on the GUI, with the sum of the face values.
     * @param player an object of the type Player. The player which you want to play for.
     */
    public void rollDice(Player player) {
        uiController.setUIDie(die.roll());
        updatePlayerPos(player, die.getFace());
    }

    /**
     * Updates the player, according to the current field.
     * @param player the active player.
     */
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
                chanceFieldEffect(player);
                break;
            case "PARKING":
                parkingFieldEffect(player, (LogicParking) field);
                break;
        }
        uiController.updatePlayerBalance(player);
    }

    /**
     * Updates the player position based on the die value.
     * @param player the active player, who will be moved.
     * @param sum the sum of all dice used in the game.
     */
    public void updatePlayerPos(Player player, int sum) {
        int oldPos = player.getPlayerPos();
        int newPos = player.getPlayerPos() + sum;
        if (newPos > uiController.fields().length-1) newPos -= uiController.fields().length;
        player.setPlayerPos(newPos);
        if (startPassed(oldPos, player.getPlayerPos())) {
            player.getMoney().addAmount(1000);
        }
        uiController.setPlayerPos(player.getPlayerNumber(), newPos, oldPos);
    }

    /**
     * Checks if the players current field value is larger than the next field value.
     * @param oldPos the players old field value.
     * @param newPos the players new field value.
     * @return true if the old field is bigger than the new, otherwise false.
     */
    public boolean startPassed(int oldPos, int newPos) {
        return newPos < oldPos;
    }

    /**
     * The effect which executes when a player lands on the parking field.
     * @param player the active player who landed on the field.
     * @param field the information for the field type LogicParking.
     */
    public void parkingFieldEffect(Player player, LogicParking field) {
        if (field.getValue() > 0) {
            uiController.writeMessage("Du fandt nogle penge på parkeringspladsen. Du modtager "+field.getValue()+" kr.");
            player.getMoney().addAmount(field.getValue());
            field.addValue(-field.getValue());
        }
    }

    /**
     * When a player lands on a chance field, a card is choosen at random. The type of the card determines what effect will be applied to the player.
     * @param player the active player who landed on the field.
     */
    public void chanceFieldEffect(Player player) {
        Random rand = new Random();
        int cardNum = rand.nextInt(14);
        String type = chanceCards.getCard(cardNum).getType();
        ChanceBase card = chanceCards.getCard(cardNum);
        die.setFace(0);
        switch (type) {
            case "START":
                chanceStart(player,(ChanceStart) card);
                break;
            case "MOVETO":
                chanceMoveTo(player, (ChanceMoveTo) card);
                break;
            case "FREEFIELD":
                chanceFreeField(player, (ChanceFreeField) card);
                break;
            case "MONEY":
                chanceMoney(player, (ChanceMoney) card);
                break;
            case "SKIPJAIL":
                chanceSkipJail(player);
                break;
            default:
        }
    }

    /**
     * The effect for the chance card type ChanceStart.
     * @param player the active player.
     * @param card the card which was chosen.
     */
    public void chanceStart(Player player, ChanceStart card) {
        uiController.displayChanceCard(card.getMessage());
        int stepsToStart = uiController.fields().length - player.getPlayerPos();
        updatePlayerPos(player, stepsToStart);
        player.getMoney().addAmount(7);
    }

    /**
     * The effect for the chance card type ChanceMoveTo.
     * @param player the active player.
     * @param card the card which was chosen.
     */
    public void chanceMoveTo(Player player, ChanceMoveTo card) {
        uiController.displayChanceCard(card.getMessage());
        if (card.getMoveAmount() == 24) {
            int stepsToField = uiController.fields().length - player.getPlayerPos() - 1;
            updatePlayerPos(player, stepsToField);
        } else {
            updatePlayerPos(player, card.getMoveAmount());
        }
        updatePlayer(player);
    }

    /**
     * The effect for the chance card type ChanceFreeFoeæd.
     * @param player the active player.
     * @param card the card which was chosen.
     */
    public void chanceFreeField(Player player, ChanceFreeField card) {
        uiController.displayChanceCard(card.getMessage());
        int fieldNum = 0;
        for (int i = player.getPlayerPos(); i < uiController.fields().length; i++) {
            if (fields.getField(i).getType().equals("STREET")) {
                LogicStreet field = (LogicStreet) fields.getField(i);
                if (field.getColor().equals(card.getColor1()) || field.getColor().equals(card.getColor2())) {
                    fieldNum = i;
                    break;
                }
            }
        }
        if (fieldNum == 0) {
            for (int i = 0; i < uiController.fields().length; i++) {
                if (fields.getField(i).getType().equals("STREET")) {
                    LogicStreet field = (LogicStreet) fields.getField(i);
                    if (field.getColor().equals(card.getColor1()) || field.getColor().equals(card.getColor2())) {
                        fieldNum = i;
                        break;
                    }
                }
            }
        }
        int stepsToField = uiController.fields().length - player.getPlayerPos() + fieldNum;
        updatePlayerPos(player, stepsToField);
        if (((LogicStreet)fields.getField(player.getPlayerPos())).getOwner() == null) {
            uiController.writeMessage("Feltet er ledigt og du er nu ejeren!");
            ((LogicStreet)fields.getField(player.getPlayerPos())).setOwner(player);
        } else {
            uiController.writeMessage(((LogicStreet)fields.getField(player.getPlayerPos())).getOwner().getName()+" ejer allerede dette felt og du skal betale leje.");
        }
    }

    /**
     * The effect for the chance card type ChanceMoney.
     * @param player the active player.
     * @param card the card which was chosen.
     */
    public void chanceMoney(Player player, ChanceMoney card) {
        if (card.getOthersPay()) {
            int amount = 0;
            for (Player aPlayer : players) {
                aPlayer.getMoney().addAmount(-1);
                amount++;
            }
            player.getMoney().addAmount(amount);
        } else {
            player.getMoney().addAmount(card.getAmount());
        }
    }

    /**
     * The effect for the chance card type ChanceSkipJail.
     * @param player the active player.
     */
    public void chanceSkipJail(Player player) {
        player.setSkipJail(true);
    }

    /**
     * Logic for what happens when a player lands on a field type object LogicGoToJail.
     * @param player the player which lands on the object field.
     * @param field the specific object of LogicGoToJail, the player lands on.
     */
    public void goToJailFieldEffect(Player player, LogicGoToJail field) {
        if (!player.getSkipJail()) {
            uiController.writeMessage("Du ryger i fængsel men betaler straffen på 3 kr.");
            uiController.jailPlayer(player, field.getJailLocation());
            player.setPlayerPos(field.getJailLocation());
            player.getMoney().addAmount(-field.getTicket());
            ((LogicParking)fields.getField(12)).addValue(field.getTicket());
        } else {
            uiController.writeMessage("Dit 'undgå fængsel's kort reddede dig fra fængsel.");
            player.setSkipJail(false);
        }
    }

    /**
     * Logic for what happens when a player lands on a field type object LogicStreet.
     * @param player the player which lands on the object field.
     * @param field the specific object of LogicStreet, the player lands on.
     */
    public void streetFieldEffect(Player player, LogicStreet field) {
        if (field.getOwner() == null && player.getMoney().getAmount() >= field.getRent()) {
            if (uiController.requestPlayerChoice("Vil du købe denne grund?", "Nej", "Ja").equals("Ja")) {
                player.getMoney().addAmount(-field.getRent());
                field.setOwner(player);            }
        } else if (field.getOwner() == player && field.getBuildings() < 3) {
            int buildingPrice = field.getRent()+1;
            if (uiController.requestPlayerChoice("Du har "+field.getBuildings()+" bygninger på dette felt. Vil du købe en for "+buildingPrice+" kr?", "Nej", "Ja").equals("Ja")) {
                field.addBuilding();
                player.getMoney().addAmount(-buildingPrice);
            }
        } else {
            uiController.writeMessage(field.getOwner().getName()+" ejer allerede dette felt. Du betaler "+field.getRent()+" kr. i leje.");
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
        int numOfPlayers = players.length;
        int startPlayer = rand.nextInt(numOfPlayers);
        System.out.println("Player "+(startPlayer+1)+" is first.");
        players[startPlayer].setPlayerTurn(true);
        return players[startPlayer];
    }

    /**
     * Changes the active players playerTurn to false, and selects the next player in line to be the active player.
     * @return the next Player in the array of Player objects.
     */
    public Player changePlayer() {
        for (int i = 0 ; i < players.length ; i++) {
            if (players[i].getPlayerTurn()) {
                players[i].setPlayerTurn(false);
                if (i == players.length-1) {
                    players[0].setPlayerTurn(true);
                    return players[0];
                } else {
                    players[i+1].setPlayerTurn(true);
                    return players[i+1];
                }
            }
        }
        System.out.println("ERROR: No active player was found.");
        return null;
    }
}
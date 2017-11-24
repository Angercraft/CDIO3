package juniorMatador;

import java.util.Random;

public class Game {

    UIController uiController = new UIController();
    Player[] player;
    Die die = new Die();
    FieldEffect fieldEffect = new FieldEffect();

    /**
     * Method to start it all. Prepares the activePlayer, runs the setupGame method and runs a loop which uses rollDice method for the active player and changes the active player to the next in line.
     */
    public void playGame() {
        Player activePlayer;
        setupGame();
        activePlayer = startPlayer();
        loop: while (true) {
            rollDice(activePlayer);
            fieldEffect.getFieldEffect(activePlayer);
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

package juniorMatador;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;

public class Game {
    UIController uiController = new UIController();
    Player[] player;
    Dies dies = new Dies();

    public void playGame() {
        Player activePlayer;
        setupGame();
        activePlayer = startPlayer();
        while (true) {
            rollDice(activePlayer);
            activePlayer = changePlayer();
        }
    }

    private void setupGame() {
        setupPlayers();
        uiController.setupUI();
    }

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

    private void rollDice(Player player) {
        dies.roll();
        uiController.setUIlDice(dies.getFace1(), dies.getFace2());
        uiController.updatePlayerPosition(player, dies.sum());
    }

    public Player startPlayer() {
        Random rand = new Random();
        int numOfPlayers = player.length;
        int startPlayer = rand.nextInt(numOfPlayers);
        System.out.println("Player "+(startPlayer+1)+" is first.");
        player[startPlayer].setPlayerTurn(true);
        return player[startPlayer];
    }

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

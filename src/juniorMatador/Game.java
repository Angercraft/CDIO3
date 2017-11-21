package juniorMatador;

public class Game {
    UIController uiController = new UIController();
    Player[] player;
    Dies dies = new Dies();
    //Die die1 = new Die();
    //Die die2 = new Die();

    public void playGame() {
        setupGame();
        while (true) {
            rollDice();
        }
    }

    private void setupGame() {
        setupPlayers();
        uiController.setupuiPlayers(player);
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
        }
    }

    private void rollDice() {
        dies.roll();
        uiController.rollDice(dies.getFace1(), dies.getFace2());
        uiController.updatePlayerPosition(player[0], dies.sum());
    }

    /*
    private void rollDice() {
        int playerField = uiController.checkForCar();
        System.out.println(playerField);
        dies.roll();
        uiController.rollDice(dies.getFace1(), dies.getFace2());
        System.out.println("test: "+playerField+" "+dies.sum());
        uiController.setCar(playerField+dies.sum());
        uiController.removeCar(playerField);
    }
    */
}

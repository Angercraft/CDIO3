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
        System.out.println(player.length);
        uiController.setupuiPlayers(player);
        uiController.setup();
    }

    private void setupPlayers() {
        String playerName;
        int number = uiController.requestNumberOfPlayers();
        for (int i = 0 ; i < number ; i++) {
            playerName = uiController.requestStringInput("Player "+(i+1)+" please input name.");
            if (playerName.equals("")) {
                playerName = "Player"+(i+1);
            }
            player[i] = new Player(playerName);
        }
    }

    private void rollDice() {
        dies.roll();
        uiController.rollDice(dies.getFace1(), dies.getFace2());
        uiController.setCar(uiController.checkForCar()+dies.getFace1()+dies.getFace2());

    }
}

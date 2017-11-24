package juniorMatador;

public class Game {
    UIController uiController = new UIController();
    Player[] player;
    Dies dies = new Dies();
    //Die die1 = new Die();
    //Die die2 = new Die();
    Field[] fields = new Field[30];


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

    public void setFields(){
        fields[0]= new Field("Start",0);
        fields[1]= new Chance("Chance",1);
        fields[2]= new PlayerOwnedAttraction("Ballonboden",1,"Lilla",2);
        fields[3]= new PlayerOwnedAttraction("Candy Floss",1,"Lilla",3);
        fields[4]= new Chance("Chance",4);
        fields[5]= new XtraTurnField("Tog",5,"Yellow");
        fields[6]= new PlayerOwnedAttraction("Dukkeforestillingen",2,"Lightblue",6);
        fields[7]= new PlayerOwnedAttraction("Trylleshowet",2,"Lightblue",7);
        fields[8]= new Attraction("Betal 2kr for at se Fyrværkeriet",8,2);
        fields[9]= new Chance("Chance",9);
        fields[10]= new Field("På besøg i Caféen",10);
        fields[11]= new PlayerOwnedAttraction("Karrusellen",2,"Pink",11);
        fields[12]= new PlayerOwnedAttraction("Robådene",2,"Pink",12);
        fields[13]= new XtraTurnField("Tog",13,"Green");
        fields[14]= new PlayerOwnedAttraction("Vandrutschebanen",3,"Orange",14);
        fields[15]= new PlayerOwnedAttraction("Minigolf Banen",3,"Orange",15);
        fields[16]= new GetMoney("Onkel Mangepenges byttepenge",16);
        fields[17]= new Chance("Chance",17);
        fields[18]= new PlayerOwnedAttraction("Videoarkaden",3,"Red",18);
        fields[19]= new PlayerOwnedAttraction("Spøgelsestoget",3,"Red",19);
        fields[20]= new Chance("Chance",20);
        fields[21]= new XtraTurnField("Tog",21,"Blue");
        fields[22]= new PlayerOwnedAttraction("Helikoptertur",4,"Yellow",22);
        fields[23]= new PlayerOwnedAttraction("Ponyridning",4,"Yellow",23);
        fields[24]= new Attraction("Betal 2kr for at delfinerne",24,2);
        fields[25]= new Chance("Chance",25);
        fields[26]= new PlayerOwnedAttraction("Radiobilerne",4,"Green",26);
        fields[27]= new PlayerOwnedAttraction("Pariserhjulet",4,"Green",27);
        fields[28]= new XtraTurnField("Tog",28,"Red");
        fields[29]= new PlayerOwnedAttraction("Svingkarusellen",5,"Blue",29);
        fields[30]= new PlayerOwnedAttraction("Rutschebanene",5,"Blue",30);




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

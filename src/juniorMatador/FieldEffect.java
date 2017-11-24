package juniorMatador;

import java.util.Arrays;

public class FieldEffect {

    private int[] street = {1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20, 22, 23};
    private int[] chance = {3, 9, 15, 21};
    private Player player;

    public void getFieldEffect(Player player) {
        int value = player.getPlayerPos();
        this.player = player;
        for (int aStreet : street) {
            if (aStreet == value) {
                isStreet(value);
            }
        }
        for (int aChance : chance) {
            if (aChance == value) {
                isChance(value);
            }
        }
    }



    public void isStreet(int value) {
        int[] one = Arrays.copyOfRange(street, 0, 5);
        int[] two = Arrays.copyOfRange(street, 5, 9);
        int[] three = Arrays.copyOfRange(street, 9, 13);
        int[] four = Arrays.copyOfRange(street, 13, 15);
        int[] five = Arrays.copyOfRange(street, 15, 17);
        if (value < 5) {
            player.money.addAmount(1);
        } else if (value < 9) {

        } else if (value < 13) {

        } else if (value < 15) {

        } else {

        }
    }

    private void isChance(int value) {

    }
}

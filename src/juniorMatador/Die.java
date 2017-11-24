package juniorMatador;

import java.util.Random;

public class Die {

    private int face;

    public int roll() {
        Random rand = new Random();
        face = rand.nextInt(6)+1;
        return face;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face1) {
        if (face1 > 0 && face1 <= 6) {
            this.face = face1;
        } else {
            System.out.println("Illegal value.");
        }
    }

    @Override
    public String toString() {
        return "["+face+"]";
    }
}

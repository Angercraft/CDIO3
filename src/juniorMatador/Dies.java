package juniorMatador;

import java.util.Random;

public class Dies {

    private int face1, face2;

    public void roll() {
        Random rand = new Random();
        face1 = rand.nextInt(6)+1;
        face2 = rand.nextInt(6)+1;
    }

    public int sum() {
        return face1 + face2;
    }

    public int getFace1() {
        return face1;
    }

    public void setFace1(int face1) {
        if (face1 > 0 && face1 <= 6) {
            this.face1 = face1;
        } else {
            System.out.println("Illegal value.");
        }
    }

    public int getFace2() {
        return face2;
    }

    public void setFace2(int face2) {
        if (face2 > 0 && face2 <= 6) {
            this.face2 = face2;
        } else {
            System.out.println("Illegal value.");
        }
    }

    @Override
    public String toString() {
        return "["+face1+", "+face2+"]";
    }
}

package test;

import juniorMatador.Die;
import static org.junit.Assert.*;

public class DieTest {

    Die testDie;

    @org.junit.Before
    public void setUp() throws Exception {
        testDie = new Die();
    }

    @org.junit.Test
    public void testRoll() throws Exception {
        int min = 1;
        int max = 6;
        for (int i = 0 ; i < 1000 ; i++) {
            testDie.roll();
            int testRoll = testDie.getFace();
            assertTrue("Face value was not within the required amount. Was instead: "+testRoll, min <= testRoll && testRoll <= max);
        }
    }

    @org.junit.Test
    public void testSetFace() throws Exception {
        int expected = 5;
        testDie.setFace(expected);
        int actual = testDie.getFace();
        assertEquals("Input was not equal to output.", expected, actual);
        expected = 15;
        testDie.setFace(expected);
        actual = testDie.getFace();
        assertNotEquals("Illegal input was accepted. Expected: "+expected, expected, actual);
    }
}
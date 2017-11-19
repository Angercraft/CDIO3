package test;

import juniorMatador.Dies;
import static org.junit.Assert.*;

public class DiesTest {

    Dies testDie;

    @org.junit.Before
    public void setUp() throws Exception {
        testDie = new Dies();
    }

    @org.junit.Test
    public void roll() throws Exception {
        int min = 1;
        int max = 6;
        for (int i = 0 ; i < 1000 ; i++) {
            testDie.roll();
            int testRoll = testDie.getFace1();
            assertTrue("Face value was not within the required amount. Was instead: "+testRoll, min <= testRoll && testRoll <= max);
        }
    }

    @org.junit.Test
    public void sum() throws Exception {
        testDie.roll();
        int expected = testDie.getFace1() + testDie.getFace2();
        int actual = testDie.sum();
        assertEquals("The sum was not equal to the face values added together. Face values was: "+testDie.getFace1()+", "+testDie.getFace2()+" and sum was: "+actual, expected, actual);
    }

    @org.junit.Test
    public void setFace1() throws Exception {
        int expected = 5;
        testDie.setFace1(expected);
        int actual = testDie.getFace1();
        assertEquals("Input was not equal to output.", expected, actual);
        expected = 15;
        testDie.setFace1(expected);
        actual = testDie.getFace1();
        assertNotEquals("Illegal input was accepted. Expected: "+expected, expected, actual);
    }

    @org.junit.Test
    public void setFace2() throws Exception {
        int expected = 5;
        testDie.setFace2(expected);
        int actual = testDie.getFace2();
        assertEquals("Input was not equal to output.", expected, actual);
        expected = 15;
        testDie.setFace2(expected);
        actual = testDie.getFace2();
        assertNotEquals("Illegal input was accepted. Expected: "+expected, expected, actual);
    }

}
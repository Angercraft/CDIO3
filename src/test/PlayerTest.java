package test;

import static org.junit.Assert.*;

import org.junit.Test;
import juniorMatador.Player;

public class PlayerTest {

	Player testPlayer;
	
    @org.junit.Before
    public void setUp() throws Exception {
        testPlayer = new Player("Ho Lee Fok", 1, 100);
    }
    
	@Test
	public void testGetName() {
		assertEquals("gotten name doesn't equal actual name", testPlayer.getName().equals("Ho Lee Fok"));
	}
	
	// Kan heller ikke gennemskue hvordan den skal løses. Nikolaj. 
	@Test
	public void testGetMoney() {
		
	}
	
	@Test
	public void testGetPlayerNumb() {
		assertEquals("gotten player number doesn't equal actual playernumber", testPlayer.getPlayerNumber());
	}
	
	@Test
	public void testGetPlayerPos() {
		assertEquals("gotten playerposition doesn't equal intial position", testPlayer.getPlayerPos());
	}
	
	@Test
	public void testGetPlayerTurn() {
		assertEquals("gotten player turn doesn't meet expectations", !testPlayer.getPlayerTurn());
	}
	
	@Test
	public void testSetPlayerturn() {
		testPlayer.setPlayerTurn(true);
		assertEquals("not possible to set player turn", testPlayer.getPlayerTurn());
	}
	
	//Test whether it's possible to set the player location.
	@Test
	public void testSetPlayerPos() {
		testPlayer.setPlayerPos(12);
		assertEquals("not possible to set the player to desired location on board", testPlayer.getPlayerPos()==12);
	}
	
	//Test if the player position can be set to outside the board.
	@Test
	public void testSetPlayerPos2() {
		testPlayer.setPlayerPos(300);
		assertEquals("possible to set player position to outside the array size", testPlayer.getPlayerPos()>23);
	}

}

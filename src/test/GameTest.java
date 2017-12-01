package test;

import static org.junit.Assert.*;

import juniorMatador.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartPassed() {
        boolean expected = true;
        boolean actual = game.startPassed(38, 3 );
        assertEquals("New position is larger than old position", expected, actual);
		
	}

}

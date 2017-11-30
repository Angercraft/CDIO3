import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	@Before
	public void setUp() throws Exception {
		firstGame = new Game(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartPassed() {
        Game game = new Game();
        int oldPos = 38;
        int newPos = 3; 
        boolean expected = true;
        boolean actual = game.startPassed(int oldPos, int newPos)
        assertEquals("New position is larger than old position", expected, actual);
		
	}

}

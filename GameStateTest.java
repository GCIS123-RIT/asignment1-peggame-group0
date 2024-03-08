package pegGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test that tests out the GameState enummeration
 */
public class GameStateTest {
    
    @Test
    public void testGameState() {
        assertEquals("Game has not started", GameState.NOT_STARTED.toString());
        assertEquals("Game in progress", GameState.IN_PROGRESS.toString());
        assertEquals("You have won the game! ", GameState.WON.toString());
        assertEquals("Game has reached a stalemate", GameState.STALEMATE.toString());
}
}

   
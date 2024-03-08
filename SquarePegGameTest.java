package pegGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test that tests out the getPossibleMoves method in the SquarePegGame class
 */
public class SquarePegGameTest {
    @Test
    public void testPossibleMoves() {
        SquarePegGame pegGame = new SquarePegGame(4, 4);
        assertEquals(3, pegGame.getPossibleMoves().size());
    }
}

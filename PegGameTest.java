package pegGame;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

/**
 * JUnit test tp test the collection of moves method in the PegGame class
 */
public class PegGameTest {

    @Test
    public void testGetPossibleMoves() {
        PegGame pegGame = new SquarePegGame(4, 4); 

        Collection<Move> moves = pegGame.getPossibleMoves();

        assertNotNull(moves);
        assertTrue(!moves.isEmpty());
    }

}


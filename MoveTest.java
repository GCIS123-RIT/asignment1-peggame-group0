package pegGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test that tests out the toString method in the Move class
 */
public class MoveTest {
    
    @Test
    public void testMoveToString() {
        Location from = new Location(2,1);
        Location to = new Location(5,3);
        Move moveTo = new Move(from, to);
        assertEquals("Move from (2, 1) to (5, 3)", moveTo.toString());

    }
}

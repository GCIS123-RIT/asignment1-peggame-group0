package pegGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit tests the toString method for the Location Class 
 */
public class LocationTest {
    
    @Test
    public void testLocationToString() {
        Location testLocation = new Location(2, 5);
        assertEquals("(2, 5)", testLocation.toString());
    }
}

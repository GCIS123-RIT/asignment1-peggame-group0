package pegGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test to test out the exception being thrown byt the class PegGameException
 */
public class pegGameExceptionTest {
    @Test
    public void testExceptions() {
        pegGameException testingException = new pegGameException("Exception");
        assertEquals("Exception", testingException.getMessage());
    }
}

package pegGame;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test that tests the board reader function to see if it can convert a text file into a board game
 */
public class BoardReaderTest {

    @Test
    public void testReadFromFile() throws IOException {
        String testFilename = "/Users/macbookpro/Downloads/sixBySix.txt" ;

        BoardReader boardReader = new BoardReader(testFilename);

        PegGame pegGame = boardReader.readFromFile();

        assertEquals(GameState.IN_PROGRESS, pegGame.getGameState());
    }
}

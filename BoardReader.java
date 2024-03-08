package pegGame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads a file and translates it into a peg game board.
 */


public class BoardReader {

    /**
     * Constructs a BoardReader with the given filename.
     * 
     * @param filename the name of the file to read from
     */


    private String filename;
    public BoardReader(String filename){
        this.filename= filename;
    }


/**
 * This method reads a file and translates it into a board
 * @return's a peg game
 * @throws IOException if the file cannot be read
 */

 
public PegGame readFromFile() throws IOException{
    FileReader fileReader = new FileReader(filename);
    BufferedReader bufferReader = new BufferedReader(fileReader); 
    int size = Integer.parseInt(bufferReader.readLine());
    SquarePegGame gameWithSquareBoard = new SquarePegGame(size, size);
    
    for (int row = 0; row < size; row++) {
        String line = bufferReader.readLine();
        for (int col = 0; col < size; col++) {
            char symbol = line.charAt(col);
            if (symbol == 'o') {
                gameWithSquareBoard.getBoard()[row][col] = 1;
            } else if (symbol == '.') {
                gameWithSquareBoard.getBoard()[row][col] = 0;
            } else {
                System.out.println("Invalid symbol in the input file.");
            }
        }
    }

    fileReader.close();
    return gameWithSquareBoard;
}

}


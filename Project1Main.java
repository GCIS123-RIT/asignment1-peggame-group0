package pegGame;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to run the Peg Game project.
 * project done by: Mohamed, Abdul Hamidi, Nadeen, Ali 
 */

public class Project1Main {
    /**
     * Main method to run the Peg Game project.
     * 
     * @param args command-line arguments
     * @throws pegGameException if an error occurs during the game
     */
    
    public class MainClass {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            // Prompt the user to enter a filename
            System.out.print("Enter the filename: ");
            String filename = scanner.nextLine().trim();
    
            // Create a new instance of the square Peg Game class from the file
            PegGame pegGame = null;
            try {
                BoardReader boardReader = new BoardReader(filename);
                pegGame = boardReader.readFromFile();
            } catch (IOException e) {
                System.out.println("Error: Failed to read from file.");
                scanner.close();
                return;
            }
            try {
                CLI.playPegGame(pegGame);
            } catch (pegGameException error) {
                System.out.println(error.getMessage());
                System.out.println("Please enter a valid move. Use suggested moves if needed.");
            }
            scanner.close();
        }
    }

}

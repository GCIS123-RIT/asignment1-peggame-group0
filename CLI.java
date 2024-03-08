package pegGame;

import java.util.Scanner;

/**
 * Command Line Interface (CLI) for playing the Peg Game.
 */

public class CLI {

    /**
     * Plays the Peg Game through the command line interface.
     * @param pegGame the Peg Game instance to play
     * @throws pegGameException if an error occurs during the game
     */


    public static void playPegGame(PegGame pegGame) throws pegGameException {
        System.out.println(pegGame.toString());
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Peg Game!");

            while (pegGame.getGameState() == GameState.IN_PROGRESS) {
                System.out.print("Enter your move (e.g., move r1 c1 r2 c2) or 'quit' to exit: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    return;
                }

                if (!input.startsWith("move ")) {
                    System.out.println("Invalid command. Please enter a valid move or 'quit'.");
                    continue;
                }

                // Extract integers from input
                String[] parts = input.substring(5).split("\\s+");
                if (parts.length != 4) {
                    System.out.println("Error: Invalid move format, use format (move r1 c1 r2 c2)");
                    continue;
                }

                try {
                    int fromRow = Integer.parseInt(parts[0].substring(1));
                    int fromCol = Integer.parseInt(parts[1].substring(1));
                    int toRow = Integer.parseInt(parts[2].substring(1));
                    int toCol = Integer.parseInt(parts[3].substring(1));

                    Location fromLocation = new Location(fromRow, fromCol);
                    Location toLocation = new Location(toRow, toCol);
                    Move move = new Move(fromLocation, toLocation);

                    pegGame.makeMove(move);
                    System.out.println(pegGame.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid move format.");
                }

            }
        }

        // Game is over
        System.out.println("Game is over over \n Thanks for playing");
        System.out.println(pegGame.toString());
        System.out.println("Game state: " + pegGame.getGameState());

    }
}


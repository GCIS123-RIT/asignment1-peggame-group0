package pegGame;

import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a square peg game.
 */

public class SquarePegGame implements PegGame {
    private int[][] board;
    private int rows;
    private int cols;


public int[][] getBoard()  {
        return board;
    }

    public int getRow(){
        return this.rows;
    }
    public int getcol(){
        return this.cols;
    }

    /**
 * Initiallizing the board that the peg game will be played on
 * The center hole is calculated by dividing the number of rows and coloumns 
 * The center hole is then made empty 
 * 1 represents a peg and 0 represents an empty hole
 */
    public SquarePegGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = 1; 
            }
        }
        board[rows / 2][cols / 2] = 0; 
    }
    
    /**
     * This method print the board through itirating over every row and coloumn and changes every 1 to "o" 
     * and every 0 to "-"
     * It also labels each row and coloumn to allow for an easier visual of rows and coloumns
     * @return's a string representing the board
     */
     @Override
     public String toString() {
         String result = "  "; 
         for (int c = 0; c < cols; c++) {
             result += c + " "; 
         }
         result += "\n";
     
         for (int r = 0; r < rows; r++) {
             result += r + " "; 
             for (int c = 0; c < cols; c++) {
                 if (board[r][c] == 1) {
                     result += "o "; 
                 } else {
                     result += "- "; 
                 }
             }
             result += "\n";
         }
         return result;
     }
     

/**
     * This method checks if a jump to the left is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidLeftHorizontalJump(int row, int col) {
        if (col - 2 >= 0 && board[row][col - 1] == 1 && board[row][col - 2] == 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * This method checks if a jump to the right is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidRightHorizontalJump(int row, int col){

        if (col + 2 < cols && board[row][col + 1] == 1 && board[row][col + 2] == 0) {
            return true;
        }
           else{
            return false;
           }
    }        
    
    /**
     * This method checks if the jump Downwards is valid or not
     * @param row is the row of the peg 
     * @param col is the coloumn of the peg
     * @return's a boolean expression depending on if the move is valid or not
     */

    private boolean isValidDownVerticalJump(int row, int col) {
        if (row - 2 >= 0 && board[row - 1][col] == 1 && board[row - 2][col] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

   /**
    * This method checks if a move upwards is valid or not 
    * @param row is the row of the peg
    * @param col is the coloumn of the peg
    * @return's boolean expression
    */
    private boolean isValidUpVerticalJump(int row, int col){
        if (row + 2 < rows && board[row + 1][col] == 1 && board[row + 2][col] == 0) {
            return true;
        }
        else {
            return false;
        }
       
    }

    /**
     * This method checks if a jump to the topleft diagonally is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidDiagonaTopLeftlJump(int row, int col) {
        if (row - 2 >= 0 && col - 2 >= 0 && board[row - 1][col - 1] == 1 && board[row - 2][col - 2] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method checks if a jump to the bottom right diagonally is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidDiagonaBottomRightlJump(int row, int col){
        if (row + 2 < rows && col + 2 < cols && board[row + 1][col + 1] == 1 && board[row + 2][col + 2] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method checks if a jump to the bottom left diagonally is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidDiagonaBottomLeftlJump(int row, int col){
        if (row + 2 < rows && col - 2  >=0 && board[row + 1][col - 1] == 1 && board[row + 2][col - 2] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method checks if a jump to the top right diagonally is valid or not
     * @param row is the row of the peg
     * @param col is the col of the peg
     * @return's boolean expression
     */
    private boolean isValidDiagonaTopRightlJump(int row, int col){
        if (row - 2 >= 0 && col + 2 < cols && board[row - 1][col + 1] == 1 && board[row - 2][col + 2] == 0) {
            return true;
        }
        else{
            return false;
        }
    }
  
    /**
     * Gets the possible moves that can be made on the current board
     * Calls on the different types of jump methods in order to determine if the move should be added or not
     * @return A collection of possible moves
     */
@Override
public Collection<Move> getPossibleMoves() {
    Collection<Move> possibleMoves = new ArrayList<>();
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            if (board[row][col] == 1) { // If there is a peg at this location
                // Check for possible horizontal jumps
                if(isValidLeftHorizontalJump(row, col)){
                    Location from = new Location(row,col);
                    Location to = new Location(row, col - 2);
                    Move move = new Move(from,to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }
                if (isValidRightHorizontalJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row, col + 2);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }
                // Check for possible vertical jumps
                if (isValidUpVerticalJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row + 2, col);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }

                if (isValidDownVerticalJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row - 2, col);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }
                // Check for possible diagonal jump
                if (isValidDiagonaTopLeftlJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row - 2, col - 2);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }

                if (isValidDiagonaTopRightlJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row - 2, col + 2);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }

                if (isValidDiagonaBottomLeftlJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row + 2, col - 2);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }

                if (isValidDiagonaBottomRightlJump(row, col)) {
                    Location from = new Location(row, col);
                    Location to = new Location(row + 2, col + 2);
                    Move move = new Move(from, to);
                    possibleMoves.add(move);
                    System.out.println("Possible move: " + move);
                }
            }
        }
    }
    return possibleMoves;
}

/**
 * This method gets the game state 
 * It returns the game state depending on how many moves are left on the board
 */
    @Override
    public GameState getGameState() {
        // If there are no possible moves, the game is either won or in stalemate
        if (getPossibleMoves().isEmpty()) {
            if (numPegsLeft() == 1) {
                System.out.println("The game has been won!");
                return GameState.WON;         
            } else 
            {
                System.out.println("The game has reached a stalemate");
                return GameState.STALEMATE;
            }
        }
        // If there are possible moves, the game is still in progress
        System.out.println("Game is still in Progress");
        return GameState.IN_PROGRESS;
    }
/**
 * This method calculates how many pegs are left on the board
 * @return's a count which represents how many pegs are left
 */
    private int numPegsLeft() {
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * This method makes the actual move on the board
     * Changes the peg that was jumped over into an empty hole
     * Changes the hole that was jumped over to into a peg
     * Changes the peg that was jumped from into an empty hole
     */
    @Override
public void makeMove(Move move) throws pegGameException {
    Location from = move.getFrom();
    Location to = move.getTo();

    // Check if the move is valid
    if (!isValidMove(from, to)) {
        throw new pegGameException("Invalid move.");
    }

    // Perform the move
    int jumpRow = (from.getRow() + to.getRow()) / 2;
    int jumpCol = (from.getColoumn() + to.getColoumn()) / 2;

    board[from.getRow()][from.getColoumn()] = 0;
    board[jumpRow][jumpCol] = 0;
    board[to.getRow()][to.getColoumn()] = 1;
}

/**
 * This method checks if the move being made is valid or not
 * @param from is the starting coordinate
 * @param to is the coordinate its jumping to 
 * @param jumpRow is the peg that will be jumped over
 * @param jumpCol is coloumn that will be jumped over
 * @return's boolean expression
 */
private boolean isValidMove(Location from, Location to) {
    int fromRow = from.getRow();
    int fromCol = from.getColoumn();
    int toRow = to.getRow();
    int toCol = to.getColoumn();

    // Check if 'from' location is within bounds and contains a peg
    if (!isValidLocation(from) || board[fromRow][fromCol] != 1) {
        return false;
    }

    // Check if 'to' location is within bounds and is an empty hole
    if (!isValidLocation(to) || board[toRow][toCol] != 0) {
        return false;
    }

    // Calculate the row and column of the peg that will be jumped over
    int jumpRow = (fromRow + toRow) / 2;
    int jumpCol = (fromCol + toCol) / 2;

    // Check if the jump location contains a peg
    if (board[jumpRow][jumpCol] != 1) {
        return false;
    }

    // Determine the type of move and check if it's valid
    if (fromRow == toRow) { // Horizontal move
        return Math.abs(fromCol - toCol) == 2; // Must move two columns horizontally
    } else if (fromCol == toCol) { // Vertical move
        return Math.abs(fromRow - toRow) == 2; // Must move two rows vertically
    } else { // Diagonal move
        return Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2; // Must move two rows and two columns diagonally
    }
}


/**
 * This method checks if the coordinates are within the board boundries
 * @param loc is the location of the peg
 * @return's boolean expression
 */
    private boolean isValidLocation(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < rows && loc.getColoumn() >= 0 && loc.getColoumn() < cols;
    }

    /**
     * Main method to play the square peg game 
     * This game utilises user input and asks for the row and coloumn for both moves from and to
     * @param args
     * @throws pegGameException throws an exception if there is an error 
     */
    public static void main(String[] args) {
        SquarePegGame pg = new SquarePegGame(4, 4);
        System.out.println(pg.toString());
        Scanner scanner1 = new Scanner(System.in); // Create a Scanner object
        
        while (pg.getGameState() == GameState.IN_PROGRESS) {
            try {
                System.out.print("Enter the row number of the peg you want to move: ");
                int fromRow = scanner1.nextInt();
                System.out.print("Enter the column number of the peg you want to move: ");
                int fromCol = scanner1.nextInt();
                System.out.print("Enter the row number of the destination: ");
                int toRow = scanner1.nextInt();
                System.out.print("Enter the column number of the destination: ");
                int toCol = scanner1.nextInt();
    
                Location fromLocation = new Location(fromRow, fromCol);
                Location toLocation = new Location(toRow, toCol);
                Move move = new Move(fromLocation, toLocation);
    
                pg.makeMove(move);
                System.out.println(pg.toString());
            } catch (pegGameException error) {
                System.out.println("Invalid move: " + error.getMessage());
                System.out.println("Please enter a valid move.");
            }
        }
        
        scanner1.close();
    }
    
}

    

   



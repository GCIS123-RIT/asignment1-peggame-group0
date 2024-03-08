package pegGame;

/**
 * Represents a location on the peg game board.
 */

public class Location {
    private int row;
    private int col;  

    /**
     * Constructs a Location with the given row and column coordinates.
     * @param row the row coordinate
     * @param col the column coordinate
     */
    
    public Location(int row, int col){
        this.row = row;
        this. col = col;

    }

/**
     * Gets the row coordinate of the location.
     * @return the row coordinate
     */
    public int getRow(){
        return row;
    }

    /**
     * Gets the column coordinate of the location.
     * @return the column coordinate
     */

    public int getColoumn(){
        return col;
    }


    /**
     * Returns a string representation of the location.
     * @return a string representation of the location
     */
    
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}





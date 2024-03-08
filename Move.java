package pegGame;

/**
 * Represents a move in the peg game, consisting of a starting location and an ending location.
 */

public class Move  {
    private Location from;
    private Location to;

    /**
     * Constructs a Move object with the specified starting and ending locations.
     * @param from the starting location of the move
     * @param to the ending location of the move
     */
    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the starting location of the move.
     * @return the starting location
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Gets the ending location of the move.
     * @return the ending location
     */
    public Location getTo() {
        return to;
    }

    /**
     * Returns a string representation of the move.
     * @return a string representation of the move
     */

    @Override
    public String toString() {
        return "Move from " + from + " to " + to;
    }
}
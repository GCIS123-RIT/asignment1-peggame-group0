package pegGame;

/**
 * Custom exception class for the peg game.
 */
public class pegGameException extends Exception {
    /**
     * Constructs a new pegGameException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public pegGameException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this pegGameException.
     *
     * @return a string representation of this pegGameException
     */
    @Override
    public String toString() {
        return "pegGameException: " + getMessage();
    }
}


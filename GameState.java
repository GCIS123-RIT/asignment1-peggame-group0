package pegGame;

/**
 * Enum representing the possible states of a peg game.
 */

public enum GameState {
    NOT_STARTED("Game has not started"),
    IN_PROGRESS("Game in progress"),
    STALEMATE("Game has reached a stalemate"),
    WON("You have won the game! ");

    private String description;

    /**
     * Constructs a GameState enum value with the specified description.
     * @param description the description of the game state
     */

    private GameState(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the game state.
     * @return the description of the game state
     */
    @Override   
    public String toString() {
        return description;
    }
}

    
    
    

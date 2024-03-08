package pegGame;

import java.util.Collection;

/**
 * Interface for a Peg Game.
 */

public interface PegGame{

    /**
     * Gets a collection of possible moves in the current game state.
     * @return a collection of possible moves
     */

    public Collection<Move> getPossibleMoves();

    /**
     * Gets the current game state.
     * @return the current game state
     */
       
    public GameState getGameState();

    /**
     * Makes a move in the game.
     * 
     * @param move the move to make
     * @throws pegGameException if an error occurs while making the move
     */

    public void makeMove(Move move) throws pegGameException;
}

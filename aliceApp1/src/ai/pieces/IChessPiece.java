package ai.pieces;

import java.util.List;

/**
 * Interface for chess piece
 * 
 * @author Ajay
 *
 */
public interface IChessPiece {
	
	/**
	 * Get the piece name
	 * 
	 * @return - the piece name
	 */
	char getPieceName();

	/**
	 * Move the given piece on the board
	 * 
	 * @param boardTag - the board tag (1 or 2)
	 * @param pos      - position to be moved
	 * @param moves    - list of moves
	 */
	void movePiece(int boardTag, int pos, List<String> moves);
	
	/**
	 * Check if king is safe
	 * 
	 * @param player - the player (white or black)
	 * @return       - true if king is safe
	 */
	boolean isKingSafe(String player);
}

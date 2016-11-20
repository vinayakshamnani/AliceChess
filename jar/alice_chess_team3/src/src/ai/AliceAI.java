package ai;

import java.util.List;

/**
 * The AI interface that dictates the api for the AI that other modules will incorporate.
 * Other modules will only be exposed to this api. An implementation of this would
 * be handled through the factory class AliceAIFactory.
 * 
 * @author krish
 *
 */
public interface AliceAI {
	/**
	 * Returns the List of valid and smart moves that the AI thinks the White player should/can
	 * make given the current board state.
	 * 
	 * @return List - List of valid and smart moves made by the white player.
	 */
	List<String> nextWhiteMoves();
	
	/**
	 * Returns the List of valid and smart moves that the AI thinks the Black player should/can
	 * make given the current board state.
	 * 
	 * @return - List - The List of valid and smart moves made by the black player.
	 */
	List<String> nextBlackMoves();
	
	/**
	 * From the List of moves made, either by the program or the other player(program), the state of the 
	 * board needs to be updated. This function should be used passing it the exact input line given in order
	 * to update the current board state.
	 * 
	 * @param s - The input given by the other player (program)
	 */
	void update(String s);
	
	/**
	 * From the List of moves that are valid, filter out the List of moves that are 'smart'
	 * The implementing class can decide which of the List of moves are better/smarter.
	 * 
	 * @param validMoves - List of valid moves that can be made.
	 * @return List - List of filtered moves that are 'smarter' than the input List.
	 */
	List<String> filterMoves(List<String> validMoves);

	/**
	 * pick the best next move for the current player using miniMax algorithm
	 *
	 * @param isMaxPlayer - true for white and false for black.
	 * @return String - a string representing the best next move for the current player.
	 */
	String pickBestMove(boolean isMaxPlayer);
}

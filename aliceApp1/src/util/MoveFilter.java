package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for filtering the low value moves
 * 
 * @author Ajay
 *
 */
public class MoveFilter {

	/**
	 * check if the list contains given move
	 * 
	 * @param moves - list of moves
	 * @param move  - the move
	 * @return true if list contains given move
	 */
	public static boolean containsInListIgnoringCase(List<String> moves, String move) {
		for(int i = 0; i < moves.size(); ++i) {
			if(move.toLowerCase().equals(moves.get(i).toLowerCase()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Sort the moves by filtering low value moves
	 * 
	 * @param moves  - list of unsorted moves
	 * @param player - the player
	 * @return       - the sorted moves by removing low value moves
	 */
	public static List<String> sortMovesByPiece(List<String> moves, String player)
	{
		List<String> ret = new ArrayList<String>();
		
		for(String s : moves) {
			if(s.startsWith(player + " moves P"))
				ret.add(s);
			else
				ret.add(0, s);
		}
		
		return ret;
	}
}

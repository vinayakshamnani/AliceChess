package util;

import java.util.ArrayList;
import java.util.List;

public class MoveFilter {

	public static boolean ContainsInListIgnoringCase(List<String> list, String move) {
		for(int i = 0; i < list.size(); ++i) {
			if(move.toLowerCase().equals(list.get(i).toLowerCase()))
				return true;
		}
		
		return false;
	}
	
	public static List<String> SortMovesByPiece(List<String> moves, String player)
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

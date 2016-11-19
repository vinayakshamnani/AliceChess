package util;

import java.util.List;

public class MoveFilter {

	public static boolean ContainsInListIgnoringCase(List<String> list, String move) {
		for(int i = 0; i < list.size(); ++i) {
			if(move.toLowerCase().equals(list.get(i).toLowerCase()))
				return true;
		}
		
		return false;
	}
}

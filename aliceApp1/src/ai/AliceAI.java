package ai;

import java.util.List;

public interface AliceAI {
	List<String> nextWhiteMoves();
	List<String> nextBlackMoves();
	void update(String s);
}

package ai;

import java.util.List;

import model.Board;

public interface IChessPiece {
	
	char getPieceName();

	void movePiece(int boardTag, int pos, List<String> moves);
}

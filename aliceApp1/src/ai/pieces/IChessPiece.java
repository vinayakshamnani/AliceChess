package ai.pieces;

import java.util.List;


public interface IChessPiece {
	
	char getPieceName();

	void movePiece(int boardTag, int pos, List<String> moves);
	
	boolean isKingSafe(String player);
}

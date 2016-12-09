package ai.pieces;

import java.util.List;
import util.Constants;

import model.Board;

/**
 * Class for representing king
 * 
 * @author Ajay
 *
 */
public class KingChessPiece extends BaseChessPiece {

	/**
	 * Parameterized constructor for setting king on board
	 * 
	 * @param board - chess board model
	 * @param name  - name of the piece
	 */
	public KingChessPiece(Board board, char name) {
		super(board, name);
	}

	/**
	 * move the king on the board
	 */
	@Override
	public void movePiece(int boardTag, int pos, List<String> moves) {
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;

        // move around by 1 step
        for(int i = 0; i < 9; i++){
            if(i != 4){
                int r1 = r - 1 + i / 3;
                int c1 = c - 1 + i % 3;
                // check if the move is within the board
                if(r1 >= 0 && r1 < 8 && c1 >= 0 && c1 < 8){
                    // check if the target position is empty or occupied by an enemy piece
                    if(board.getFromBoard(boardTag, r1, c1) == ' ' ||
                            player.equals(Constants.PLAYER_WHITE) != Character.isUpperCase(board.getFromBoard(boardTag, r1, c1))){
                        // check if the corresponding position on the other board is empty
                        if(board.getFromBoard((boardTag * 2) % 3, r1, c1) == ' '){
                            char ch = player.equals(Constants.PLAYER_WHITE) ? Constants.WHITE_KING : Constants.BLACK_KING;
                            char enemy = board.getFromBoard(boardTag, r1, c1);
                            // Here we need two rounds of checks, first we need to check if the move is legal on the
                            // original board. Then we need to check if the move is also legal after the king was
                            // transferred to the other board

                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard(boardTag, r1, c1, ch);
                            if(isKingSafe(player)) {
                                // transfer the king to the other board
                                board.setBoard(boardTag, r1, c1, ' ');
                                board.setBoard((boardTag * 2) % 3, r1, c1, ch);
                                if(isKingSafe(player)){
                                    moves.add(player + " moves K from " + boardTag + " " +
                                            board.getCol()[c] + board.getRow()[r] + " to " +
                                            board.getCol()[c1] + board.getRow()[r1]);
                                }
                            }
                            // undo the update
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard(boardTag, r1, c1, enemy);
                            board.setBoard((boardTag * 2) % 3, r1, c1, ' ');
                        }
                    }
                }
            }
        }
		
	}

}

package ai.pieces;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import util.Constants;

import model.Board;

/**
 * Class for representing knight
 * 
 * @author Ajay
 *
 */
public class KnightChessPiece extends BaseChessPiece {

	/**
	 * Parameterized constructor for setting knight on board
	 * 
	 * @param board - chess board model
	 * @param name  - name of the piece
	 */
	public KnightChessPiece(Board board, char name) {
		super(board, name);
	}

	/**
	 * move the knight on the board
	 */
	@Override
	public void movePiece(int boardTag, int pos, List<String> moves) {
        int r = pos / 8;
        int c = pos % 8;

        List<List<Integer>> candidates = new ArrayList<List<Integer>>();
        candidates.add(Arrays.asList(r + 1, c + 2));
        candidates.add(Arrays.asList(r + 2, c + 1));
        candidates.add(Arrays.asList(r + 2, c - 1));
        candidates.add(Arrays.asList(r + 1, c - 2));
        candidates.add(Arrays.asList(r - 1, c - 2));
        candidates.add(Arrays.asList(r - 2, c - 1));
        candidates.add(Arrays.asList(r - 2, c + 1));
        candidates.add(Arrays.asList(r - 1, c + 2));

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;

        for(List<Integer> list : candidates){
            // check if the target place is within the board
            if(list.get(0) >= 0 && list.get(0) < 8 && list.get(1) >= 0 && list.get(1) < 8){
                // check if the target place is empty or occupied by enemy piece
                if(board.getFromBoard(boardTag, list.get(0), list.get(1)) == ' ' ||
                        Character.isUpperCase(board.getFromBoard(boardTag, list.get(0), list.get(1))) !=
                                Character.isUpperCase(board.getFromBoard(boardTag, r, c))){
                    // check if the corresponding place on the other board is empty
                    if(board.getFromBoard((boardTag * 2) % 3, list.get(0), list.get(1)) == ' '){
                        char ch = player.equals(Constants.PLAYER_WHITE) ? Constants.WHITE_KNIGHT : Constants.BLACK_KNIGHT;
                        char enemy = board.getFromBoard(boardTag, list.get(0), list.get(1));
                        // temporarily update the board to check if the king is still safe
                        board.setBoard(boardTag, r, c, ' ');
                        board.setBoard(boardTag, list.get(0), list.get(1), ' ');
                        board.setBoard((boardTag * 2) % 3, list.get(0), list.get(1), ch);
                        if(isKingSafe(player)) {
                            moves.add(player + " moves N from " + boardTag + " " +
                                    board.getCol()[c] + board.getRow()[r] + " to " +
                                    board.getCol()[list.get(1)] + board.getRow()[list.get(0)]);
                        }
                        // undo the update
                        board.setBoard(boardTag, r, c, ch);
                        board.setBoard(boardTag, list.get(0), list.get(1), enemy);
                        board.setBoard((boardTag * 2) % 3, list.get(0), list.get(1), ' ');
                    }
                }
            }
        }
		
	}

}

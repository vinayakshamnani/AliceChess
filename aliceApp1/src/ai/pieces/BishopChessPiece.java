package ai.pieces;

import java.util.List;

import model.Board;
import util.Constants;

/**
 * Class for representing bishop
 * 
 * @author Ajay
 *
 */
public class BishopChessPiece extends BaseChessPiece {

	/**
	 * Parameterized constructor for setting bishop on board
	 * 
	 * @param board - chess board model
	 * @param name  - name of the piece
	 */
	public BishopChessPiece(Board board, char name) {
		super(board, name);
	}

	/**
	 * move the bishop on the board
	 */
	@Override
	public void movePiece(int boardTag, int pos, List<String> moves) {
        int r = pos / 8;
        int c = pos % 8;

        String player = Character.isUpperCase(board.getFromBoard(boardTag, r, c)) ? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;

        // use i and j to indicate the direction of the move, for example (1, 1) refers to moving southeast
        for(int i = -1; i <= 1; i+=2){
            for(int j = -1; j <= 1; j+=2){
                // steps indicates how far the move is from the original position
                int steps = 1;
                try{
                    // keep trying while the path is empty, else check if the piece on the path is an enemy piece
                    while(board.getFromBoard(boardTag, r + i * steps, c + j * steps) == ' '){
                        // if the target position on the opposite board is empty and king is safe after the move
                        // then we can make the move
                        if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                            char ch = player.equals(Constants.PLAYER_WHITE) ? Constants.WHITE_BISHOP : Constants.BLACK_BISHOP;
                            // update the board to check if king is still safe after the rook move
                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                            if(isKingSafe(player)) {
                                moves.add(player + " moves B from " + boardTag + " " +
                                        board.getCol()[c] + board.getRow()[r] + " to " +
                                        board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                            }
                            // undo the move and restore the board
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                        }
                        steps++;
                    }
                    // can catch enemy piece
                    if(Character.isUpperCase(board.getFromBoard(boardTag, r + i * steps, c + j * steps)) !=
                            player.equals(Constants.PLAYER_WHITE)){
                        // check if the corresponding position on the other board is empty
                        if(board.getFromBoard((boardTag * 2) % 3, r + i * steps, c + j * steps) == ' '){
                            char ch = player.equals(Constants.PLAYER_WHITE) ? 'B' : 'b';
                            char enemy = board.getFromBoard(boardTag, r + i * steps, c + j * steps);
                            // temporarily update the board to check if king is still safe after the rook move
                            board.setBoard(boardTag, r, c, ' ');
                            board.setBoard(boardTag, r + i * steps, c + j * steps, ' ');
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ch);
                            if(isKingSafe(player)) {
                                moves.add(player + " moves B from " + boardTag + " " +
                                        board.getCol()[c] + board.getRow()[r] + " to " +
                                        board.getCol()[c + j * steps] + board.getRow()[r + i * steps]);
                            }
                            // undo the update
                            board.setBoard(boardTag, r, c, ch);
                            board.setBoard(boardTag, r + i * steps, c + j * steps, enemy);
                            board.setBoard((boardTag * 2) % 3, r + i * steps, c + j * steps, ' ');
                        }
                    }
                }
                catch (Exception e){}
            }
        }
	}
}

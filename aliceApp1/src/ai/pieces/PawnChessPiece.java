package ai.pieces;

import java.util.List;
import util.Constants;

import model.Board;

/**
 * Class for representing pawn
 * 
 * @author Ajay
 */
public class PawnChessPiece extends BaseChessPiece {

	/**
	 * Parameterized constructor for setting pawn on board
	 * 
	 * @param board - chess board model
	 * @param name  - name of the piece
	 */
	public PawnChessPiece(Board board, char pieceName) {
		 super(board, pieceName);
	}
	
	/**
	 * move the pawn on the board
	 */
	@Override
	public void movePiece(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;
        int dir = name == Constants.WHITE_PAWN ? -1 : 1;
        String player = (dir == -1) ? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;
        char ini_pos = (dir == -1) ? '2' : '7';
        char ch = name;

        //pawn can move 2 steps if it is at the initial position
        if(boardTag == 1 && board.getRow()[r] == ini_pos){
            //move by 2 steps
            if(board.getFromBoard(boardTag, r + dir, c) == ' '
                    && board.getFromBoard(boardTag, r + 2 * dir, c) == ' '
                    && board.getBoardB()[r + 2 * dir][c] == ' '){
                // temporarily update the board to check if the king is still safe after the move
                board.setBoard(boardTag, r, c, ' ');
                board.setBoard((boardTag * 2) % 3, r + 2 * dir, c, ch);
                if(isKingSafe(player)) {
                    moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                            board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + 2 * dir]);
                }
                //undo update
                board.setBoard(boardTag, r, c, ch);
                board.setBoard((boardTag * 2) % 3, r + 2 * dir, c, ' ');
            }
        }

        //move forward by 1 step
        try{
        if(board.getFromBoard(boardTag, r + dir, c) == ' ' &&
                board.getFromBoard((boardTag * 2) % 3, r + dir, c) == ' '){
            // temporarily update the board to check if the king is still safe after the move
            board.setBoard(boardTag, r, c, ' ');
            board.setBoard((boardTag * 2) % 3, r + dir, c, ch);
            if(isKingSafe(player)) {
                moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                        board.getRow()[r] + " to " + board.getCol()[c] + board.getRow()[r + dir]);
            }
            //undo update
            board.setBoard(boardTag, r, c, ch);
            board.setBoard((boardTag * 2) % 3, r + dir, c, ' ');
        }
        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("row:"+ r+dir + " col:"+ c);
        } 
        //catch enemy piece
        try{
            for(int i = -1; i <= 1; i+=2) {
                if (board.getFromBoard(boardTag, r + dir, c + i) != ' ' &&
                        Character.isUpperCase(board.getFromBoard(boardTag, r + dir, c + i)) != player.equals(Constants.PLAYER_WHITE)
                        && board.getFromBoard((boardTag * 2) % 3, r + dir, c + i) == ' '){
                    char enemy = board.getFromBoard(boardTag, r + dir, c + i);
                    // temporarily update the board to check if the king is still safe after the move
                    board.setBoard(boardTag, r, c, ' ');
                    board.setBoard(boardTag, r + dir, c + i, ' ');
                    board.setBoard((boardTag * 2) % 3, r + dir, c + i, ch);
                    if (isKingSafe(player)) {
                        moves.add(player + " moves P from " + boardTag + " " + board.getCol()[c] +
                                board.getRow()[r] + " to " + board.getCol()[c + i] + board.getRow()[r + dir]);
                    }
                    //undo update
                    board.setBoard(boardTag, r, c, ch);
                    board.setBoard(boardTag, r + dir, c + i, enemy);
                    board.setBoard((boardTag * 2) % 3, r + dir, c + i, ' ');
                }
            }
        }catch (Exception e){
        	e.printStackTrace();
        }

    }
}

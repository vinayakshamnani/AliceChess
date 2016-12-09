package ai;

import java.util.List;

import model.Board;

public class PawnChessPiece extends BaseChessPiece {

	public PawnChessPiece(Board board, char pieceName) {
		 super(board, pieceName);
	}
	
	@Override
	public void movePiece(int boardTag, int pos, List<String> moves){
        int r = pos / 8;
        int c = pos % 8;
        int dir = name == 'P' ? -1 : 1;
        String player = (dir == -1) ? "white" : "black";
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
                        Character.isUpperCase(board.getFromBoard(boardTag, r + dir, c + i)) != player.equals("white")
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
        }catch (Exception e){}

    }
}

package ai;

import model.Board;

import java.util.ArrayList;
import java.util.List;

import ai.pieces.IChessPiece;
import util.Constants;
import util.MoveFilter;

/**
 * Our first iteration of the AliceAI api.
 * It's better than just dumbly giving up or picking a random move that can be invalid.
 * It actually calculates all the legal moves that can be made for the current game board.
 * From this List of valid/legal moves, this implementation only returns the 1st
 * move in the list.
 * It does NOT select the smarter option of the bunch. There is no formal intelligence associated to it.
 * 
 * @author krish
 *
 */
public class BaseAliceAIImpl implements AliceAI {
	
	final static int DEPTH = 3;
	final static int filterThreshold = 39;
	
	private List<IChessPiece> chessPieces;
	
	public BaseAliceAIImpl()
	{
		chessPieces = new ArrayList<IChessPiece>();
	}
	
	public void addChessPieceToAI(IChessPiece chessPiece)
	{
		chessPieces.add(chessPiece);
	}
	
	/**
	 * The actual Board to hold the current state of the game.
	 */
	private Board board = new Board();
	
	public Board getAIBoard() {
		return board;
	}

	/**
	 * This is not doing any thing as of now. Just returns the same input List of moves. 
	 */
	public List<String> filterMoves(List<String> validMoves) {
		return validMoves;
	}
	
	public void nextWhiteMoves(char[][] board, int boardTag, List<String> moves) {
        for(int i = 0; i < 64; i++)
        	for(IChessPiece p : chessPieces) 
        		if(p.getPieceName() > Constants.CAP_A && p.getPieceName() < Constants.CAP_Z && p.getPieceName() == board[i / 8][i % 8]) 
        			p.movePiece(boardTag, i, moves);
	}
	
	
	/**
	 * Returns the next set of white moves that are legal to make but not smarter.
	 */
    public List<String> nextWhiteMoves(){
        List<String> moves = new ArrayList<String>();
        
        /* Loop through all positions on the first board and find out if a move can be made by the piece */
        nextWhiteMoves(board.getBoardA(), Board.BOARD_A, moves);
        
        /* Loop through all positions on the second board and find out if a move can be made by the piece */
        nextWhiteMoves(board.getBoardB(), Board.BOARD_B, moves);
        
        /* Finally return the List of moves. If this is empty, the calling program should give up. */
        return moves;
    }

    
    public void nextBlackMoves(char[][]board, int boardTag, List<String> moves){
        for(int i = 0; i < 64; i++) 
        	for(IChessPiece p : chessPieces) 
        		if(p.getPieceName() > Constants.SMALL_A && p.getPieceName() < Constants.SMALL_Z && p.getPieceName() == board[i / 8][i % 8]) 
        			p.movePiece(boardTag, i, moves);      
    }

    /**
     * Returns the next set of black moves that are legal to make but not smarter.
     */
    public List<String> nextBlackMoves(){
    	List<String> moves = new ArrayList<String>();
    	
    	/* Loop through all positions on the first board and find out if a move can be made by the piece */
    	nextBlackMoves(board.getBoardA(), Board.BOARD_A, moves);
    	
    	/* Loop through all positions on the second board and find out if a move can be made by the piece */
    	nextBlackMoves(board.getBoardB(), Board.BOARD_B, moves);
    	
    	/* Finally return the List of moves. If this is empty, the calling program should give up. */
    	return moves;
    }
    
    
    public void update(String s) {
    	// From the input message, find out which board it is, the piece, the row and column
    	// it has moved from and to.
        int boardTag = s.charAt(19) - '0';
        char ch = s.charAt(12);
        String color = s.substring(0, 5);
        if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
        int preCol = s.charAt(21) - 'a';
        int preRow = 8 - (s.charAt(22) - '0');
        int col = s.charAt(27) - 'a';
        int row = 8 - (s.charAt(28) - '0');

        // when a pawn reach the last row, it will be automatically promoted to a queen.
        if(ch == 'P' && row == 0) ch = 'Q';
        if(ch == 'p' && row == 7) ch = 'q';

        // Just update the board.
        board.setBoard(boardTag, preRow, preCol, ' ');
        board.setBoard(boardTag, row, col, ' ');
        board.setBoard((boardTag * 2) % 3, row, col, ch);

        //printBoard();
    }

    private void printBoard() {
        for(int i = 0; i < 8; i++){
            for(char c : board.getBoardA()[i]){
                if(c == ' ') {
                    c = '-';
                }
                System.out.print(c + " ");
            }
            System.out.print("  ");
            for(char c : board.getBoardB()[i]){
                if(c == ' ') {
                    c = '-';
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }


    /**
     * This function picks the best move for the current player,
     * where white is the maxPlayer and black the minPlayer
     *
     */
    public String pickBestMove(boolean isMaxPlayer) {
        
        return miniMax(DEPTH, isMaxPlayer);
    }

    private String miniMax(int depth, boolean isMaxPlayer){
        String player = isMaxPlayer? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;
        String bestMove = player + " surrenders";
        int highValue = Integer.MIN_VALUE;
        int lowValue = Integer.MAX_VALUE;
        int currentValue;

        List<String> nonSortedMoves = isMaxPlayer ? nextWhiteMoves() : nextBlackMoves();
        List<String> nextMoves = MoveFilter.SortMovesByPiece(nonSortedMoves, player);
        int counter = 0;

        for(String s : nextMoves){
        	
        	if(counter == filterThreshold)
        		break;
        	
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            currentValue = isMaxPlayer ? max(depth - 1) : min(depth - 1);
            if(isMaxPlayer && currentValue > highValue){
                highValue = currentValue;
                bestMove = s;
            }
            else if(!isMaxPlayer && currentValue < lowValue){
                lowValue = currentValue;
                bestMove = s;
            }

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');
            
            counter++;

        }
        return bestMove;
    }

    private int max(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        int bestValue = Integer.MIN_VALUE;
        List<String> nextWhiteMoves = nextWhiteMoves();
        if (nextWhiteMoves.size() == 0) return bestValue;
        for (String s : nextWhiteMoves) {
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            int value = min(depth - 1);
            bestValue = Math.max(bestValue, value);

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');
        }
        return bestValue;
    }

    private int min(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        int bestValue = Integer.MAX_VALUE;
        List<String> nextBlackMoves = nextBlackMoves();
        if(nextBlackMoves.size() == 0) return bestValue;
        for(String s : nextBlackMoves){
            int boardTag = s.charAt(19) - '0';
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - 'a';
            int preRow = 8 - (s.charAt(22) - '0');
            int col = s.charAt(27) - 'a';
            int row = 8 - (s.charAt(28) - '0');
            char preChar = board.getFromBoard(boardTag, row, col);
            //update board
            board.setBoard(boardTag, preRow, preCol, ' ');
            board.setBoard(boardTag, row, col, ' ');
            board.setBoard((boardTag * 2) % 3, row, col, ch);

            int value = max(depth - 1);
            bestValue = Math.min(bestValue, value);

            //undoUpdate
            board.setBoard(boardTag, preRow, preCol, ch);
            board.setBoard(boardTag, row, col, preChar);
            board.setBoard((boardTag * 2) % 3, row, col, ' ');
        }
        return bestValue;
    }

    private int[] evaluate(char[][] board) {
    	int sumWhite = 0;
    	int sumBlack = 0;
    	
    	for(int i = 0; i < 64; i++){
            switch (board[i / 8][i % 8]){
                case 'P' : sumWhite += Constants.STRENGTH_PAWN;
                    break;
                case 'R' : sumWhite += 82;
                    break;
                case 'N' : sumWhite += 32;
                    break;
                case 'B' : sumWhite += 54;
                    break;
                case 'Q' : sumWhite += 132;
                    break;
                case 'K' : sumWhite += 10000;
                    break;
                case 'p' : sumBlack += Constants.STRENGTH_PAWN;
                    break;
                case 'r' : sumBlack += 82;
                    break;
                case 'n' : sumBlack += 32;
                    break;
                case 'b' : sumBlack += 54;
                    break;
                case 'q' : sumBlack += 132;
                    break;
                case 'k' : sumBlack += 10000;
                    break;
            }
        }
    	
    	return new int[] {sumWhite, sumBlack};
    }
    
    /**
     * This function evaluate the current board and return a score which is calculated by considering
     * the pieces, check, move ability, and ...
     *
     */
    private int evaluate(){
        // scores of white and black
        int sumWhite = 0;
        int sumBlack = 0;

        // add the weight of active pieces to score
        int[] sums = evaluate(board.getBoardA());
        sumWhite += sums[0];
        sumBlack += sums[1];
        
        sums = evaluate(board.getBoardB());
        sumWhite += sums[0];
        sumBlack += sums[1];

        // add points for check
        if(!chessPieces.get(0).isKingSafe(Constants.PLAYER_WHITE)) sumBlack += 1;
        if(!chessPieces.get(0).isKingSafe(Constants.PLAYER_BLACK)) sumWhite += 1;

        return sumWhite - sumBlack;
    }
}

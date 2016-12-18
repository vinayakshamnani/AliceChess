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
	
	// List of all chess pieces
	private List<IChessPiece> chessPieces;
	
	/**
	 * Default constructor
	 */
	public BaseAliceAIImpl()
	{
		chessPieces = new ArrayList<>();
	}
	
	/**
	 * Parameterized constructor to add new chess piece in the chessPieces
	 */
	public void addChessPieceToAI(IChessPiece chessPiece)
	{
		chessPieces.add(chessPiece);
	}
	
	/**
	 * The actual Board to hold the current state of the game.
	 */
	private Board board = new Board();
	
	/**
	 * Get the AI board
	 */
	public Board getAIBoard() {
		return board;
	}

	/**
	 * Returns all the possible white moves that are legal to make.
	 */
    public List<String> nextWhiteMoves(){
        List<String> moves = new ArrayList<String>();
        
        /* Loop through all positions on the first board and find out if a move can be made by the piece */
        nextWhiteMoves(board.getBoardA(), Constants.BOARD_A, moves);
        
        /* Loop through all positions on the second board and find out if a move can be made by the piece */
        nextWhiteMoves(board.getBoardB(), Constants.BOARD_B, moves);
        
        /* Finally return the List of moves. If this is empty, the calling program should give up. */
        return moves;
    }

    @Override
    public List<String> filterMoves(List<String> validMoves) {
        return validMoves;
    }

    /**
     * Get the list of white player moves
     *
     * @param board    - the board
     * @param boardTag - the board tag (1 or 2)
     * @param moves    - the list of moves
     */
    public void nextWhiteMoves(char[][] board, int boardTag, List<String> moves) {
        for(int i = 0; i < 64; i++)
            if(board[i / 8][i % 8] == ' ') continue;
            else{
                for(IChessPiece p : chessPieces)
                    if(p.getPieceName() > Constants.CAP_A && p.getPieceName() < Constants.CAP_Z
                            && p.getPieceName() == board[i / 8][i % 8])
                        p.movePiece(boardTag, i, moves);
            }
    }

    /**
     * Returns all the possible next black moves that are legal to make.
     */
    public List<String> nextBlackMoves(){
        List<String> moves = new ArrayList<String>();

    	/* Loop through all positions on the first board and find out if a move can be made by the piece */
        nextBlackMoves(board.getBoardA(), Constants.BOARD_A, moves);

    	/* Loop through all positions on the second board and find out if a move can be made by the piece */
        nextBlackMoves(board.getBoardB(),Constants.BOARD_B, moves);

    	/* Finally return the List of moves. If this is empty, the calling program should give up. */
        return moves;
    }

    /**
	 * Get the list of black player moves
	 * 
	 * @param board    - the board
	 * @param boardTag - the board tag (1 or 2)
	 * @param moves    - the list of moves
	 */
    public void nextBlackMoves(char[][]board, int boardTag, List<String> moves){
        for(int i = 0; i < 64; i++)
            if(board[i / 8][i % 8] == ' ') continue;
            else {
                for (IChessPiece p : chessPieces)
                    if (p.getPieceName() > Constants.SMALL_A && p.getPieceName() < Constants.SMALL_Z
                            && p.getPieceName() == board[i / 8][i % 8])
                        p.movePiece(boardTag, i, moves);
            }
    }

    /**
     * Update the board model for the move (move is represented by string)
     */
    public void update(String s) {
    	// From the input message, find out which board it is, the piece, the row and column
    	// it has moved from and to.
        int boardTag = s.charAt(19) - Constants.ZERO;
        char ch = s.charAt(12);
        String color = s.substring(0, 5);
        if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
        int preCol = s.charAt(21) - Constants.SMALL_A;
        int preRow = 8 - (s.charAt(22) - Constants.ZERO);
        int col = s.charAt(27) - Constants.SMALL_A;
        int row = 8 - (s.charAt(28) - Constants.ZERO);

        // when a pawn reaches the last row, it will be automatically promoted to a queen.
        if(ch == Constants.BLACK_PAWN && row == 7) ch = Constants.BLACK_QUEEN;
        if(ch == Constants.WHITE_PAWN && row == 0) ch = Constants.WHITE_QUEEN;

        // Just update the board.
        board.setBoard(boardTag, preRow, preCol, ' ');
        board.setBoard(boardTag, row, col, ' ');
        board.setBoard((boardTag * 2) % 3, row, col, ch);

        printBoard();
    }

    @SuppressWarnings("unused")
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
        
        return miniMax(Constants.DEPTH, isMaxPlayer);
    }

    /**
     * Minimax algorithm for finding the best move
     * 
     * @param depth        - depth is reached by the minimax
     * @param isMaxPlayer  - the player for whom we need to max the score (max player)
     * @return the string representation of the best move
     */
    private String miniMax(int depth, boolean isMaxPlayer){
    	// check which player we want to max score
        String player = isMaxPlayer? Constants.PLAYER_WHITE : Constants.PLAYER_BLACK;
        // initial best move is surrender
        String bestMove = player + Constants.SURRENDERS;
        // Setting high and low values
        int highValue = Integer.MIN_VALUE;
        int lowValue = Integer.MAX_VALUE;
        int currentValue;

        // get all moves of the max player
        List<String> nonSortedMoves = isMaxPlayer ? nextWhiteMoves() : nextBlackMoves();
        // sort moves on basis of piece values
        List<String> nextMoves = MoveFilter.sortMovesByPiece(nonSortedMoves, player);
        int counter = 0;

        // get the best move recursively
        for(String s : nextMoves){
        	
        	if(counter == Constants.FILTERTHRESHOLD)
        		break;
        	
            int boardTag = s.charAt(19) - Constants.ZERO;
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - Constants.SMALL_A;
            int preRow = 8 - (s.charAt(22) - Constants.ZERO);
            int col = s.charAt(27) - Constants.SMALL_A;
            int row = 8 - (s.charAt(28) - Constants.ZERO);
            char preChar = board.getFromBoard(boardTag, row, col);

            // when a pawn reaches the last row, it will be automatically promoted to a queen.
            if(ch == Constants.BLACK_PAWN && row == 7) ch = Constants.BLACK_QUEEN;
            if(ch == Constants.WHITE_PAWN && row == 0) ch = Constants.WHITE_QUEEN;

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

    /**
     * Get the max value
     * 
     * @param depth - depth is reached by the minimax
     * @return the maximum value reached for the given depth
     */
    private int max(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        // set best value to minimum
        int bestValue = Integer.MIN_VALUE;
        List<String> nextWhiteMoves = nextWhiteMoves();
        if (nextWhiteMoves.size() == 0) return bestValue;
        
        // calculate max value recursively
        for (String s : nextWhiteMoves) {
            int boardTag = s.charAt(19) - Constants.ZERO;
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - Constants.SMALL_A;
            int preRow = 8 - (s.charAt(22) - Constants.ZERO);
            int col = s.charAt(27) - Constants.SMALL_A;
            int row = 8 - (s.charAt(28) - Constants.ZERO);
            char preChar = board.getFromBoard(boardTag, row, col);

            // when a pawn reaches the last row, it will be automatically promoted to a queen.
            if(ch == Constants.BLACK_PAWN && row == 7) ch = Constants.BLACK_QUEEN;
            if(ch == Constants.WHITE_PAWN && row == 0) ch = Constants.WHITE_QUEEN;

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

    /**
     * Get the min value
     * 
     * @param depth - depth is reached by the minimax
     * @return the minimum value reached for the given depth
     */
    private int min(int depth) {
        if (depth == 0) {
            return evaluate();
        }

        // set best value to maximum
        int bestValue = Integer.MAX_VALUE;
        List<String> nextBlackMoves = nextBlackMoves();
        if(nextBlackMoves.size() == 0) return bestValue;
        
        // calculate minimum value recursively
        for(String s : nextBlackMoves){
            int boardTag = s.charAt(19) - Constants.ZERO;
            char ch = s.charAt(12);
            String color = s.substring(0, 5);
            if(color.equals(Constants.PLAYER_BLACK)) ch = Character.toLowerCase(ch);
            int preCol = s.charAt(21) - Constants.SMALL_A;
            int preRow = 8 - (s.charAt(22) - Constants.ZERO);
            int col = s.charAt(27) - Constants.SMALL_A;
            int row = 8 - (s.charAt(28) - Constants.ZERO);
            char preChar = board.getFromBoard(boardTag, row, col);

            // when a pawn reaches the last row, it will be automatically promoted to a queen.
            if(ch == Constants.BLACK_PAWN && row == 7) ch = Constants.BLACK_QUEEN;
            if(ch == Constants.WHITE_PAWN && row == 0) ch = Constants.WHITE_QUEEN;

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


    /**
     * This function evaluate the current board and return a score which is calculated by considering
     * the pieces, check, move ability, and ...
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
        if(!chessPieces.get(0).isKingSafe(Constants.PLAYER_WHITE)) sumBlack += Constants.CHECK;
        if(!chessPieces.get(0).isKingSafe(Constants.PLAYER_BLACK)) sumWhite += Constants.CHECK;

        return sumWhite - sumBlack;
    }

    /**
     * Get the the list of player scores
     *
     * @param board - board representing the pieces
     * @return      - list of player scores
     */
    private int[] evaluate(char[][] board) {
        // initialize the scores to zero
        int sumWhite = 0;
        int sumBlack = 0;

        for(int i = 0; i < 64; i++){
            int row = i / 8;
            int col = i % 8;
            switch (board[row][col]){
                case Constants.WHITE_PAWN :
                    sumWhite += Constants.STRENGTH_PAWN + Constants.PAWN_TABLE[row][col];
                    break;
                case Constants.WHITE_ROOK :
                    sumWhite += Constants.STRENGTH_ROOK + Constants.ROOK_TABLE[row][col];
                    break;
                case Constants.WHITE_KNIGHT :
                    sumWhite += Constants.STRENGTH_KNIGHT + Constants.KNIGHT_TABLE[row][col];
                    break;
                case Constants.WHITE_BISHOP :
                    sumWhite += Constants.STRENGTH_BISHOP + Constants.BISHOP_TABLE[row][col];
                    break;
                case Constants.WHITE_QUEEN :
                    sumWhite += Constants.STRENGTH_QUEEN + Constants.QUEEN_TABLE[row][col];
                    break;
                case Constants.WHITE_KING :
                    sumWhite += Constants.STRENGTH_KING;
                    break;
                case Constants.BLACK_PAWN :
                    sumBlack += Constants.STRENGTH_PAWN + Constants.PAWN_TABLE[7 - row][7 - col];
                    break;
                case Constants.BLACK_ROOK :
                    sumBlack += Constants.STRENGTH_ROOK + Constants.ROOK_TABLE[7 - row][7 - col];
                    break;
                case Constants.BLACK_KNIGHT :
                    sumBlack += Constants.STRENGTH_KNIGHT + Constants.KNIGHT_TABLE[7 - row][7 - col];
                    break;
                case Constants.BLACK_BISHOP :
                    sumBlack += Constants.STRENGTH_BISHOP + Constants.BISHOP_TABLE[7 - row][7 - col];
                    break;
                case Constants.BLACK_QUEEN :
                    sumBlack += Constants.STRENGTH_QUEEN + Constants.QUEEN_TABLE[7 - row][7 - col];
                    break;
                case Constants.BLACK_KING :
                    sumBlack += Constants.STRENGTH_KING;
                    break;
            }
        }

        return new int[] {sumWhite, sumBlack};
    }
}

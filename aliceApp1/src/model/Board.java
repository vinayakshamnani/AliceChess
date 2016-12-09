package model;

import util.Constants;

/**
 * The model class that holds the game state
 * This is as simple as holding a 2D array of the chess boards.
 * 
 * White pieces are represented as capital letters while black ones as small letters.
 * 
 * @author krish
 *
 */
public class Board {
	
	
	/**
	 * A notation of the rows.
	 */
	private char[] row = {'8', '7', '6', '5', '4', '3', '2', '1'};
	
	/**
	 * A notation of the columns.
	 */
    private char[] col = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    /**
     * Board A representation
     * 
     * Black pieces are represented by lower case and white pieces are represented in upper case
     */
    private char[][] boardA = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
    };

    /**
     * Board B representation
     */
    private char[][] boardB = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };
    
    /**
     * Get the piece information from the board
     * 
     * @param board - board tag, it can be board 1 or 2
     * @param row   - row index of the given piece 
     * @param col   - column index of the given piece
     * @return      - the piece info present at the given position
     */
    public char getFromBoard(int board, int row, int col) {
    	return board == Constants.BOARD_A ? boardA[row][col] : boardB[row][col];
    }

    /**
     * Set the piece information in the board
     * 
     * @param boardTag - board tag, it can be board 1 or 2
     * @param row      - row index of the given piece
     * @param col      - column index of the given piece
     * @param ch       - the piece info to set at the given position
     */
	public void setBoard(int boardTag, int row, int col, char ch) {
		if(boardTag == Constants.BOARD_A) {
			boardA[row][col] = ch;
		}
		else{
			boardB[row][col] = ch;
		}
	}
    
	/**
	 * Get the character present at the given row index
	 * 
	 * @param rowVal - row index
	 * @return       - character present at the row index in row array
	 */
    public char row(int rowVal) {
    	return row[rowVal];
    }
    
    /**
     * Get the character present at the given index
     * 
     * @param colVal - column index
     * @return       - character present at the column index in col array
     */
    public char col(int colVal) {
    	return col[colVal];
    }

    /**
     * Get the row array
     * 
     * @return - the row array
     */
	public char[] getRow() {
		return row;
	}

	/**
	 * Set the row array 
	 * 
	 * @param row - the new row array
	 */
	public void setRow(char[] row) {
		this.row = row;
	}

	/**
	 * Get the col array
	 * 
	 * @return - the col array
	 */
	public char[] getCol() {
		return col;
	}

	/**
	 * Set the col array
	 * 
	 * @param col - the new col array
	 */
	public void setCol(char[] col) {
		this.col = col;
	}

	/**
	 * Get the the first board
	 * 
	 * @return - the first board
	 */
	public char[][] getBoardA() {
		return boardA;
	}

	/**
	 * Set the first board
	 * 
	 * @param boardA - the new board
	 */
	public void setBoardA(char[][] boardA) {
		this.boardA = boardA;
	}

	/**
	 * Get the second board
	 * 
	 * @return - the second board
	 */
	public char[][] getBoardB() {
		return boardB;
	}

	/**
	 * Set the second board
	 * 
	 * @param boardB - the new board
	 */
	public void setBoardB(char[][] boardB) {
		this.boardB = boardB;
	}
}

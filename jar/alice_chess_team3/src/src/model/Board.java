package model;

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
	 * A simple notation of Board A
	 */
	public static final int BOARD_A = 1;
	
	/**
	 * A simple notation of Board B
	 */
	public static final int BOARD_B = 2;
	
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
    
    public char getFromBoard(int board, int row, int col) {
    	return board == BOARD_A ? boardA[row][col] : boardB[row][col];
    }

	public void setBoard(int boardTag, int row, int col, char ch) {
		if(boardTag == BOARD_A) {
			boardA[row][col] = ch;
		}
		else{
			boardB[row][col] = ch;
		}
	}
    
    public char row(int rowVal) {
    	return row[rowVal];
    }
    
    public char col(int colVal) {
    	return col[colVal];
    }

	public char[] getRow() {
		return row;
	}

	public void setRow(char[] row) {
		this.row = row;
	}

	public char[] getCol() {
		return col;
	}

	public void setCol(char[] col) {
		this.col = col;
	}

	public char[][] getBoardA() {
		return boardA;
	}

	public void setBoardA(char[][] boardA) {
		this.boardA = boardA;
	}

	public char[][] getBoardB() {
		return boardB;
	}

	public void setBoardB(char[][] boardB) {
		this.boardB = boardB;
	}
    
    
    
}

package model;

public class Board {
	public static final int BOARD_A = 1;
	public static final int BOARD_B = 2;
	
	
	private char[] row = {'8', '7', '6', '5', '4', '3', '2', '1'};
    private char[] col = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

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

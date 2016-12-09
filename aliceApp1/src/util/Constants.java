package util;

public class Constants {

	// Constants for representing black player pieces
	public static final char WHITE_PAWN = 'P';
	public static final char WHITE_ROOK = 'R';
	public static final char WHITE_KNIGHT = 'N';
	public static final char WHITE_BISHOP = 'B';
	public static final char WHITE_QUEEN = 'Q';
	public static final char WHITE_KING = 'K';
	
	// Constants for representing white player pieces
	public static final char BLACK_PAWN = 'p';
	public static final char BLACK_ROOK = 'r';
	public static final char BLACK_KNIGHT = 'n';
	public static final char BLACK_BISHOP = 'b';
	public static final char BLACK_QUEEN = 'q';
	public static final char BLACK_KING = 'k';
	
	// Constant for representing white player
	public static final String PLAYER_WHITE = "white";
	// Constant for representing black player
	public static final String PLAYER_BLACK = "black";
	
	// Constants for checking if piece is black
	public static final char CAP_A = 'A';
	public static final char CAP_Z = 'Z';
	
	// Constants for checking if piece is white
	public static final char SMALL_A = 'a';
	public static final char SMALL_Z = 'z';
	
	// Constants for piece values
	public static final int STRENGTH_PAWN = 100;
	public static final int STRENGTH_ROOK = 820;
	public static final int STRENGTH_KNIGHT = 320;
	public static final int STRENGTH_BISHOP = 540;
	public static final int STRENGTH_QUEEN = 1320;
	public static final int STRENGTH_KING = 20000;

	// Constants for check values
	public static final int CHECK = 50;

	// Constant for mini-max depth
	public final static int DEPTH = 3;
	// Constant for max threshold moves
	public final static int FILTERTHRESHOLD = 35;
	// Constant for representing zero
	public static final char ZERO = '0';
	
	// Constant for surrender string
	public static final String SURRENDERS = " surrenders";


	/**
	 * Tables for Piece-Square values
	 * See https://chessprogramming.wikispaces.com/Simplified+evaluation+function
	 */
	// Constant for Pawn position values
	public static final int[][] PAWN_TABLE = {
			{0,  0,  0,  0,  0,  0,  0,  0},
			{50, 50, 50, 50, 50, 50, 50, 50},
			{10, 10, 20, 30, 30, 20, 10, 10},
			{5,  5, 10, 25, 25, 10,  5,  5},
			{0,  0,  0, 20, 20,  0,  0,  0},
			{5, -5,-10,  0,  0,-10, -5,  5},
			{5, 10, 10,-20,-20, 10, 10,  5},
			{0,  0,  0,  0,  0,  0,  0,  0},
	};

	// Constant for Rook position values
	public static final int[][] ROOK_TABLE = {
			{0,  0,  0,  0,  0,  0,  0,  0},
			{5, 10, 10, 10, 10, 10, 10,  5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{-5,  0,  0,  0,  0,  0,  0, -5},
			{0,  0,  0,  5,  5,  0,  0,  0},
	};

	// Constant for Knight position values
	public static final int[][] KNIGHT_TABLE = {
			{-50,-40,-30,-30,-30,-30,-40,-50},
			{-40,-20,  0,  0,  0,  0,-20,-40},
			{-30,  0, 10, 15, 15, 10,  0,-30},
			{-30,  5, 15, 20, 20, 15,  5,-30},
			{-30,  0, 15, 20, 20, 15,  0,-30},
			{-30,  5, 10, 15, 15, 10,  5,-30},
			{-40,-20,  0,  5,  5,  0,-20,-40},
			{-50,-40,-30,-30,-30,-30,-40,-50},
	};

	// Constant for Bishop position values
	public static final int[][] BISHOP_TABLE = {
			{-20,-10,-10,-10,-10,-10,-10,-20},
			{-10,  0,  0,  0,  0,  0,  0,-10},
			{-10,  0,  5, 10, 10,  5,  0,-10},
			{-10,  5,  5, 10, 10,  5,  5,-10},
			{-10,  0, 10, 10, 10, 10,  0,-10},
			{-10, 10, 10, 10, 10, 10, 10,-10},
			{-10,  5,  0,  0,  0,  0,  5,-10},
			{-20,-10,-10,-10,-10,-10,-10,-20},
	};

	// Constant for Queen position values
	public static final int[][] QUEEN_TABLE = {
			{-20,-10,-10, -5, -5,-10,-10,-20},
			{-10,  0,  0,  0,  0,  0,  0,-10},
			{-10,  0,  5,  5,  5,  5,  0,-10},
			{-5,  0,  5,  5,  5,  5,  0, -5},
			{0,  0,  5,  5,  5,  5,  0, -5},
			{-10,  5,  5,  5,  5,  5,  0,-10},
			{-10,  0,  5,  0,  0,  0,  0,-10},
			{-20,-10,-10, -5, -5,-10,-10,-20},
	};

	/**
	 * A simple notation of Board A
	 */
	public static final int BOARD_A = 1;
	
	/**
	 * A simple notation of Board B
	 */
	public static final int BOARD_B = 2;

}

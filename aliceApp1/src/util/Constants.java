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
	public static final int STRENGTH_PAWN = 10;
	public static final int STRENGTH_ROOK = 82;
	public static final int STRENGTH_KNIGHT = 32;
	public static final int STRENGTH_BISHOP = 54;
	public static final int STRENGTH_QUEEN = 132;
	public static final int STRENGTH_KING = 10000;
	
	// Constant for mini-max depth
	public final static int DEPTH = 3;
	// Constant for max threshold moves
	public final static int FILTERTHRESHOLD = 35;
	// Constant for representing zero
	public static final char ZERO = '0';
	
	// Constant for surrender string
	public static final String SURRENDERS = " surrenders";

}

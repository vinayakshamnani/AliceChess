package ai;

import util.Constants;

/**
 * The Factory class for the AI module.
 * All the prototypes/implementations for the required components are mapped here.
 * Depending on the usage, we can simply plug and play the right implementation in this Factory to
 * expose the right implementation.
 * 
 * @author krish
 *
 */
public class AliceAIFactory {

	/**
	 * The instance for this Singleton class.
	 */
	private static AliceAIFactory Instance;
	
	/**
	 * The constructor made private
	 */
	private AliceAIFactory() {
		/* Do Nothing */
	}
	
	/**
	 * The getInstance method to get your hands on the Instance variable.
	 * If the variable has been created, it returns the same variable,
	 * else creates a new instance and returns it back to the calling program.
	 * 
	 * This ensures that there's only one instance of the AliceAIFactory variable in memory.
	 * 
	 * @return AliceAIFactory - The Instance variable required to access the Factory class.
	 */
	public static AliceAIFactory GetInstance() {
		if(Instance == null) {
			Instance = new AliceAIFactory();
		}
		
		return Instance;
	}
	
	/**
	 * The method that returns the implementation of the AliceAI api.
	 * Any mapping change has to be done here. The calling program should not
	 * be aware of the actual implementation.
	 * 
	 * @return AliceAI - The implementation for the api you want mapped for this set of
	 * functionality.
	 */
	public AliceAI getAIComponent() {
		// As of now, mapped BaseAliceAIImpl to AliceAI api.
		AliceAI aiComponent = new BaseAliceAIImpl();
		
		aiComponent.addChessPieceToAI(new PawnChessPiece(aiComponent.getAIBoard(), Constants.WHITE_PAWN));
		aiComponent.addChessPieceToAI(new PawnChessPiece(aiComponent.getAIBoard(), Constants.BLACK_PAWN));
		
		aiComponent.addChessPieceToAI(new RookChessPiece(aiComponent.getAIBoard(), Constants.WHITE_ROOK));
		aiComponent.addChessPieceToAI(new RookChessPiece(aiComponent.getAIBoard(), Constants.BLACK_ROOK));
		
		aiComponent.addChessPieceToAI(new KnightChessPiece(aiComponent.getAIBoard(), Constants.WHITE_KNIGHT));
		aiComponent.addChessPieceToAI(new KnightChessPiece(aiComponent.getAIBoard(), Constants.BLACK_KNIGHT));
		
		aiComponent.addChessPieceToAI(new BishopChessPiece(aiComponent.getAIBoard(), Constants.WHITE_BISHOP));
		aiComponent.addChessPieceToAI(new BishopChessPiece(aiComponent.getAIBoard(), Constants.BLACK_BISHOP));
		
		aiComponent.addChessPieceToAI(new QueenChessPiece(aiComponent.getAIBoard(), Constants.WHITE_QUEEN));
		aiComponent.addChessPieceToAI(new QueenChessPiece(aiComponent.getAIBoard(), Constants.BLACK_QUEEN));
		
		aiComponent.addChessPieceToAI(new KingChessPiece(aiComponent.getAIBoard(), Constants.WHITE_KING));
		aiComponent.addChessPieceToAI(new KingChessPiece(aiComponent.getAIBoard(), Constants.BLACK_KING));
		
		return aiComponent;
	}
	
	
}

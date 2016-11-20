package ai;

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
		
		return aiComponent;
	}
}

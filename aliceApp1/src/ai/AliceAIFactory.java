package ai;

public class AliceAIFactory {

	private static AliceAIFactory Instance;
	
	private AliceAIFactory() {
		
	}
	
	public static AliceAIFactory GetInstance() {
		if(Instance == null) {
			Instance = new AliceAIFactory();
		}
		
		return Instance;
	}
	
	public AliceAI getAIComponent() {
		AliceAI aiComponent = new BaseAliceAIImpl();
		
		return aiComponent;
	}
}

package Values;

public class Parameter {

	private int clustertion;
	private static Parameter instance = null;
	private Parameter(){}
	
	public static synchronized Parameter getInstance() {
		if (instance == null) {
			instance = new Parameter();
		}
		return instance;
	}
	
	public void setClustertion(int clustertion) {
		this.clustertion = clustertion;
	}
	
	public int getClustertion() {
		return this.clustertion;
	}
}

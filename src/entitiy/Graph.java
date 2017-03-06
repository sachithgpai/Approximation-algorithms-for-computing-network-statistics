package entitiy;

import java.util.HashMap;

public class Graph {

	private HashMap<Integer, Node> hMap;
	
	public Graph(){
    	hMap = new HashMap<Integer, Node>();
    }
	
	public HashMap<Integer, Node> gethMap() {
		return hMap;
	}
	
	public void sethMap(HashMap<Integer, Node> hMap) {
		this.hMap = hMap;
	}  
}

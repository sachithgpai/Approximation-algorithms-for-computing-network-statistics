package entitiy;

import java.util.*;

public class TarjanGraph {
	
	private HashMap<Integer, TarjanNode> hMap;
	
	public TarjanGraph(){
    	hMap = new HashMap<Integer, TarjanNode>();
    }
	
	public HashMap<Integer, TarjanNode> gethMap() {
		return hMap;
	}
	
	public void sethMap(HashMap<Integer, TarjanNode> hMap) {
		this.hMap = hMap;
	}
	
}

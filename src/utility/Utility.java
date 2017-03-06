package utility;

import java.io.*;
import java.util.*;

import entitiy.*;
import graph.algorithms.DFS;
import graph.algorithms.Tarjan;

public class Utility {
	
	public static void addToTarjanGraph(Integer fromNodeId, Integer toNodeId, HashMap<Integer, TarjanNode> hMap){
		TarjanNode objNode;
		if (!hMap.containsKey(fromNodeId)){
			objNode = new TarjanNode(fromNodeId);
			objNode.getAdjList().add(toNodeId);
			hMap.put(fromNodeId, objNode);		    		   
			if (!hMap.containsKey(toNodeId)){
				objNode = new TarjanNode(toNodeId);
				hMap.put(toNodeId, objNode);		    			   
			}
		}else{
			hMap.get(fromNodeId).getAdjList().add(toNodeId);
			if (!hMap.containsKey(toNodeId) ){
				objNode = new TarjanNode(toNodeId);
				hMap.put(toNodeId, objNode);
			}
		}
	}
	
	
	public static void addToDirectedGraph(Integer fromNodeId, Integer toNodeId, HashMap<Integer, Node> hMap){
		Node objNode;
		if (!hMap.containsKey(fromNodeId)){
			objNode = new Node(fromNodeId);
			objNode.populateBitHashStr();
			objNode.getAdjList().add(toNodeId);
			hMap.put(fromNodeId, objNode);		    		   
			if (!hMap.containsKey(toNodeId)){
				objNode = new Node(toNodeId);
				hMap.put(toNodeId, objNode);		    			   
			}
		}else{
			hMap.get(fromNodeId).getAdjList().add(toNodeId);
			if (!hMap.containsKey(toNodeId) ){
				objNode = new Node(toNodeId);
				objNode.populateBitHashStr();
				hMap.put(toNodeId, objNode);
			}
		}
	}
	
	
	public static void addToUndirectedGraph(Integer fromNodeId, Integer toNodeId, HashMap<Integer, Node> hMap){
		Node objNode;
		if (!hMap.containsKey(fromNodeId)){
			objNode = new Node(fromNodeId);
			objNode.getAdjList().add(toNodeId);
			hMap.put(fromNodeId, objNode);
			if (!hMap.containsKey(toNodeId)){
				objNode = new Node(toNodeId);
				objNode.getAdjList().add(fromNodeId);
				hMap.put(toNodeId, objNode);
			}else{
				hMap.get(toNodeId).getAdjList().add(fromNodeId);
			}
		}else{
			hMap.get(fromNodeId).getAdjList().add(toNodeId);
			if (!hMap.containsKey(toNodeId)){
				objNode = new Node(toNodeId);		    			
				objNode.getAdjList().add(fromNodeId);
				hMap.put(toNodeId, objNode);
			}else{
				hMap.get(toNodeId).getAdjList().add(fromNodeId);
			}
		}	
	}
	
	public static void saveLSCC() throws Exception{
		Tarjan tr = new Tarjan(AppConfiguration.TARJAN_GRAPH);
		tr.getGraphSCCList();
		ArrayList<TarjanNode> nodeList = tr.getLscc();
		
//		AppConfiguration.BWG.write("\n@" + new Date() + " : LSCC Node Count: " + nodeList.size());		
		System.out.println("@ [" + new Date() + "] LSCC Node Count: " + nodeList.size());
		
		int edgeCount = 0;
		try{
			String filePath = AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR + AppConfiguration.LCC_FILE_NAME;
			int size = nodeList.size();
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			TarjanNode node;
			int fromNodeId;
			ArrayList neighbourList;
			int nSize = 0;
			TarjanNode toNodeId;
			String line = "";
			for(int i = 0; i < size; i++){
				node = nodeList.get(i);
				fromNodeId = node.getNodeId();
				neighbourList = node.getAdjList();
				nSize = neighbourList.size();
				for(int j = 0; j < nSize; j++){
					Integer nId = (Integer)neighbourList.get(j);				
					toNodeId = tr.getGraph().gethMap().get(nId);
					if(node.isInSCC() && toNodeId.isInSCC()){
						line = fromNodeId + "	" + nId +"\n";
						bw.write(line);
						edgeCount++;
					}
				}
			}
			bw.close();
			fw.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
//		AppConfiguration.BWG.write("\n@" + new Date() + " : LSCC Edge Count: " + edgeCount);
		System.out.println("@ [" + new Date() + "] LSCC Edge Count: " + edgeCount);
	}
	
	public static void saveLWCC() throws Exception{
		DFS dfs = new DFS(AppConfiguration.UNDIRECTED_GRAPH);
		dfs.LWCC();
		ArrayList<Node> nodeList = dfs.getLwcc();
//		AppConfiguration.BWG.write("\n@" + new Date() + " : LWCC Node Count: " + nodeList.size());
		System.out.println("@ [" + new Date() + "] LWCC Node Count: " + nodeList.size());
		
		int edgeCount = 0;
		try{
			String filePath = AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR + AppConfiguration.LCC_FILE_NAME;
			int size = nodeList.size();
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < size; i++){
				Integer key = new Integer(nodeList.get(i).getNodeId());
				Node node = AppConfiguration.UNDIRECTED_GRAPH.gethMap().get(key);
				
				Integer fromNodeId = node.getNodeId();
				ArrayList neighbourList = node.getAdjList();
				int nSize = neighbourList.size();
				for(int j = 0; j < nSize; j++){
					int toNodeId = (Integer)neighbourList.get(j);
//					if(node.getNodeId() == 8 && toNodeId == 319)
//						System.out.println("Yes " + fromNodeId + " && " + toNodeId);
					String line = fromNodeId + "	" + toNodeId +"\n";
					bw.write(line);
					edgeCount++;
//					System.out.println("counter " +edgeCount);
				}
			}
			bw.close();
			fw.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
//		AppConfiguration.BWG.write("\n@" + new Date() + " : LWCC Edge Count: " + (edgeCount/2));
		System.out.println("@ [" + new Date() + "] LWCC Edge Count: " + (edgeCount/2));
	}
	
	public static void calculateGraphStatistics(HashMap<Integer, Long> distanceMap){
		//get ArrayList to sort it
		ArrayList<Distance> distanceList = new ArrayList<Distance>();
		for(Map.Entry<Integer, Long> d: distanceMap.entrySet()){
			distanceList.add(new Distance(d.getKey(), d.getValue()));
		}
		Collections.sort(distanceList);
		// end sorting
		
		try{
//			AppConfiguration.BWG.write("\nDistance	Count\n");
			System.out.println("Distance	Count");
			
			double distanceTotalSum = 0;
			double distanceTotalCount = 0;
			int size = distanceList.size();
			for(int i = 0; i < size; i++){
				int distacneValue = distanceList.get(i).getDistance();
				long distanceCount = distanceList.get(i).getCount();
				distanceTotalSum +=  distacneValue * distanceCount;
				distanceTotalCount += distanceCount;				
				if(i == size-1)
					AppConfiguration.DIAMETER = distacneValue;
//				AppConfiguration.BWG.write(distacneValue + "	" + distanceCount +"\n");
				System.out.println(distacneValue + "		" + distanceCount);
			}
			AppConfiguration.MEAN = Math.round((distanceTotalSum / distanceTotalCount) * 100)/100.0d;
			double medianIndex = distanceTotalCount / 2;
			double effectiveDiameterIndex = distanceTotalCount * 0.9;
			for(int i = 0; i < size; i++){
				int distacneValue = distanceList.get(i).getDistance();
				long distanceCount = distanceList.get(i).getCount();
				medianIndex -= distanceCount;
				effectiveDiameterIndex -= distanceCount;
				if(medianIndex <= 0 && AppConfiguration.MEDIAN == -1)
					AppConfiguration.MEDIAN = Math.round(distacneValue*100)/100.0d;
				if(effectiveDiameterIndex <= 0 && AppConfiguration.EFFECTIVE_DIAMETER == -1)
					AppConfiguration.EFFECTIVE_DIAMETER = distacneValue;
			}
//			AppConfiguration.BWG.write("\nMean: " +AppConfiguration.MEAN);
//			AppConfiguration.BWG.write("\nMedian: " +AppConfiguration.MEDIAN);
//			AppConfiguration.BWG.write("\nDiameter: " +AppConfiguration.DIAMETER);
//			AppConfiguration.BWG.write("\nEffective Diameter: " +AppConfiguration.EFFECTIVE_DIAMETER);
			
			System.out.println("Mean: " +AppConfiguration.MEAN);
			System.out.println("Median: " +AppConfiguration.MEDIAN);
			System.out.println("Diameter: " +AppConfiguration.DIAMETER);
			System.out.println("Effective Diameter: " +AppConfiguration.EFFECTIVE_DIAMETER);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void loadTarjanDirectedGraph(){
		Integer fromNodeId;
		Integer toNodeId;
 	   	String [] edgeNodes;
 	   	HashMap<Integer, TarjanNode> tarjanGraphMap;
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR + AppConfiguration.FILE_NAME))) {
		    String line;
		    int count = 0;
		    tarjanGraphMap = AppConfiguration.TARJAN_GRAPH.gethMap();
		    while ((line = br.readLine()) != null) {
		       if(count >= AppConfiguration.IGNORE_LINES){
		    	   edgeNodes = line.split("\\s+");
		    	   fromNodeId = Integer.parseInt(edgeNodes[0]);
		    	   toNodeId = Integer.parseInt(edgeNodes[1]);  	   
		    	   Utility.addToTarjanGraph(fromNodeId, toNodeId, tarjanGraphMap);
		       }
		       count++;
		       if(count % AppConfiguration.THRESHOLD == 0)
		    	   System.out.println("@ [" + new Date() + "] Line #: " + count + " LOADED..");
		    }
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void loadUndirectedGraph(){
		Integer fromNodeId;
		Integer toNodeId;
 	   	String [] edgeNodes;
	    HashMap<Integer, Node> unDirectedGraphMap;
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR + AppConfiguration.FILE_NAME))) {
		    String line;
		    int count = 0;
		    unDirectedGraphMap = AppConfiguration.UNDIRECTED_GRAPH.gethMap();
//		    System.out.println("undierected size: " + unDirectedGraphMap.size());
		    while ((line = br.readLine()) != null) {
		       if(count >= AppConfiguration.IGNORE_LINES){
		    	   edgeNodes = line.split("\\s+");
		    	   fromNodeId = Integer.parseInt(edgeNodes[0]);
		    	   toNodeId = Integer.parseInt(edgeNodes[1]);
		    	   Utility.addToUndirectedGraph(fromNodeId, toNodeId, unDirectedGraphMap);
		       }
		       count++;
		       if(count % AppConfiguration.THRESHOLD == 0)
		    	   System.out.println("@ [" + new Date() + "] Line #: " + count + " LOADED..");
		    }		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void loadLCC(String graphType){
		Integer fromNodeId;
		Integer toNodeId;
 	   	String [] edgeNodes;
	    HashMap<Integer, Node> graphMap = null;
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR + AppConfiguration.LCC_FILE_NAME))) {
		    String line;
		    int count = 0;
		    if(graphType.equals("Directed")){
		    	graphMap = AppConfiguration.DIRECTED_GRAPH.gethMap();
		    }else if(graphType.equals("Undirected")){
		    	graphMap = AppConfiguration.UNDIRECTED_GRAPH.gethMap();
		    }
		    while ((line = br.readLine()) != null) {
	    	   edgeNodes = line.split("\\s+");
	    	   fromNodeId = Integer.parseInt(edgeNodes[0]);
	    	   toNodeId = Integer.parseInt(edgeNodes[1]);  	   
//	    	   if(graphType.equals("Directed")){
	    	   Utility.addToDirectedGraph(fromNodeId, toNodeId, graphMap);
//	    	   }else if(graphType.equals("Undirected")){
//	    		   Utility.addToUndirectedGraph(fromNodeId, toNodeId, graphMap);
//	    	   }
		       count++;
//		       System.out.println("COUNT: " + count);
		       if(count % AppConfiguration.THRESHOLD == 0)
		    	   System.out.println("@ [" + new Date() + "] LCC-Line #: " + count + " LOADED..");
		    }		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}

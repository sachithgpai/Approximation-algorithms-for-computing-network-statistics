package graph.algorithms;

import java.util.*;
import entitiy.*;

public class BFS {
	
	private Graph graph;
	private Queue<Node> levelQ;
	private HashMap<Integer, Long> distanceMap;
	public static int edgecount = 0;
	
	public BFS(){
		levelQ = new LinkedList<Node>();
		this.distanceMap = new HashMap<Integer, Long>();
	}
	
	public BFS(Graph graph){
		this();
		this.graph = graph;
	}
	
	public void bfs(Node s){
		cleanGraph();
		s.setVisited(true);
		levelQ.offer(s);
		
		Node v;
		ArrayList<Integer> adjList;
		int adjListSize;
		Node u;
		int key;
		long n = 1;
		while(levelQ.size() > 0){
			v = levelQ.poll();
			adjList = v.getAdjList();
			adjListSize = adjList.size();
			for(int i = 0; i < adjListSize; i++){
				u = this.getGraph().gethMap().get(adjList.get(i));
				if(v.getLevel() == 0 && u.isVisited()){
					edgecount++;
				}
					
				if(!u.isVisited()){
					u.setVisited(true);
					u.setParentNodeId(v.getParentNodeId());
					u.setLevel(v.getLevel()+1);
					levelQ.offer(u);
					//update distance map
					key = u.getLevel();
					if (distanceMap.containsKey(key)){
						distanceMap.put(key, distanceMap.get(key) + 1);
					}else{
						distanceMap.put(key, n);
					}
				}
			}
		}
	}
	
	
	public void bfs(Node fromNode, Node toNode){
		cleanGraph();
		fromNode.setVisited(true);
		levelQ.offer(fromNode);
		
		Node v;
		ArrayList<Integer> adjList;
		int adjListSize;
		Node u;
		int key;
		while(levelQ.size() > 0){
			v = levelQ.poll();
			adjList = v.getAdjList();
			adjListSize = adjList.size();
			for(int i = 0; i < adjListSize; i++){
				u = this.getGraph().gethMap().get(adjList.get(i));
				if(!u.isVisited()){
					u.setVisited(true);
					u.setParentNodeId(v.getParentNodeId());
					u.setLevel(v.getLevel()+1);
					levelQ.offer(u);
					//update distance map
					if(u.getNodeId() == toNode.getNodeId()){
						key = u.getLevel();
						if (distanceMap.containsKey(key)){
							distanceMap.put(key, distanceMap.get(key) + 1);
						}else{
							long n = 1;
							distanceMap.put(key, n);
						}
					}
				}
			}
			
		}
	}
	
	
	public void cleanGraph(){
		for (Node v : graph.gethMap().values()) {
		    v.setLevel(0);
		    v.setParentNodeId(0);
		    v.setVisited(false);
		}
	}
	
	public void calcShortestPairDistances(Graph graph){
		if(this.graph == null)
			this.graph = graph;
//		long graphSize = graph.gethMap().size();
//		this.distanceMap.put(0, graphSize);
		int c = 0;
		for (Node s : graph.gethMap().values()){
//			System.out.println("Node#: " + (++c));
			bfs(s);
		}
//		System.out.println("edgecount " + edgecount);
	}
	
	
	public void calcDistanceUsingSampleSource(Graph graph){
		if(this.graph == null)
			this.graph = graph;
//		long zeroDistance = 0;
		for (Node s : graph.gethMap().values()){
		    if(s.isSelected()){
//		    	zeroDistance++;
		    	bfs(s);
		    }
		}
//		this.distanceMap.put(0, zeroDistance);
	}
	
	public void calcDistanceUsingSamplePair(Graph graph, ArrayList<RandomPair> randomPairList){
		if(this.graph == null)
			this.graph = graph;
		int listSize = randomPairList.size();		
		for(int i = 0;  i < listSize; i++){
//			System.out.println("i : " +i);
			int count = 0;
			Node fromNode = new Node();
			Node toNode = new Node();
			for (Node s : graph.gethMap().values()){
				if(count == randomPairList.get(i).getFromId()){
					fromNode = s;
				}
				if(count == randomPairList.get(i).getToId()){
					toNode = s;
				}
				count++;
			}
			bfs(fromNode, toNode);	
		}		
	}
		
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public HashMap<Integer, Long> getDistanceMap() {
		return distanceMap;
	}

	public void setDistanceMap(HashMap<Integer, Long> distanceMap) {
		this.distanceMap = distanceMap;
	}
	
	
}

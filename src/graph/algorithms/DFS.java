package graph.algorithms;

import java.util.ArrayList;
import java.util.Map;

import entitiy.*;

public class DFS {
	
	private Graph graph;
	private int numWCC;
	private ArrayList<Node> lwcc;
	private ArrayList<Node> currentWcc;
	private int count;
	
	
	public DFS(){
		numWCC = 0;
		lwcc = new ArrayList<Node>();
		currentWcc = new ArrayList<Node>();
		count = 0;
	}
	
	public DFS(Graph graph){
		this();
		this.graph = graph;
	}
	
	public void LWCC(){
		for (Map.Entry<Integer, Node> nodeV : graph.gethMap().entrySet()){
			Node v = nodeV.getValue();
//			if(v.getNodeId() == 8){
//				for(int i = 0; i < v.getAdjList().size(); i++){
//					if(v.getAdjList().get(i) == 319)
//						System.out.println("---> 319");
//				}
//			}
			if(!v.isVisited()){
				currentWcc.add(v);
				count = 1;
				dfsVisit(v);
				if(count > lwcc.size()){
					lwcc = currentWcc;
				}
				currentWcc = new ArrayList<Node>();
			}
		}
	}
	
	public void dfsVisit(Node v){
		count++;
		v.setVisited(true);
		ArrayList<Integer> neighbourList = v.getAdjList();
		int listSize = neighbourList.size();
		for(int i = 0; i < listSize; i++){
			Node childNode = graph.gethMap().get(neighbourList.get(i));
			if(!childNode.isVisited()){
				dfsVisit(childNode);
				currentWcc.add(childNode);
			}
		}
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public int getNumWCC() {
		return numWCC;
	}

	public void setNumWCC(int numWCC) {
		this.numWCC = numWCC;
	}

	public ArrayList<Node> getLwcc() {
		return lwcc;
	}

	public void setLwcc(ArrayList<Node> lwcc) {
		this.lwcc = lwcc;
	}
	
}

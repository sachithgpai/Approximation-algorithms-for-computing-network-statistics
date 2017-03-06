package entitiy;

import java.util.ArrayList;

//Customized node to be used by Tarjan algorithm.
public class TarjanNode{
	
	private int nodeId;
	private ArrayList<Integer> adjList;
	private boolean visited;
	private int nodeIndex;
	private int lowestLink;
	private boolean onStack;
	private boolean inSCC;
	
	public TarjanNode(){
		adjList = new ArrayList<Integer>();
		visited = false;	
		nodeIndex = 0;
		lowestLink = 0;
		onStack = false;
		inSCC = false;
	}
	
	public TarjanNode(int nodeId){
		this();
		this.nodeId = nodeId;
	}
	
	public int getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public int getLowestLink() {
		return lowestLink;
	}

	public void setLowestLink(int lowestLink) {
		this.lowestLink = lowestLink;
	}

	public boolean isOnStack() {
		return onStack;
	}

	public void setOnStack(boolean onStack) {
		this.onStack = onStack;
	}

	public boolean isInSCC() {
		return inSCC;
	}

	public void setInSCC(boolean inSCC) {
		this.inSCC = inSCC;
	}
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public ArrayList<Integer> getAdjList() {
		return adjList;
	}
	public void setAdjList(ArrayList<Integer> adjList) {
		this.adjList = adjList;
	}
	
	public void addNeighbour(Integer neighbour){
		this.adjList.add(neighbour);
	}
	
	public int getNeighbourSize(){
		return this.adjList.size();
	}
	
	public boolean isVisited(){
		return this.visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
}

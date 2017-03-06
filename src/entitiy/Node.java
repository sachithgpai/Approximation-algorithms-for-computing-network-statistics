package entitiy;

import java.util.ArrayList;
import utility.AppConfiguration;

//Normal node to be used by all algorithms other than Tarjan.
public class Node {
	
	private int nodeId;
	private ArrayList<Integer> adjList;
	private boolean visited;
	private int parentNodeId;
	private int level;
	private long bitHashAtTimeT;
	private long bitHashAtTimeTMinusOne;
	private boolean selected;
	
	public Node(){
		adjList = new ArrayList<Integer>();
		visited = false;
		parentNodeId = 0;
		level = 0;
		bitHashAtTimeT = 0;
		bitHashAtTimeTMinusOne = 0;
		selected = false;
	}
	public Node(int nodeId){
		this();
		this.nodeId = nodeId;
	}
	
	public void populateBitHashStr(){
		float prob = 0;
		for(int i = 0; i < AppConfiguration.BIT_HASH_SIZE; i++){ 
			prob = AppConfiguration.RN.nextFloat();
			if(prob < 1/(Math.pow(2, i+1)))
				bitHashAtTimeTMinusOne = bitHashAtTimeTMinusOne | (1 << i);
		}
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
	
	public int getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(int parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getBitHashAtTimeT() {
		return bitHashAtTimeT;
	}
	public void setBitHashAtTimeT(long bitHashAtTimeT) {
		this.bitHashAtTimeT = bitHashAtTimeT;
	}
	public long getBitHashAtTimeTMinusOne() {
		return bitHashAtTimeTMinusOne;
	}
	public void setBitHashAtTimeTMinusOne(long bitHashAtTimeTMinusOne) {
		this.bitHashAtTimeTMinusOne = bitHashAtTimeTMinusOne;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
		
}

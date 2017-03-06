package graph.algorithms;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import entitiy.*;

public class Tarjan{
	
	private Stack<TarjanNode> stack;
	private TarjanGraph trGraph;
	private int time;
	
	private ArrayList<TarjanNode> lscc;
	private ArrayList<TarjanNode> currentScc;
	private int count;
	
	public Tarjan(){
		stack = new Stack<TarjanNode>();
		time = 0;
		lscc = new ArrayList<TarjanNode>();
		currentScc = new ArrayList<TarjanNode>();
		count = 0;
	}
	
	public Tarjan(TarjanGraph trGraph){
		this();
		this.trGraph = trGraph;
	}
	
	public void getGraphSCCList() {
		for(Map.Entry<Integer, TarjanNode> tarjanNode : trGraph.gethMap().entrySet()){
			count++;
			TarjanNode trNode = tarjanNode.getValue();
			if(!trNode.isVisited()){
				getNodeSCC(trNode);
			}
		}
		int size = lscc.size();
		int i = 0;
		for(i = 0; i < size; i++){
			lscc.get(i).setInSCC(true);
		}
		currentScc.clear();
	}
	
	public void getNodeSCC(TarjanNode parentTrNode) {		
		parentTrNode.setVisited(true);
		parentTrNode.setNodeIndex(++time);
		parentTrNode.setLowestLink(time);
		parentTrNode.setOnStack(true);
		stack.push(parentTrNode);
		TarjanNode childTrNode;
		int childrenSize = parentTrNode.getNeighbourSize();
		
		for(int i = 0; i < childrenSize; i++){
			childTrNode = trGraph.gethMap().get(parentTrNode.getAdjList().get(i));
			if(!childTrNode.isVisited()){
				getNodeSCC(childTrNode);
				if(childTrNode.getLowestLink() < parentTrNode.getLowestLink())
					parentTrNode.setLowestLink(childTrNode.getLowestLink());//minimize
			}else{
				if(childTrNode.isOnStack()){
					if(childTrNode.getNodeIndex() < parentTrNode.getLowestLink())
						parentTrNode.setLowestLink(childTrNode.getNodeIndex());//minimize
				}
			}
		}
			
		if(parentTrNode.getLowestLink() == parentTrNode.getNodeIndex()){
			TarjanNode w = new TarjanNode();
			currentScc = new ArrayList<TarjanNode>();
			count = 0;
			do{
				count++;
				w = stack.pop();
				w.setOnStack(false);
				currentScc.add(w);
			}while(!w.equals(parentTrNode));
			if(count > lscc.size()){
				lscc = currentScc;
			}
		}   
	}
	
	
	public TarjanGraph getGraph() {
		return trGraph;
	}

	public void setGraph(TarjanGraph trGraph) {
		this.trGraph = trGraph;
	}
	
	public ArrayList<TarjanNode> getLscc() {
		return lscc;
	}

	public void setLscc(ArrayList<TarjanNode> lscc) {
		this.lscc = lscc;
	}
}

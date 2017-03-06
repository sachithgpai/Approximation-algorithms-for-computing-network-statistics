package graph.algorithms.approx;

import java.util.*;
import entitiy.*;
import graph.algorithms.BFS;
import utility.AppConfiguration;

public class RandomNodeApproximation extends ApproximateScheme{
	
	private int sampleSize;
	private int samplePercentage;
	private int graphSize;

	public RandomNodeApproximation() {
		
	}
	
	public RandomNodeApproximation(Graph graph, int samplePercentage) {
		this.setGraph(graph);
		this.graphSize = this.getGraph().gethMap().size();
		this.samplePercentage = samplePercentage;
		this.sampleSize =  Math.round(((float)this.samplePercentage/100*graphSize));
	}
	
	@Override
	public void calculateNetworkStatistics() {
		float raondomProb = 0;
		float uniforProb = 0;
		int count = 0;
		HashMap<Integer, Node> hMap = this.getGraph().gethMap();	
		while(count < sampleSize){
//			System.out.println("Round # " + count);
			graphSize = graphSize - count;
			for (Node node : hMap.values()){
				raondomProb = AppConfiguration.RN.nextFloat();
				uniforProb = (float)1/samplePercentage;
				if(!node.isSelected() &&  count < sampleSize &&  raondomProb < uniforProb){
					node.setSelected(true);
					count++;
				}
				graphSize--;
			}
		}
//		System.out.println("--- " + count + " **** " + sampleSize);
//		System.out.println("before graph size : " + hMap.size());
		
		Iterator it = hMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Integer, Node> pair = (Map.Entry<Integer, Node>)it.next();
	        if(!((Node)pair.getValue()).isSelected())
	        	it.remove(); // avoids a ConcurrentModificationException
	    }
		
//		System.out.println("after graph size : " + hMap.size());
		for (Node node : hMap.values()){
			ArrayList<Integer> neighbourList = node.getAdjList();
			ArrayList<Integer> newNeighbourList = new ArrayList<Integer>();
			int listSize = neighbourList.size();
			for(int i = 0; i < listSize; i++){
				if(hMap.containsKey(neighbourList.get(i))){
					newNeighbourList.add(neighbourList.get(i));
				}
			}
			node.setAdjList(newNeighbourList);
		}
		
		
//		System.out.println("ff  " + this.getGraph().gethMap().size());
		
		BFS bfs = new BFS();
		bfs.calcShortestPairDistances(this.getGraph());
		for(Map.Entry<Integer, Long> item: bfs.getDistanceMap().entrySet()){
//			BWG.write(item.getKey() + "	" + item.getValue() +"\n");
			System.out.println(item.getKey() + "	" + item.getValue() +"\n");
		}
		
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public int getSamplePercentage() {
		return samplePercentage;
	}

	public void setSamplePercentage(int samplePercentage) {
		this.samplePercentage = samplePercentage;
	}
	
}

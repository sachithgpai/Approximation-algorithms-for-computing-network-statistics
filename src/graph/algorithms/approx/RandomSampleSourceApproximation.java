package graph.algorithms.approx;

import java.util.*;
import entitiy.*;
import graph.algorithms.BFS;
import utility.AppConfiguration;
import utility.Utility;

public class RandomSampleSourceApproximation extends ApproximateScheme{
	
	private int numberOfSamples;
	private int graphSize;

	public RandomSampleSourceApproximation() {
		
	}
	
	public RandomSampleSourceApproximation(Graph graph, int samplePercentage) {
		this.setGraph(graph);
		this.graphSize = this.getGraph().gethMap().size();
		this.numberOfSamples = samplePercentage;
	}
	
	@Override
	public void calculateNetworkStatistics() {
//		float raondomProb = 0;
//		float uniforProb = 0;
		int count = 0;
//		HashMap<Integer, Node> hMap = this.getGraph().gethMap();	
		while(count < numberOfSamples){
			int id = AppConfiguration.RN.nextInt(this.graphSize);
			int iterator = 0;
			for (Node s : this.getGraph().gethMap().values()){
				if(iterator == id){
					s.setSelected(true);
				}
				iterator++;
			}
			
			
			
//			System.out.println("Round # " + count);
//			graphSize = graphSize - count;
//			for (Node node : hMap.values()){
//				raondomProb = AppConfiguration.RN.nextFloat();
//				uniforProb = (float)1/this.getGraph().gethMap().size();
//				if(!node.isSelected() &&  count < numberOfSamples &&  raondomProb < uniforProb){
//					node.setSelected(true);
					count++;
//				}
//				graphSize--;
//			}
		}	
				
//		System.out.println("ff  " + this.getGraph().gethMap().size());
		
		BFS bfs = new BFS();
		bfs.calcDistanceUsingSampleSource(this.getGraph());
		Utility.calculateGraphStatistics(bfs.getDistanceMap());
//		for(Map.Entry<Integer, Long> item: bfs.getDistanceMap().entrySet()){
//			BWG.write(item.getKey() + "	" + item.getValue() +"\n");
//			System.out.println(item.getKey() + "	" + item.getValue() +"\n");
//		}
		
	}

		
}

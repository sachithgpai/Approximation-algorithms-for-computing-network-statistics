package graph.algorithms.approx;

import java.util.*;
import entitiy.*;
import graph.algorithms.BFS;
import utility.AppConfiguration;
import utility.Utility;

public class RandomSamplePairApproximation extends ApproximateScheme{
	
	private int numberOfSamples;
	private int graphSize;
	private ArrayList<RandomPair> randomPairList;

	public RandomSamplePairApproximation() {
		
	}
	
	public RandomSamplePairApproximation(Graph graph, int samplePercentage) {
		this.setGraph(graph);
		this.graphSize = this.getGraph().gethMap().size();
		this.numberOfSamples = samplePercentage;
		randomPairList = new ArrayList<RandomPair>();
	}
	
	@Override
	public void calculateNetworkStatistics() {
//		float raondomProb = 0;
//		float uniforProb = 0;
		int count = 0;
//		HashMap<Integer, Node> hMap = this.getGraph().gethMap();	
		while(count < numberOfSamples){
//			System.out.println("HI------------------->>>"+count);
//			raondomProb = AppConfiguration.RN.nextFloat();
//			uniforProb = (float)1/(this.getGraph().gethMap().size() );
			RandomPair rp = new RandomPair();
			rp.setFromId(AppConfiguration.RN.nextInt(this.graphSize));
			rp.setToId(AppConfiguration.RN.nextInt(this.graphSize));
//			if(count < numberOfSamples &&  raondomProb < uniforProb){
				randomPairList.add(rp);
				count++;
//			}
		}
//		System.out.println("End---------------------->>>");
//		for(int i = 0; i < randomPairList.size(); i++){
//			RandomPair rp = randomPairList.get(i);
//			System.out.println("from: " + rp.getFromId() + " to: " + rp.getToId());
//			
//		}
				
		BFS bfs = new BFS();
		bfs.calcDistanceUsingSamplePair(this.getGraph(), randomPairList);
		Utility.calculateGraphStatistics(bfs.getDistanceMap());
//		for(Map.Entry<Integer, Long> item: bfs.getDistanceMap().entrySet()){
//////			BWG.write(item.getKey() + "	" + item.getValue() +"\n");
//			System.out.println(item.getKey() + "	" + item.getValue() +"\n");
//		}
		
	}
	
}

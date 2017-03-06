package graph.algorithms.approx;

import java.util.HashMap;

import entitiy.Graph;
import graph.algorithms.FlajoletMarten;
import utility.AppConfiguration;
import utility.Utility;

public class FlajoletMartenApproximation extends ApproximateScheme{
	
	private int numOfRepetition;
	private int graphSize;
	
	public FlajoletMartenApproximation(){
		
	}
	
	public FlajoletMartenApproximation(Graph graph, int numOfRepetition) {
		this.setGraph(graph);
		this.graphSize = this.getGraph().gethMap().size();
		this.numOfRepetition = numOfRepetition;
	}
	

	@Override
	public void calculateNetworkStatistics() {
		FlajoletMarten fM = new FlajoletMarten(this.getGraph().gethMap());
		fM.flajoletMarten();
		HashMap<Integer, Long> distanceMap = new HashMap<Integer, Long>();
		for(int j = 0; j < AppConfiguration.T_TIME; j++){
			long value = (long)FlajoletMarten.distanceMatrix[AppConfiguration.NUMBER_OF_HASH-1][j]/(AppConfiguration.NUMBER_OF_HASH-1);
//			System.out.println("ffff "+FlajoletMarten.distanceMatrix[AppConfiguration.NUMBER_OF_HASH-1][j] + "   " + value);
			if(value > 0)
				distanceMap.put(j, value);
		}
		Utility.calculateGraphStatistics(distanceMap);
	}

	public int getNumOfRepetition() {
		return numOfRepetition;
	}

	public void setNumOfRepetition(int numOfRepetition) {
		this.numOfRepetition = numOfRepetition;
	}
	
	

}

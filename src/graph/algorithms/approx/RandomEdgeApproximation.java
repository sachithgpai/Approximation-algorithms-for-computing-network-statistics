package graph.algorithms.approx;

import entitiy.Graph;

public class RandomEdgeApproximation extends ApproximateScheme{
	
	private int sampleSize;
	private int samplePercentage;
	private int graphSize;

	public RandomEdgeApproximation() {
	}
	
	public RandomEdgeApproximation(Graph graph, int samplePercentage) {
		this.setGraph(graph);
		this.graphSize = this.getGraph().gethMap().size();
		this.samplePercentage = samplePercentage;
		this.sampleSize = this.samplePercentage/100 * graphSize;
	}
	
	@Override
	public void calculateNetworkStatistics() {
		// TODO Auto-generated method stub
		
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

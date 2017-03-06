package graph.algorithms.approx;

import entitiy.*;

public abstract class ApproximateScheme {
	
	private Graph graph;
	
	public abstract void calculateNetworkStatistics();

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
}

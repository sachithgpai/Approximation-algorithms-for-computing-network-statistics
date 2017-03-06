package graph.algorithms.approx;

import entitiy.Graph;

public class ApproximationFactory {
	
	public ApproximationFactory(){
		
	}
	
	public static ApproximateScheme getApproxSchem(int id, Graph graph, int accuracyParameter){
		ApproximateScheme approxSchem = null;
		if(id == 1){
			approxSchem = new RandomSampleSourceApproximation(graph, accuracyParameter);
		}else if (id == 2){
			approxSchem = new RandomSamplePairApproximation(graph, accuracyParameter);
		}else if (id == 3){
			approxSchem = new FlajoletMartenApproximation(graph, accuracyParameter);
		}else if (id == 4){
			approxSchem = new RandomNodeApproximation(graph, accuracyParameter);
		}else if (id == 5){
			approxSchem = new RandomEdgeApproximation(graph, accuracyParameter);
		}
		return approxSchem;
	}

}

package graph.algorithms;

import java.util.*;
import entitiy.Node;
import utility.AppConfiguration;

public class FlajoletMarten {

	public static final long[][] distanceMatrix = new long[AppConfiguration.NUMBER_OF_HASH][AppConfiguration.T_TIME];	
	private HashMap<Integer, Node> hMap;
	
	int old = 0;
//	int[] tt = new int [11];
	 
	
	public FlajoletMarten(){
		hMap = new HashMap<Integer, Node>();
	}
	
	public FlajoletMarten(HashMap<Integer, Node> hMap){
		this.hMap = hMap;
	}
	
	public void getIndividualNeighbourhood(int hashIteration){
		long neighbourCount = 0;
		long neighbourApproxAtTimeT = 0;
		long oldApproximation = 0;
		//do for # of t times (t represents distance '1,2,3,4,....etc')
		for(int t = 1; t < AppConfiguration.T_TIME; t++){
			oldApproximation = neighbourApproxAtTimeT;
			neighbourApproxAtTimeT = 0;
			//start for each node
			for (Node node : hMap.values()){
				ArrayList<Integer> neighbourList = node.getAdjList();
				int size = neighbourList.size();
				node.setBitHashAtTimeT(node.getBitHashAtTimeTMinusOne());
				for(int j = 0; j < size; j++){
					node.setBitHashAtTimeT( node.getBitHashAtTimeT() | hMap.get(neighbourList.get(j)).getBitHashAtTimeTMinusOne() );
				}
				neighbourCount = getMostRightZeroBit(node.getBitHashAtTimeT());
				neighbourApproxAtTimeT += Math.pow(2, neighbourCount);
			}
			distanceMatrix[hashIteration][t] = neighbourApproxAtTimeT-oldApproximation;
			moveOneStep();
		}
	}
	
	public void flajoletMarten(){
		for(int i = 0; i < AppConfiguration.NUMBER_OF_HASH-1; i++){
//			System.out.println("iteration # " + i);
//			distanceMatrix[i][0] = hMap.size();
			getIndividualNeighbourhood(i);
			for (Node node : hMap.values()){
				node.setBitHashAtTimeT(0);
				node.setBitHashAtTimeTMinusOne(0);
				node.populateBitHashStr();
			}
		}
		
		for(int i = 0; i < AppConfiguration.NUMBER_OF_HASH-1; i++){
			for(int j = 0; j < AppConfiguration.T_TIME; j++){
				distanceMatrix[AppConfiguration.NUMBER_OF_HASH-1][j] += distanceMatrix[i][j];
			}
		}
	}
	
	
	public void moveOneStep(){
		for (Node s : hMap.values()){
			s.setBitHashAtTimeTMinusOne(s.getBitHashAtTimeT());
			s.setBitHashAtTimeT(0);
		}
	}
	
	public int getMostRightZeroBit(long number){
		int count = 0;
		while(number > 0){
			if((number & 1) == 0)
				return count;
			else{
				count++;
				number = number >> 1;
			}
		}
		return count;		
	}

	public HashMap<Integer, Node> gethMap() {
		return hMap;
	}

	public void sethMap(HashMap<Integer, Node> hMap) {
		this.hMap = hMap;
	}
	
}

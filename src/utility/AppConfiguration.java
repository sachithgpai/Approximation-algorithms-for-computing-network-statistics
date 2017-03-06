package utility;

//import java.io.BufferedWriter;
import java.util.Random;

import entitiy.Graph;
import entitiy.TarjanGraph;

public class AppConfiguration {
	
	public static Random RN = new Random(System.currentTimeMillis()); 
	
	public static final int BIT_HASH_SIZE = 32;// bitHashSize = 32;
	public static final int NUMBER_OF_HASH = 101; //numberOfHash = 101; // 100 actual and 1 for the average calculation
	public static final int T_TIME = 16; //tTime = 16;
	
	public static String PATH;
	public static String FILE_NAME;
	public static int IGNORE_LINES;
	
	public static String CONNECTED_GRAPH_TYPE; 					//{"LSCC" or "LWCC"}
	public static final String LSCC = "LSCC";
	public static final String LWCC = "LWCC";
	
	
	public static String CALCULATION_TYPE; 						//{"EXACT" or "APPROX"}
	public static final String APPROX = "APPROX";
	public static final String EXACT = "EXACT";
	
	public static String APPROXIMATION_SCHEM;					//{"R_SRC" or "R_PAIR" or "F_M" or "R_N" or "R_E"}
	public static final String RANDOM_SOURCE = "R_SRC";
	public static final String RANDOM_PAIR = "R_PAIR";
	public static final String FLAJOLET_MARTEN = "F_M";
	public static final String RANDOM_NODE = "R_N";
	public static final String RANDOM_EDGE = "R_E";
	
	public static int ACCURACY_PARAMETER;
	
//	public static String ANALYSIS_FILE_NAME;
//	public static BufferedWriter BWG;
	public static String LCC_FILE_NAME;	
	public static String SYSTEM_CHARACTER_SEPARATOR;
	public static TarjanGraph TARJAN_GRAPH = new TarjanGraph();	//it is a directed graph
	public static Graph DIRECTED_GRAPH = new Graph();
	public static Graph UNDIRECTED_GRAPH = new Graph();
	
	public static final String ERROR_MSG_1 = "Sorry you have to enter 4 or 5 parameters in this order [FileName, #IgnoredLines, LSCC or LWCC, EXACT or APPROX, APPROX_SCHEM, ACCURACY_PARAMETER]";
	public static final String ERROR_MSG_2 = "Wrong Approximation scheme, please use [R_SRC or R_PAIR or F_M]";
	
	public static double MEDIAN = -1;
	public static double MEAN = -1;
	public static long DIAMETER = -1;
	public static long EFFECTIVE_DIAMETER = -1;
	
	public static final int THRESHOLD = 1000000;
}
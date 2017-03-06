package main;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import graph.algorithms.*;
import graph.algorithms.approx.*;
import utility.*;

public class Runner {

	// This function is used to parse the parameters
	public static void init(String[] str) throws Exception {
		if (str.length != 4 && str.length != 6)
			throw new Exception(AppConfiguration.ERROR_MSG_1);
		Path currentRelativePath = Paths.get("");
		AppConfiguration.PATH = currentRelativePath.toAbsolutePath().toString();
		AppConfiguration.FILE_NAME = str[0];
		AppConfiguration.IGNORE_LINES = Integer.parseInt(str[1]);
		AppConfiguration.CONNECTED_GRAPH_TYPE = str[2];
		AppConfiguration.CALCULATION_TYPE = str[3];

		if (str.length == 6 && AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.APPROX)) {
			AppConfiguration.APPROXIMATION_SCHEM = str[4];
			AppConfiguration.ACCURACY_PARAMETER = Integer.parseInt(str[5]);
		} else if (str.length == 6
				&& !AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.APPROX)) {
			System.out.println(AppConfiguration.ERROR_MSG_2);
		}

		AppConfiguration.SYSTEM_CHARACTER_SEPARATOR = File.separator;
		// Assuming the file name will be ended with "."+ 3 characters ex:
		// ".txt"
//		AppConfiguration.ANALYSIS_FILE_NAME = AppConfiguration.FILE_NAME.substring(0,
//				AppConfiguration.FILE_NAME.length() - 4) + "_ANALYSIS.txt";
//		File file = new File(AppConfiguration.PATH + AppConfiguration.SYSTEM_CHARACTER_SEPARATOR
//				+ AppConfiguration.ANALYSIS_FILE_NAME);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		AppConfiguration.BWG = new BufferedWriter(fw);
//		AppConfiguration.BWG.write("Graph File Name: " + AppConfiguration.FILE_NAME);
//		AppConfiguration.BWG.write("\nAnalysis File Name: " + AppConfiguration.ANALYSIS_FILE_NAME);
//		AppConfiguration.BWG.write("\n@" + new Date() + " : Start Processing");
		if (AppConfiguration.CONNECTED_GRAPH_TYPE.toUpperCase().equals("LSCC"))
			AppConfiguration.LCC_FILE_NAME = AppConfiguration.FILE_NAME.substring(0,
					AppConfiguration.FILE_NAME.length() - 4) + "_LSCC.txt";
		if (AppConfiguration.CONNECTED_GRAPH_TYPE.toUpperCase().equals("LWCC"))
			AppConfiguration.LCC_FILE_NAME = AppConfiguration.FILE_NAME.substring(0,
					AppConfiguration.FILE_NAME.length() - 4) + "_LWCC.txt";
	}

	public static void main(String[] str) {
		try {
			System.out.println("@ [" + new Date() + "] START PROGRAM..");
			init(str);
			System.out.println("@ [" + new Date() + "] PARAM LOADED..");
			if (AppConfiguration.CONNECTED_GRAPH_TYPE.toUpperCase().equals(AppConfiguration.LSCC)) {
				System.out.println("@ [" + new Date() + "] START LSCC..");
				Utility.loadTarjanDirectedGraph();
				Utility.saveLSCC();
				System.out.println("@ [" + new Date() + "] File " + AppConfiguration.LCC_FILE_NAME + " SAVED..");
				System.out.println("@ [" + new Date() + "] END LSCC..");
				AppConfiguration.TARJAN_GRAPH.gethMap().clear();
				Utility.loadLCC("Directed");
				if (AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.EXACT)) {
					System.out.println("@ [" + new Date() + "] START CALCULATING DISTANCES FOR LSCC");
					BFS bfs = new BFS();
					bfs.calcShortestPairDistances(AppConfiguration.DIRECTED_GRAPH);
					System.out.println("@ [" + new Date() + "] END CALCULATING DISTANCES FOR LSCC");
					Utility.calculateGraphStatistics(bfs.getDistanceMap());
				} else if (AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.APPROX)) {
					ApproximateScheme appSchem = null;
					if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase().equals(AppConfiguration.RANDOM_SOURCE)) {
						appSchem = ApproximationFactory.getApproxSchem(1, AppConfiguration.DIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					} else if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase()
							.equals(AppConfiguration.RANDOM_PAIR)) {
						appSchem = ApproximationFactory.getApproxSchem(2, AppConfiguration.DIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					} else if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase()
							.equals(AppConfiguration.FLAJOLET_MARTEN)) {
						appSchem = ApproximationFactory.getApproxSchem(3, AppConfiguration.DIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					}
					appSchem.calculateNetworkStatistics();
				}
			} else if (AppConfiguration.CONNECTED_GRAPH_TYPE.toUpperCase().equals(AppConfiguration.LWCC)) {
				System.out.println("@ [" + new Date() + "] START LWCC..");
				Utility.loadUndirectedGraph();
				Utility.saveLWCC();
				System.out.println("@ [" + new Date() + "] File " + AppConfiguration.LCC_FILE_NAME + " SAVED..");
				System.out.println("@ [" + new Date() + "] END LWCC..");
				AppConfiguration.UNDIRECTED_GRAPH.gethMap().clear();
				Utility.loadLCC("Undirected");
				if (AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.EXACT)) {
					System.out.println("@ [" + new Date() + "] START CALCULATING DISTANCES FOR LWCC");
					BFS bfs = new BFS();
					bfs.calcShortestPairDistances(AppConfiguration.UNDIRECTED_GRAPH);
					System.out.println("@ [" + new Date() + "] END CALCULATING DISTANCES FOR LWCC");
					Utility.calculateGraphStatistics(bfs.getDistanceMap());
				} else if (AppConfiguration.CALCULATION_TYPE.toUpperCase().equals(AppConfiguration.APPROX)) {
					ApproximateScheme appSchem = null;
					if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase().equals(AppConfiguration.RANDOM_SOURCE)) {
						appSchem = ApproximationFactory.getApproxSchem(1, AppConfiguration.UNDIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					} else if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase()
							.equals(AppConfiguration.RANDOM_PAIR)) {
						appSchem = ApproximationFactory.getApproxSchem(2, AppConfiguration.UNDIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					} else if (AppConfiguration.APPROXIMATION_SCHEM.toUpperCase()
							.equals(AppConfiguration.FLAJOLET_MARTEN)) {
						appSchem = ApproximationFactory.getApproxSchem(3, AppConfiguration.UNDIRECTED_GRAPH, AppConfiguration.ACCURACY_PARAMETER);
					}
					appSchem.calculateNetworkStatistics();
				}
			} else {
				System.out
						.println("Not defined type [Not LSCC nor LWCC] but, " + AppConfiguration.CONNECTED_GRAPH_TYPE);
				System.out.println("Please re-run the program with valid parameters [Either LSCC or LWCC]");
				return;
			}
//			AppConfiguration.BWG.write("\n@" + new Date() + " : End Processing ");
//			AppConfiguration.BWG.close();
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			System.out.println("Ex1: Wiki-Vote.txt 4 LSCC EXACT");
			System.out.println("Ex2: Wiki-Vote.txt 0 LWCC APPROX R_PAIR");
			System.out.println("Ex3: Wiki-Vote.txt 4 LSCC APPROX F_M");
		}
		System.out.println("@ [" + new Date() + "] END PROGRAM..");
	}
}

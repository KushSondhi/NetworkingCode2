package com.edu.depaul;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		Set<String> alldocuments = new HashSet<String>();
		String universalDataset = "";

		try {
			while (true) {
				System.out.println("\n\nChoose following Options to perform task : ");
				System.out.println("1. File Indexing");
				System.out.println("2. Perform Searching in a Specific dataset");
				System.out.println("3. Quit");

				int choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1:

					System.out.print("Enter No. of Threads to perform MultiThreading : ");
					int threads = Integer.parseInt(sc.nextLine());

					System.out.println("In which DataSet you want to perform Indexing ? ");
//					System.out.println("Enter like this : Dataset1 / Dataset2--...");
					String nameOfdataSet = sc.nextLine().toString();
					universalDataset = nameOfdataSet;

					IndexStore indexdocuments = new IndexStore();

					alldocuments = indexdocuments.listAlldocumentsAnddataSets("src//main//resources//" , nameOfdataSet, threads);

					nameOfdataSet = null;
					break;

				case 2:
					System.out.print("Enter item to Search : ");
					String input = sc.nextLine();
					input = input.substring(7);
					System.out.println(input);

					System.out.println("In which DataSet you want to search this input ? ");
					String datasetname = sc.nextLine().toString();
//					String datasetname = universalDataset;

					ArrayList<String> validDataSets = new ArrayList<String>();

					String pathOfResources = "src//main//resources//";

					File folder = new File(pathOfResources);
					for (File f : folder.listFiles()) {
						validDataSets.add(f.getName().toString());
					}

					if (!validDataSets.contains(datasetname)) {
						System.out.println("Dataset value Incorrect...");
						break;
					}

					RetrievDataEngine engine = new RetrievDataEngine();
					Map<String, Set<String>> resultantMap = new HashMap<String, Set<String>>();
					resultantMap = engine.generateDocWordPair(pathOfResources, datasetname);

//					System.out.println(resultantMap);
//					resultantMap.clear();

					FileRetrievalEngine fileEngine = new FileRetrievalEngine();
					
					if (input.contains(" OR ")) {
						System.out.println("OR method");
						fileEngine.alphaNumericStringWithOR(input, resultantMap);
						
					} else if (input.contains(" AND ")) {
						
						fileEngine.alphaNumericStringWithAND(input, resultantMap);
						
					} else {
//						ThreadPoolFiles indexThreading = new ThreadPoolFiles();
//						indexThreading.MultiThreadingSearching(resultantMap, 4, input);
						fileEngine.alphaNumericString(input, resultantMap);
					}
					resultantMap = null;
					break;

				case 3:
					System.out.println("Exiting the program.");
					sc.close();
					System.exit(0);
				default:
					System.out.println("Invalid option. Please select a valid option.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

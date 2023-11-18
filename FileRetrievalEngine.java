package com.edu.depaul;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileRetrievalEngine {

	public void alphaNumericString(String input, Map<String, Set<String>> mapSet) {

		Instant startTime = Instant.now();
		ArrayList<String> resultantArray = new ArrayList<String>();

		for (Map.Entry<String, Set<String>> mapEntry : mapSet.entrySet()) {
			if (mapEntry.getValue().contains(input)) {
				resultantArray.add(mapEntry.getKey());
			}
		}
		Instant endTime = Instant.now();
		long time = Duration.between(startTime, endTime).toMillis();
		System.out.print("Search completed in " + time + " ms. ");
		System.out.println("Search results: " + resultantArray);
		startTime = endTime = null;
//		return resultantArray;
	}

	public Set<String> alphaNumericStringWithAND(String input, Map<String, Set<String>> mapSet) {

		Instant startTime = Instant.now();
		Set<String> resultantSet = new HashSet<String>();

		ArrayList<String> inputItems = new ArrayList<String>();
		for (String word : input.split(" ")) {
			if (!word.equals("AND")) {
				inputItems.add(word);
//				System.out.println(word);
			}
		}

		for (Map.Entry<String, Set<String>> mapEntry : mapSet.entrySet()) {
			if (mapEntry.getValue().containsAll(inputItems)) {
				resultantSet.add(mapEntry.getKey());
			}
		}

		Instant endTime = Instant.now();
		long time = Duration.between(startTime, endTime).toMillis();
		System.out.print("Search completed in : " + time + " ms. ");
		System.out.println(resultantSet);
		startTime = endTime = null;
		return resultantSet;
	}

	public Set<String> alphaNumericStringWithOR(String input, Map<String, Set<String>> mapSet) {

		Instant startTime = Instant.now();
		Set<String> resultantSet = new HashSet<String>();

		ArrayList<String> inputItems = new ArrayList<String>();
		for (String word : input.split(" ")) {
			if (!word.equals("OR")) {
				inputItems.add(word);
				System.out.println(word);
			}
		}

		for (String word : inputItems) {
			for (Map.Entry<String, Set<String>> mapEntry : mapSet.entrySet()) {
				if (mapEntry.getValue().contains(word)) {
					resultantSet.add(mapEntry.getKey());
				}
			}
		}

		Instant endTime = Instant.now();
		long time = Duration.between(startTime, endTime).toMillis();
		System.out.print("Search completed in " + time + " ms. ");
		System.out.println(resultantSet);
		startTime = endTime = null;
		return resultantSet;
	}
}

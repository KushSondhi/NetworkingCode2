package com.edu.depaul;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreadPoolFiles {

	public void MultiThreadingSearching(Map<String, Set<String>> mapSet, int numOfThreads, String input) {

		Instant startTime = Instant.now();

		int n = mapSet.size();
		int chunkSize = n / numOfThreads;
//		System.out.println("Chunk Size : " + chunkSize);

		List<Thread> threads = new ArrayList<>();
		ArrayList<String> resultantArray = new ArrayList<String>();

		// Create threads & search through different chunks of the Map
		for (int i = 0; i < numOfThreads; i++) {

			int temp = i;
			Thread t = new Thread(() -> {
				int start = temp * chunkSize;
				int end = (temp == numOfThreads - 1) ? n : (temp + 1) * chunkSize;

				for (int j = start; j < end; j++) {
					Object firstKey = mapSet.keySet().toArray()[j];

					if (mapSet.get(firstKey).contains(input)) {
						resultantArray.add(firstKey.toString());
					}
					firstKey = null;
				}

			});
			threads.add(t);
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
//				return (int) Thread.currentThread().getId() * chunkSize;
			}
		}

		Instant endTime = Instant.now();
		long time = Duration.between(startTime, endTime).toMillis();
		System.out.print("Search completed in " + time+ " ms. ");
		System.out.println(resultantArray);
	}

}

package com.edu.depaul;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Class to Store documents present in all DataSets using MultiThreading
public class IndexStore {

	public Set<String> listAlldocumentsAnddataSets(String pathOfResources, String nameOfdataset, int noOfThreads) {

		Instant startTime = Instant.now();

		File dataSetfolder = new File(pathOfResources + nameOfdataset);
		File[] alldocuments = dataSetfolder.listFiles();

		int n = alldocuments.length;
//		System.out.println("Total no. of documents : " + n);
		int chunkSize = n / noOfThreads;

		List<Thread> threads = new ArrayList<>();
		Set<String> documents = new HashSet<String>();

		for (int i = 0; i < noOfThreads; i++) {
			int temp = i;
			Thread t = new Thread(() -> {
				int start = temp * chunkSize;
				int end = (temp == noOfThreads - 1) ? n : (temp + 1) * chunkSize;

				for (int j = start; j < end; j++) {
					documents.add(alldocuments[j].getAbsolutePath().toString());
//					System.out.println(alldocuments[j]);
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
			}
		}

		Instant end = Instant.now();
		long time = Duration.between(startTime, end).toMillis();
		System.out.println("Completed Indexing " + nameOfdataset + " in " + time + " ms");
		startTime = end = null;
		return (documents);

	}

}

package com.edu.depaul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RetrievDataEngine {

	public Map<String, Set<String>> generateDocWordPair(String pathofResources, String dataSet) {

		return IterateOverdataSetdocuments(pathofResources + dataSet);
	}

	public Map<String, Set<String>> IterateOverdataSetdocuments(String pathOfDataSet) {

		Map<String, Set<String>> documentWordPair = new HashMap<String, Set<String>>();
		File dataSetfolder = new File(pathOfDataSet);
		File[] documentsindataSet = dataSetfolder.listFiles();

		for (int j = 0; j < documentsindataSet.length; j++) {
			if (documentsindataSet[j].isFile()) {
				documentWordPair.put(documentsindataSet[j].getAbsolutePath(),
						generateSetOfUniqueWords(documentsindataSet[j].getAbsolutePath().toString()));
			}
		}
		return documentWordPair;
	}

	public Set<String> generateSetOfUniqueWords(String pathofdoc) {

		Set<String> setKeyWords = new HashSet<String>();
		try (BufferedReader buffer = new BufferedReader(new FileReader(pathofdoc))) {

			String str;
			while ((str = buffer.readLine()) != null) {
				str = str.toString().replaceAll("[^A-Za-z0-9@:.]", " ").replaceAll("\\*", "").replace("\n", "");
				for (String s : str.split(" ")) {
					setKeyWords.add(s.replaceAll("[^A-Za-z0-9@]", ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return setKeyWords;
	}

}

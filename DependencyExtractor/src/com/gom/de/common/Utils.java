package com.gom.de.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}

	public static String extractPackageName(String line) {
		String fullName = line.split(" ")[1].replace(";", "").toLowerCase();

		int count = fullName.length() - fullName.replace(".", "").length();

		if (count < 3)
			return fullName;

		int rIdx = 0;

		for (int i = 0; i < 3; ++i) {
			rIdx = fullName.indexOf('.', rIdx);
			if (rIdx < fullName.length())
				rIdx++;
			else
				break;
		}

		return fullName.substring(0, rIdx - 1);
	}

	public static int getLineCount(File f) {
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			int lineCount = 0;
			while (br.readLine() != null)
				lineCount++;
			return lineCount;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}

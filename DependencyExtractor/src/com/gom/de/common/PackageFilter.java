package com.gom.de.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageFilter {
	public List<String> stlList;
	
	public PackageFilter()
	{
		stlList = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(PathUtils.getDataPath(), "stl.txt")))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	if(line.startsWith("java") || line.startsWith("org")){
		    		stlList.add(line.toLowerCase().trim());
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean invalidationPackage(String packageName)
	{
		if(packageName.isEmpty()) return false;
		if(packageName.equals("static")) return false;
		
		for(String stl : stlList)
		{
			if(packageName.startsWith(stl)) return false;
		}
		
		return true;
	}
}

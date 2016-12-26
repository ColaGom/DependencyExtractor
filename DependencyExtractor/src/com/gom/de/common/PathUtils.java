package com.gom.de.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
	
	public static final String PATH_ROOT = "C:/GitRepo";
	private static final String DIR_RESULT = "#Result";
	private static final String DIR_DATA = "#Data";
	private static final String DIR_DEPENDENCY = "#Dependency";
	
	public static String getProjectPath(String projectName)
	{
		return createPathInRoot(projectName);
	}
	
	public static String getDependencyPath()
	{
		return createPathInRoot(DIR_DEPENDENCY);
	}
	
	public static String getResultPath(String fileName)
	{
		Path path = Paths.get(createPathInRoot(DIR_RESULT), fileName);
		return path.toString();
	}
	
	public static String getResultPath()
	{
		return createPathInRoot(DIR_RESULT);
	}
	
	public static String getDataPath()
	{
		return createPathInRoot(DIR_DATA);
	}
	
	private static String createPathInRoot(String subDir)
	{
		Path path = Paths.get(PATH_ROOT, subDir);
		File file = path.toFile();
		
		if(!file.exists()) file.mkdirs();
		
		return path.toString();
	}
}

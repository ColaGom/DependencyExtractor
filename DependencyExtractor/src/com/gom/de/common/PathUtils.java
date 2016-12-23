package com.gom.de.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
	
	private static final String PATH_ROOT = "C:/GitRepo";
	private static final String DIR_RESULT = "#Data";
	private static final String DIR_DEPENDENCY = "#Dependency";
	
	public static String getProjectPath(String projectName)
	{
		return createPathInRoot(projectName);
	}
	
	public static String getDependencyPath()
	{
		return createPathInRoot(DIR_DEPENDENCY);
	}
	
	public static String getResultPath()
	{
		return createPathInRoot(DIR_RESULT);
	}
	
	private static String createPathInRoot(String subDir)
	{
		Path path = Paths.get(PATH_ROOT, subDir);
		return path.toString();
	}
}

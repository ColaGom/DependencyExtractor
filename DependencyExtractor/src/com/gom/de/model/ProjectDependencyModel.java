package com.gom.de.model;

import java.util.Map;

public class ProjectDependencyModel {
	
	private int totalLineCount;
	private String projectName;
	private Map<String, DependencyModel> internalMap;
	private Map<String, ExternalDependencyModel> externalMap;
	
	public ProjectDependencyModel(String projectName) {
		this.projectName = projectName;
		totalLineCount = 0;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void increaseTotalLineCount(int lineCount)
	{
		totalLineCount += lineCount;
	}
	
	public int getTotalLineCount() {
		return totalLineCount;
	}
	
	private int getInternalCount()
	{
		return internalMap.size();
	}
	
	private int getExternalCount()
	{
		return externalMap.size();
	}

}

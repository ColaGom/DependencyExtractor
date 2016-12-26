package com.gom.de.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gom.de.common.Utils;

public class ProjectDependencyModel implements Comparable<ProjectDependencyModel>{
	
	private int totalLineCount;
	private String projectName;
	private Map<String, DependencyModel> internalMap;
	private Map<String, ExternalDependencyModel> externalMap;
	
	public ProjectDependencyModel(String projectName) {
		this.projectName = projectName;
		totalLineCount = 0;
		internalMap = new HashMap<>();
		externalMap = new HashMap<>();
	}
	
	public void sort()
	{
		internalMap = Utils.sortByValue(internalMap);
		externalMap = Utils.sortByValue(externalMap);
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void externalFiltering()
	{
		Iterator<String> itKey = externalMap.keySet().iterator();

		while(itKey.hasNext())
		{
			String exKey = itKey.next();
			
			if(internalMap.containsKey(exKey)){
				itKey.remove();
			}
		}
	}
	
	public void addInternalModel(DependencyModel value)
	{
		if(internalMap.containsKey(value.getFullName()))
		{
			DependencyModel internalModel = internalMap.get(value.getFullName());
			internalModel.addLineCount(value.getRelatedLineCount());
			internalModel.increaseCount();
		}
		else
		{
			internalMap.put(value.getFullName(), value);
		}
	}
	
	public void addExternalModel(ExternalDependencyModel value, DependencyModel internal)
	{
		if(externalMap.containsKey(value.getFullName()))
		{
			ExternalDependencyModel externalModel = externalMap.get(value.getFullName());
			externalModel.addLineCount(value.getRelatedLineCount());
			externalModel.increaseCount();
			externalModel.addRelatedList(internal);
		}
		else
		{
			value.addRelatedList(internal);
			externalMap.put(value.getFullName(), value);
		}
	}
	
	public void addTotalLineCount(int lineCount)
	{
		totalLineCount += lineCount;
	}
	
	public int getTotalLineCount() {
		return totalLineCount;
	}
	
	public int getInternalCount()
	{
		return internalMap.size();
	}
	
	public int getExternalCount()
	{
		return externalMap.size();
	}
	
	public Map<String, ExternalDependencyModel> getExternalMap() {
		return externalMap;
	}
	
	public Map<String, DependencyModel> getInternalMap() {
		return internalMap;
	}

	@Override
	public int compareTo(ProjectDependencyModel o) {
		if(getExternalCount() == o.getExternalCount()) return 0;
		else if(getExternalCount() < o.getExternalCount()) return -1;
		else return 1;
	}
}

package com.gom.de.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectDependencyCompareModel implements Comparable<ProjectDependencyCompareModel> {
	
	private List<ExternalDependencyModel> overlapList;
	private String title;
	
	public ProjectDependencyCompareModel(ProjectDependencyModel a, ProjectDependencyModel b) {
		overlapList = new ArrayList<>();
		
		title = a.getProjectName() + " vs " + b.getProjectName();
		
		for(ExternalDependencyModel externalA : a.getExternalMap().values())
		{
			for(ExternalDependencyModel externalB : b.getExternalMap().values())
			{
				if(externalA.getFullName().equals(externalB.getFullName()))
				{
					overlapList.add(externalB);
				}
			}
		}
	}
	
	
	public List<ExternalDependencyModel> getOverlapList() {
		return overlapList;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getOverlapCount()
	{
		return overlapList.size();
	}

	@Override
	public int compareTo(ProjectDependencyCompareModel o) {
		if(getOverlapCount() == o.getOverlapCount()) return 0;
		else if(getOverlapCount() < o.getOverlapCount()) return -1;
		else return 1;
	}
}

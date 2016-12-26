package com.gom.de.model;

import java.util.ArrayList;
import java.util.List;

public class ExternalDependencyModel extends DependencyModel {
	
	private List<String> relatedList;
	
	public ExternalDependencyModel(String fullName) {
		super(fullName);
		relatedList = new ArrayList<>();
	}
	
	public void addRelatedList(DependencyModel parent)
	{
		if(!relatedList.contains(parent.getFullName()))
				relatedList.add(parent.getFullName());
	}
}

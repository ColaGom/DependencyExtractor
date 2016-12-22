package com.gom.de.model;

import java.util.ArrayList;
import java.util.List;

public class ExternalDependencyModel extends DependencyModel {
	
	private List<String> relatedList;
	
	public ExternalDependencyModel() {
		super();
		relatedList = new ArrayList<>();
	}
	
	public void addRelatedList(DependencyModel parent)
	{
		relatedList.add(parent.getFullName());
	}
}

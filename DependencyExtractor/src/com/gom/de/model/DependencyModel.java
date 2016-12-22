package com.gom.de.model;

public class DependencyModel {
	
	private String fullName;
	private int count;
	private int relatedLineCount;
	
	public DependencyModel() {
		count = 0;
		relatedLineCount = 0;
	}
	
	private void increaseLineCount(int lineCount)
	{
		relatedLineCount += lineCount;
	}
	
	private int increaseCount()
	{
		return ++count;
	}
	
	public int getRelatedLineCount() {
		return relatedLineCount;
	}
	
	public int getCount() {
		return count;
	}

	public String getFullName() {
		return fullName;
	}

}

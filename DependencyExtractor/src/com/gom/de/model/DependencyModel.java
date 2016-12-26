package com.gom.de.model;

public class DependencyModel implements Comparable<DependencyModel> {

	private String fullName;
	private int count;
	private int relatedLineCount;

	public DependencyModel(String fullName) {
		count = 1;
		relatedLineCount = 0;
		this.fullName = fullName;
	}

	public void addLineCount(int lineCount) {
		relatedLineCount += lineCount;
	}

	public int increaseCount() {
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

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int compareTo(DependencyModel o) {
		if (count == o.getCount())
			return 0;
		else if (count < o.getCount())
			return -1;
		else
			return 1;
	}
}

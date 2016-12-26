package com.gom.de.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.gom.de.model.ProjectDependencyModel;

public class ResearchSequence {
	
	private List<ProjectDependencyModel> list;
	
	public ResearchSequence(List<ProjectDependencyModel> list)
	{
		this.list = list;
	}
	
	public void testMethod()
	{
		
	}
	
	public void test1()
	{
		//1~100위까지 정렬된 list
	}
	
	private void saveToFile(String str, String fileName)
	{
		try(PrintWriter out = new PrintWriter(new File(PathUtils.getResultPath(), fileName)))
		{
			out.print(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

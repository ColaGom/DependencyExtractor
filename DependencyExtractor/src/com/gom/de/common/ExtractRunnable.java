package com.gom.de.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.gom.de.extractor.IExtractor.CallBack;
import com.gom.de.extractor.ProjectDependencyExtractor;
import com.gom.de.model.ProjectDependencyCompareModel;
import com.gom.de.model.ProjectDependencyModel;
import com.gom.de.model.dao.ProjectDependencyDao;
import com.gom.de.serializer.ProjectDependencyCompareSerializer;
import com.gom.de.serializer.ProjectDependencySerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExtractRunnable implements Runnable, CallBack<ProjectDependencyModel>{
	
	private List<ProjectDependencyModel> dependencyList;
	private PackageFilter filter;
	
	public ExtractRunnable() {
		dependencyList = new ArrayList<>();
		filter = new PackageFilter();
	}
	
	@Override
	public void run() {
		File [] listProjects = new File(PathUtils.PATH_ROOT).listFiles();
		List<String> projects = new ArrayList<>();
		
		for(File dir:listProjects)
		{
			if(dir.isDirectory() && !dir.getName().startsWith("#"))
			{
				projects.add(dir.getName());
			}
		}
		
		//projects.add("infinispan");
		
		ProjectDependencyExtractor extractor = new ProjectDependencyExtractor(filter);
		extractor.setCallback(this);
		
		for(String projectName:projects)
		{
			System.out.println("extract : " + projectName);
			extractor.extract(projectName);
		}
		
		onFinished();
	}
	
	private void onFinished()
	{
		Collections.sort(dependencyList);
		Collections.reverse(dependencyList);
		
//		GsonBuilder gson = new GsonBuilder();
//		gson.registerTypeAdapter(ProjectDependencyModel.class, new ProjectDependencySerializer()).setPrettyPrinting();
//
//		Gson parser = gson.create();
		
		//ProjectDependencyDao dao = new ProjectDependencyDao();
		int current = 0;
		
		for(ProjectDependencyModel model:dependencyList)
		{
			model.externalFiltering();
			
			
			
//			try {
//				dao.insert(model);
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			
//			try(PrintWriter out = new PrintWriter(PathUtils.getResultPath(model.getProjectName()+".json")))
//			{
//				out.print(parser.toJson(model));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
			System.out.println("progess : " + (++current) + "/" + dependencyList.size());
		}

		
		compareOverlapPackage(300);
		compareOverlapPackage(200);
		compareOverlapPackage(100);
		compareOverlapPackage(50);
		compareOverlapPackage(10);
		//TODO : ������������ �����ϰ� �ٽ��Ұ�. ���� ��������.
//
//		List<ProjectDependencyCompareModel> compareList = new ArrayList<>();
//		
//		for(int i = 0 ; i < 300 ; ++i)
//		{
//			for(int j = i+1 ; j < 300 ; ++j)
//			{
//				compareList.add(new ProjectDependencyCompareModel(dependencyList.get(i), dependencyList.get(j)));
//			}
//		}
//
//		GsonBuilder gson = new GsonBuilder();
//		gson.registerTypeAdapter(ProjectDependencyCompareModel.class, new ProjectDependencyCompareSerializer()).setPrettyPrinting();
//		Gson parser = gson.create();
//		
//		float totalAvg = 0;
//		
//		for(ProjectDependencyCompareModel data:compareList)
//		{
//			totalAvg += data.getOverlapCount();
//		}
//		
//		totalAvg /= 300;
//		
//		try(PrintWriter out = new PrintWriter(PathUtils.getResultPath("random_compare_300.json")))
//		{
//			out.println("avg : " + totalAvg);
//			
//			for(ProjectDependencyCompareModel data:compareList)
//			{
//				out.println(parser.toJson(data));
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		List<ProjectDependencyCompareModel> compareList = new ArrayList<>();
//		for(int i = 0 ; i < 100 ; ++i)
//		{
//			Random rand = new Random();
//			int a = rand.nextInt(100);
//			int b = rand.nextInt(100);
//			
//			while(b == a)
//			{
//				b = rand.nextInt(100);
//			}
//			System.out.println("a : " + a + " b : " + b);
//			compareList.add(new ProjectDependencyCompareModel(dependencyList.get(a), dependencyList.get(b)));
//		}
//		
//		List<ProjectDependencyCompareModel> compareList200 = new ArrayList<>();
//		for(int i = 0 ; i < 200 ; ++i)
//		{
//			Random rand = new Random();
//			int a = rand.nextInt(200);
//			int b = rand.nextInt(200);
//			
//			while(b == a)
//			{
//				b = rand.nextInt(100);
//			}
//			System.out.println("a : " + a + " b : " + b);
//			compareList200.add(new ProjectDependencyCompareModel(dependencyList.get(a), dependencyList.get(b)));
//		}
//		
//		GsonBuilder gson = new GsonBuilder();
//		gson.registerTypeAdapter(ProjectDependencyCompareModel.class, new ProjectDependencyCompareSerializer()).setPrettyPrinting();
//		Gson parser = gson.create();
//		
//		float totalAvg = 0;
//		
//		for(ProjectDependencyCompareModel data:compareList)
//		{
//			totalAvg += data.getOverlapCount();
//		}
//		
//		totalAvg /= compareList.size();
//		
//		try(PrintWriter out = new PrintWriter(PathUtils.getResultPath("random_compare_100.json")))
//		{
//			out.println("avg : " + totalAvg);
//			
//			for(ProjectDependencyCompareModel data:compareList)
//			{
//				out.println(parser.toJson(data));
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		totalAvg = 0;
//		
//		for(ProjectDependencyCompareModel data:compareList200)
//		{
//			totalAvg += data.getOverlapCount();
//		}
//		
//		totalAvg /= compareList200.size();
//		
//		try(PrintWriter out = new PrintWriter(PathUtils.getResultPath("random_compare_200.json")))
//		{
//			out.println("avg : " + totalAvg);
//			
//			for(ProjectDependencyCompareModel data:compareList200)
//			{
//				out.println(parser.toJson(data));
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("onFinished");
	}

	@Override
	public void onFinishedExtract(ProjectDependencyModel result) {
		result.sort();
		dependencyList.add(result);
	}
	
	private void compareOverlapPackage(int rank)
	{
		List<ProjectDependencyCompareModel> compareList = new ArrayList<>();
		
		for(int i = 0 ; i < rank ; ++i)
		{
			for(int j = i+1 ; j < rank ; ++j)
			{
				compareList.add(new ProjectDependencyCompareModel(dependencyList.get(i), dependencyList.get(j)));
			}
		}

		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(ProjectDependencyCompareModel.class, new ProjectDependencyCompareSerializer()).setPrettyPrinting();
		Gson parser = gson.create();
		
		float totalAvg = 0;
		
		for(ProjectDependencyCompareModel data:compareList)
		{
			totalAvg += data.getOverlapCount();
		}
		
		totalAvg /= compareList.size();
		
		try(PrintWriter out = new PrintWriter(PathUtils.getResultPath("random_compare_"+rank+".json")))
		{
			out.println("avg : " + totalAvg);
			
			for(ProjectDependencyCompareModel data:compareList)
			{
				out.println(parser.toJson(data));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

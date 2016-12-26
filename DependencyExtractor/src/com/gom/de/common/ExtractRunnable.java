package com.gom.de.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gom.de.extractor.IExtractor.CallBack;
import com.gom.de.extractor.ProjectDependencyExtractor;
import com.gom.de.model.ProjectDependencyModel;
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
		
		ProjectDependencyExtractor extractor = new ProjectDependencyExtractor(filter);
		extractor.setCallback(this);
		
		for(String projectName:projects)
		{
			System.out.println(projectName);
			extractor.extract(projectName);
		}
		
		onFinished();
	}
	
	private void onFinished()
	{
		Collections.sort(dependencyList);
		
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(ProjectDependencyModel.class, new ProjectDependencySerializer()).setPrettyPrinting();

		Gson parser = gson.create();
		
		for(ProjectDependencyModel model:dependencyList)
		{
			model.externalFiltering();
			
			try(PrintWriter out = new PrintWriter(PathUtils.getResultPath(model.getProjectName()+".json")))
			{
				out.print(parser.toJson(model));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void onFinishedExtract(ProjectDependencyModel result) {
		result.sort();
		dependencyList.add(result);
	}
}

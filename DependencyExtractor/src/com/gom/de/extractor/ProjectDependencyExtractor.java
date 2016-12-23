package com.gom.de.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.gom.de.common.PathUtils;
import com.gom.de.model.ProjectDependencyModel;

public class ProjectDependencyExtractor extends AbstractExtractor<ProjectDependencyModel>
{

	@Override
	public void extract(String projectName) {
		ProjectDependencyModel result = new ProjectDependencyModel(projectName);
		
		File projectRoot = new File(PathUtils.getProjectPath(projectName));
		List<File> javaFiles = (List<File>)FileUtils.listFiles(projectRoot, new String []{"java"}, true);
		
		for(File file:javaFiles)
		{
			try {
				processingJavaFile(result, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		callback.onFinishedExtract(result);
	}
	
	private void processingJavaFile(ProjectDependencyModel model, File file) throws FileNotFoundException, IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	//TODO : processing line
		    }
		}
	}
}

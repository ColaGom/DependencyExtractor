package com.gom.de.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.gom.de.common.PackageFilter;
import com.gom.de.common.PathUtils;
import com.gom.de.common.Utils;
import com.gom.de.model.DependencyModel;
import com.gom.de.model.ExternalDependencyModel;
import com.gom.de.model.ProjectDependencyModel;

public class ProjectDependencyExtractor extends AbstractExtractor<ProjectDependencyModel>
{
	PackageFilter filter;
	public ProjectDependencyExtractor(PackageFilter filter) {
		this.filter = filter;
	}

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
		int lineCount = Utils.getLineCount(file);
		model.addTotalLineCount(lineCount);
		DependencyModel internalModel = new DependencyModel("");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		    	if(line.startsWith("package"))
		    	{
		    		internalModel.setFullName(Utils.extractPackageName(line));
		    		internalModel.addLineCount(lineCount);
		    		model.addInternalModel(internalModel);
		    	}
		    	else if(line.startsWith("import"))
		    	{
		    		ExternalDependencyModel externalModel = new ExternalDependencyModel(Utils.extractPackageName(line));
		    		
		    		if(filter.invalidationPackage(externalModel.getFullName())){
			    		externalModel.addLineCount(lineCount);	
			    		model.addExternalModel(externalModel, internalModel);
		    		}
		    	}
		    }
		}
	}
}

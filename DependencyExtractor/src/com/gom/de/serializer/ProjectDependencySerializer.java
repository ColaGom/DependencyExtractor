package com.gom.de.serializer;

import java.lang.reflect.Type;

import com.gom.de.model.ProjectDependencyModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProjectDependencySerializer implements JsonSerializer<ProjectDependencyModel>{

	@Override
	public JsonElement serialize(ProjectDependencyModel data, Type type, JsonSerializationContext context) {
		JsonObject root = new JsonObject();
		root.addProperty("projectName", data.getProjectName());
		root.addProperty("externalCount", data.getExternalCount());
		root.addProperty("internalCount", data.getInternalCount());
		root.addProperty("totalLineCount", data.getTotalLineCount());
		
		Gson gson = new Gson();
		root.add("externalMap", gson.toJsonTree(data.getExternalMap()));
		root.add("internalMap", gson.toJsonTree(data.getInternalMap()));
			
		return root;
	}

}

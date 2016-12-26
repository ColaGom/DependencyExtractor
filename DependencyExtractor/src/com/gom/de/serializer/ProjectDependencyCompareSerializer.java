package com.gom.de.serializer;

import java.lang.reflect.Type;

import com.gom.de.model.ProjectDependencyCompareModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProjectDependencyCompareSerializer implements JsonSerializer<ProjectDependencyCompareModel> {

	@Override
	public JsonElement serialize(ProjectDependencyCompareModel data, Type type, JsonSerializationContext context) {
		JsonObject root = new JsonObject();
		
		root.addProperty("title", data.getTitle());
		root.addProperty("overlapCount", data.getOverlapCount());
		
		Gson gson = new Gson();
		root.add("overlapList", gson.toJsonTree(data.getOverlapList()));

		return root;
	}

}

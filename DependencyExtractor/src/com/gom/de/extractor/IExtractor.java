package com.gom.de.extractor;

public interface IExtractor<T> {
	
	void setCallback(CallBack<T> callback);
	void extract(String projectName);
	
	public interface CallBack<T>{
		void onFinishedExtract(T result);
	}
	
}

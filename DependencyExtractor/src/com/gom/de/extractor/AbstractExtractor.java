package com.gom.de.extractor;

public abstract class AbstractExtractor<T> implements IExtractor<T> {

	protected IExtractor.CallBack<T> callback;
	
	@Override
	public void setCallback(IExtractor.CallBack<T> callback) {
		this.callback = callback;
	}

	@Override
	public abstract void extract(String projectName);
}

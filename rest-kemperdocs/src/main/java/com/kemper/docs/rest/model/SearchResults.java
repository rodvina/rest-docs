package com.kemper.docs.rest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResults<T extends DocumentResult> extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Map<String, List<T>> results;

	public Map<String, List<T>> getResults() {
		if (this.results == null) {
			this.results = new HashMap<String, List<T>>();
		}
		return this.results;
	}

	public void setResults(Map<String, List<T>> results) {
		this.results = results;
	}
	
	public void putResults(String itemCategory, List<T> results) {
		this.getResults().put(itemCategory, results);
	}

//	private List<T> results;
//	public List<T> getResults() {
//		if (this.results == null) {
//			this.results = new ArrayList<T>();
//		}
//		return this.results;
//	}

//	public void setResults(List<T> results) {
//		this.results = results;
//	}
	
	
}

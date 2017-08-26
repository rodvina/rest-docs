package com.kemper.docs.rest.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResults<T extends DocumentResult> extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private List<T> results;

	public List<T> getResults() {
		if (this.results == null) {
			this.results = new ArrayList<T>();
		}
		return this.results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	
}

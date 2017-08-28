package com.kemper.docs.rest.service;

import com.kemper.docs.rest.model.BaseModel;
import com.kemper.docs.rest.model.DocumentResult;
import com.kemper.docs.rest.model.SearchResults;

public interface DocsService<S extends BaseModel, T extends DocumentResult> {

	public SearchResults<T> search(S request);
}
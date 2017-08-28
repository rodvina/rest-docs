package com.kemper.docs.rest.mapper;

import com.kemper.docs.rest.model.DocumentResult;
import com.kemper.docs.rest.model.SearchResults;
import com.kemper.docs.rest.util.CMSDomain;
import com.ksg.cms.client.model.SearchReply;

public interface IDocMapper<T extends DocumentResult> {

	public SearchResults<T> mapCMSToModel(SearchReply searchReply, CMSDomain domain);
}
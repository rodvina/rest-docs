package com.kemper.docs.rest.cms;

import com.kemper.docs.rest.model.BaseModel;
import com.kemper.docs.rest.model.RetrieveResults;
import com.ksg.cms.client.model.SearchReply;

public interface ICMSDocumentService<T extends BaseModel> {
	
	public RetrieveResults getDocument(String contentId);

	public SearchReply search(T request);

}

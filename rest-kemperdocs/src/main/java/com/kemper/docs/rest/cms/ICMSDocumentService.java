package com.kemper.docs.rest.cms;

import com.kemper.docs.rest.model.BaseModel;
import com.ksg.cms.client.model.RetrieveReply;
import com.ksg.cms.client.model.SearchReply;

public interface ICMSDocumentService<T extends BaseModel> {
	
	public RetrieveReply getDocument(String contentId);

	public SearchReply search(T request);
	
	public String[] getDomains();

}

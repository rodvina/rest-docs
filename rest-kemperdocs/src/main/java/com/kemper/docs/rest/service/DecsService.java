package com.kemper.docs.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kemper.docs.rest.cms.ICMSDocumentService;
import com.kemper.docs.rest.mapper.IDocMapper;
import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.model.DecResult;
import com.kemper.docs.rest.model.RetrieveResults;
import com.kemper.docs.rest.model.SearchResults;
import com.ksg.cms.client.model.RetrieveReply;
import com.ksg.cms.client.model.SearchReply;

@Service("DecsService")
public class DecsService implements DocsService<DecRequest, DecResult> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DecsService.class);

	@Autowired
	@Qualifier("DecsCMSDocumentService")
	private ICMSDocumentService<DecRequest> docService;

	@Autowired
	@Qualifier("DecMapper")
	private IDocMapper<DecResult> mapper;
	
	@Override
	public SearchResults<DecResult> search(DecRequest request) {
		
		SearchReply searchReply = docService.search(request);
		return mapper.mapCMSToModel(searchReply);
	}

	@Override
	public RetrieveResults retrieve(String contentid) {
		RetrieveReply retrieveReply = docService.getDocument(contentid);
		RetrieveResults response = new RetrieveResults();
		response.setDocument(retrieveReply.getDecodedFileByteArray());
		response.setContentId(contentid);
		return response;
	}
}

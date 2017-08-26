package com.kemper.docs.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kemper.docs.rest.model.BaseModel;
import com.kemper.docs.rest.model.RetrieveResults;
import com.ksg.cms.client.CMService;
import com.ksg.cms.client.model.Expression;
import com.ksg.cms.client.model.RetrieveReply;
import com.ksg.cms.client.model.RetrieveRequest;
import com.ksg.cms.client.model.SearchReply;
import com.ksg.cms.client.model.SearchRequest;
import com.ksg.cms.client.util.RequestUtils;

@Service("CMSDocumentService")
public abstract class CMSDocumentService<T extends BaseModel> implements DocumentService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CMSDocumentService.class);
	
	@Value("${cms.request.username}") 
	private String UNAME;
	
	@Value("${cms.request.password}") 
	private String PWD;
	
	@Value("${cms.request.orgname}") 
	private String ORGNAME;
	
	@Value("${cms.request.appname}") 
	private String APPNAME;
	
	@Value("${cms.request.spname}") 
	private String SPNAME;
	
	@Autowired
	private CMService cmClient;
	
	@Override
	public RetrieveResults getDocument(String contentId) {
		
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		RequestUtils.addCustDetails(retrieveRequest, SPNAME, UNAME, PWD);
		RequestUtils.addClientDetails(retrieveRequest, ORGNAME, APPNAME);
		
		retrieveRequest.setContentID(contentId);
		
		RetrieveReply retrieveReply = cmClient.retrieve(retrieveRequest);
		
		RetrieveResults response = new RetrieveResults();
		if (retrieveReply.isSuccess()) {
			LOGGER.warn("Desc=SuccessfulDocRetrieve;ContentId="+contentId);
			response.setDocument(retrieveReply.getDecodedFileByteArray());
			
		} else {
			LOGGER.error("Error=CMSFailedToRetrieveDocument;RetrieveRequest="+retrieveRequest.toXmlString());
			LOGGER.error("Error=CMSFailedToRetrieveDocument;RetrieveReply=" + retrieveReply.toXmlString());
		}
		
		return response;
	}
	
	@Override
	public SearchReply search(T request) {
		
		SearchRequest searchRequest = new SearchRequest();
		RequestUtils.addCustDetails(searchRequest, SPNAME, UNAME, PWD);
		RequestUtils.addClientDetails(searchRequest, ORGNAME, APPNAME);

		
		RequestUtils.addSearchDetails(searchRequest, RequestUtils.createDomains(this.getDomains()), this.getExpression(request).toString());
		
		LOGGER.info("searchRequest="+searchRequest.toXmlString());
		
		SearchReply searchReply = cmClient.search(searchRequest);

		LOGGER.info(">>>>>>>>>> Response XML:<<<<<<<<<<<\n" + searchReply.toXmlString());
		
		return searchReply;
	}

	/**
	 * Implement to return <code>Expression</code>
	 * @return
	 */
	abstract Expression getExpression(T request);

	/**
	 * Implement to return string array of domains
	 * @return
	 */
	abstract String[] getDomains();
	
}
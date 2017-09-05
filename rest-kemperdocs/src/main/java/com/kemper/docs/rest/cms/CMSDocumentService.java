package com.kemper.docs.rest.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kemper.docs.rest.model.BaseModel;
import com.ksg.cms.client.CMService;
import com.ksg.cms.client.model.Expression;
import com.ksg.cms.client.model.RetrieveReply;
import com.ksg.cms.client.model.RetrieveRequest;
import com.ksg.cms.client.model.SearchReply;
import com.ksg.cms.client.model.SearchRequest;
import com.ksg.cms.client.util.RequestUtils;

public abstract class CMSDocumentService<T extends BaseModel> implements ICMSDocumentService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CMSDocumentService.class);
	
	@Autowired
	private CMSProperties cmsProperties;
	
	@Autowired
	private CMService cmClient;
	
	@Override
	public RetrieveReply getDocument(String contentId) {
		
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		RequestUtils.addCustDetails(retrieveRequest, cmsProperties.getRequestSpname(), cmsProperties.getRequestUsername(), cmsProperties.getRequestPassword());
		RequestUtils.addClientDetails(retrieveRequest, cmsProperties.getRequestOrgname(), cmsProperties.getRequestAppname());
		
		retrieveRequest.setContentID(contentId);
		
		RetrieveReply retrieveReply = cmClient.retrieve(retrieveRequest);
		
		if (retrieveReply.isSuccess()) {
			LOGGER.warn("Desc=SuccessfulDocRetrieve;ContentId="+contentId);
//			response.setDocument(retrieveReply.getDecodedFileByteArray());
			
		} else {
			LOGGER.error("Error=CMSFailedToRetrieveDocument;RetrieveRequest="+retrieveRequest.toXmlString());
			LOGGER.error("Error=CMSFailedToRetrieveDocument;RetrieveReply=" + retrieveReply.toXmlString());
		}
		
		return retrieveReply;
	}
	
	@Override
	public SearchReply search(T request) {
		
		SearchRequest searchRequest = new SearchRequest();

		RequestUtils.addCustDetails(searchRequest, cmsProperties.getRequestSpname(), cmsProperties.getRequestUsername(), cmsProperties.getRequestPassword());
		RequestUtils.addClientDetails(searchRequest, cmsProperties.getRequestOrgname(), cmsProperties.getRequestAppname());
		
		
		RequestUtils.addSearchDetails(searchRequest, 
				RequestUtils.createDomains(this.getDomains()), this.getExpression(request).toString());
		
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
	public abstract String[] getDomains();
	
}

package com.kemper.docs.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kemper.docs.rest.model.DocumentResult;
import com.kemper.docs.rest.model.SearchResults;
import com.kemper.docs.rest.util.CMSConstants;
import com.kemper.docs.rest.util.CMSDomain;
import com.ksg.cms.client.model.DomainResponse;
import com.ksg.cms.client.model.SearchReply;

/**
 * Subclass this and implement the <code>mapMetaData</code> method to map
 * the cms document response metadata to your <code>DocumentResult</code> subclass
 * 
 * This class handles the boilerplate code of evaluating the <code>SearchReply</code> 
 * object
 * 
 * @author kahrxo
 *
 * @param <T>
 */
public abstract class DocMapper<T extends DocumentResult> implements IDocMapper<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocMapper.class);
	
	public SearchResults<T> mapCMSToModel(SearchReply searchReply, CMSDomain domain) {
		SearchResults<T> searchResults = new SearchResults<T>();
		//check searchReply
		if (searchReply != null && searchReply.isSuccess()) {
			
			//check domain response map
			if (MapUtils.isNotEmpty(searchReply.getDomainResponses()) ) {
			
				//find response list by domain
				List<DomainResponse> responseListByDomain = searchReply.getDomainResponses().get(domain.toString());
				searchResults.setResults(this.map(responseListByDomain));
			}
		}
		
		return searchResults;

	}
	
	private List<T> map(List<DomainResponse> responseListByDomain) {

		if (CollectionUtils.isEmpty(responseListByDomain)) {
			return new ArrayList<T>();
		}
		
		List<T> collect = 
				 responseListByDomain.stream()
							.map( x -> this.mapMetaData(x) )
							.collect(Collectors.toList());
		return collect;
		
	}
	
	protected String getCMSFieldValue(Map<String, String> metadataMap, CMSConstants.CMSField cmsField) {
		return metadataMap.get(cmsField.toString());
	}
	
	/**
	 * Override to implement mapping of metadata map to subclass type of <code>DocumentResult</code>
	 * @param metadataMap
	 * @return
	 */
	abstract T mapMetaData(DomainResponse domainResponse);


//	public static void heehe(SearchReply searchReply) {
//		if (searchReply.isSuccess()) {
//			
//			//searchReply contains a list of domains. each domain contains a list of responses.
//			//we are expecting a search by invoice # to return 1 domain and 1 response since this is a
//			//request to retrieve the PDF by invoice #. this will not handle multiple domains or multiple responses
//			//and will return the first match only
//			String contentId = null;
//			Map<String, List<DomainResponse>> responses = searchReply.getDomainResponses();
////[findbugs:performance] iterator on entrySet is more efficient than iterator on keySet 			
////			Iterator<String> it = responses.keySet().iterator();
//			Iterator<Entry<String, List<DomainResponse>>> it = responses.entrySet().iterator();
//			
//			
//			//iterate through each domain
//			while (it.hasNext()) {
////				String domain = it.next();
//				Entry<String, List<DomainResponse>> domain = it.next();				
//
//				//get list of responses for domain
////				List<DomainResponse> drList = responses.get(domain);
//				List<DomainResponse> drList = domain.getValue();
//				LOGGER.info("CMSDomain="+domain.getKey());
//				if (CollectionUtils.isNotEmpty(drList)) {
//					//get itemId from 1st response
//					contentId = drList.get(0).getItemID();
//					response = new DocumentResponse();
//					response.setContentId(contentId);
//					break;
//				}
//				
//			}
//			
//			//empty search reply
//			if (null == response) {
//				LOGGER.error("Error=CMSSearchReplyReturnedEmpty");
//				response = new DocumentResponse();
//				response.setStatus(new Status(MsgStatusCode.SYSTEMERROR, "Empty searchReply from CMS"));
//			}
//
//		} else {
//			// 4.1 - failure; inspect failure reason(s)
//			LOGGER.error("Error=CMSSearchFailed;errorCode=" + searchReply.getErrorCode());
//
//			//4.1.2 - if failure == 200; check ext message
//			if(Integer.parseInt(searchReply.getErrorCode()) == 200)
//			{
//				LOGGER.error("Error=CMSSearchFailed;extErrorCode=" + searchReply.getExtErrorCode());
//				LOGGER.error("Error=CMSSearchFailed;extErrorDesc=" + searchReply.getExtErrorDesc());
//			}			
//			response = new DocumentResponse();
//			response.setStatus(new Status(MsgStatusCode.SYSTEMERROR, "Unsuccessful searchReply from CMS"));
//			
//		}
		
//	}
}

package com.kemper.docs.rest.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.util.CMSDomain;
import com.ksg.cms.client.model.Expression;

/**
 * This class wraps the <code>CMClient</code> by subclassing the <code>CMSDocumentService</code> 
 * and overriding the <code>getExpression</code> and <code>getDomains</code> methods.  
 * 
 * In doing so, this class can be used to perform search operations on the DOM_DECS_AND_NTCE doc class/folder
 * 
 * @author kahrxo
 *
 */
@Service("DecsCMSDocumentService")
public class DecsCMSDocumentService extends CMSDocumentService<DecRequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DecsCMSDocumentService.class);
	
	@Override
	protected Expression getExpression(DecRequest request) {

		LOGGER.info("decRequest="+request);
		//build expression				
		Expression expression = DecsExpressionBuilder.buildExpression(request);
		LOGGER.info("expression="+expression);
		return expression;
	}

	@Override
	public String[] getDomains() {
		//TODO: find domain name
		return new String[]{CMSDomain.DOM_DECS_AND_NTCE.toString()};
	}

}

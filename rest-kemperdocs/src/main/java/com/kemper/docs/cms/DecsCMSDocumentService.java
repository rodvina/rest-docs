package com.kemper.docs.cms;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kemper.docs.rest.model.DecRequest;
import com.ksg.cms.client.model.Expression;

@Service("DecsCMSDocumentService")
public class DecsCMSDocumentService extends CMSDocumentService<DecRequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DecsCMSDocumentService.class);
	
	@Override
	Expression getExpression(DecRequest request) {

		LOGGER.info("decRequest="+request);
		//build expression
		//TODO: find METADATA names
		Expression expression = 
				Expression.eq("FROM_DT", request.getFromDate())
				.and(Expression.eq("TO_DATE", request.getToDate())
				.and(Expression.in(
						"PRODUCER_CODE", request.getProducerCodeList().toArray(new String[request.getProducerCodeList().size()])
						)
					) 
				);
				
		
		if (StringUtils.isNotEmpty(request.getPolicyno())) {
			expression = expression.and(Expression.like("POL_NO", request.getPolicyno()));
		}
		
		

		return expression;
	}

	@Override
	String[] getDomains() {
		//TODO: find domain name
		return new String[]{"DOM_ENTP_BILL_FORMS"};
	}

}

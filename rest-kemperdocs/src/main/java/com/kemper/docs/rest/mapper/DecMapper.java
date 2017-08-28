package com.kemper.docs.rest.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kemper.docs.rest.model.DecResult;
import com.ksg.cms.client.model.DomainResponse;

@Component("DecMapper")
public class DecMapper extends DocMapper<DecResult> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DecMapper.class);
	
	protected DecResult mapMetaData(DomainResponse domainResponse) {
		Map<String, String> metadataMap = domainResponse.getMetaDataMap();
		DecResult dec = new DecResult();
		dec.setPolicyno(metadataMap.get("POL_NO"));
		dec.setProducerCode(metadataMap.get("PROD_CD"));
		dec.setContentId(domainResponse.getItemID());
		return dec;	
	}
	

}

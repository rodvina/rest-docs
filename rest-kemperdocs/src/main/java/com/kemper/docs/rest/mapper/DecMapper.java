package com.kemper.docs.rest.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kemper.docs.rest.model.DecResult;
import com.kemper.docs.rest.util.CMSConstants;
import com.kemper.docs.rest.util.CMSConstants.CMSMetaData;
import com.ksg.cms.client.model.DomainResponse;

@Component("DecMapper")
public class DecMapper extends DocMapper<DecResult> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DecMapper.class);
	
	protected DecResult mapMetaData(DomainResponse domainResponse) {
		Map<String, String> metadataMap = domainResponse.getMetaDataMap();
		DecResult dec = new DecResult();
		dec.setPolicyno(super.getCMSMetaDataValue(metadataMap, CMSMetaData.POL_NO_TX));
		dec.setProducerCode(super.getCMSMetaDataValue(metadataMap, CMSMetaData.PRDCR_CD));
		dec.setCity(super.getCMSMetaDataValue(metadataMap, CMSMetaData.MAIL_CITY_NM));
		dec.setState(super.getCMSMetaDataValue(metadataMap, CMSMetaData.RSK_ST_CD));
		dec.setZip(super.getCMSMetaDataValue(metadataMap, CMSMetaData.MAIL_ZIP_CD));
		dec.setTransactionType(super.getCMSMetaDataValue(metadataMap, CMSMetaData.TRAN_TYPE_TX));
		dec.setLob(super.getCMSMetaDataValue(metadataMap, CMSMetaData.LOB_CD));
		dec.setEffectiveDate(super.getCMSMetaDataValue(metadataMap, CMSConstants.CMSMetaData.EFF_DT));
		dec.setPremium(super.getCMSMetaDataValue(metadataMap, CMSConstants.CMSMetaData.PRM_AMT));
		dec.setContentId(domainResponse.getItemID());
		return dec;	
	}
	

}

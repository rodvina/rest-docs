package com.kemper.docs.rest.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kemper.docs.rest.model.DecResult;
import com.kemper.docs.rest.util.CMSConstants;
import com.kemper.docs.rest.util.CMSConstants.CMSField;
import com.ksg.cms.client.model.DomainResponse;

@Component("DecMapper")
public class DecMapper extends DocMapper<DecResult> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DecMapper.class);
	
	protected DecResult mapMetaData(DomainResponse domainResponse) {
		Map<String, String> metadataMap = domainResponse.getMetaDataMap();
		DecResult dec = new DecResult();
		dec.setPolicyno(super.getCMSFieldValue(metadataMap, CMSField.POL_NO_TX_MV));
		dec.setProducerCode(super.getCMSFieldValue(metadataMap, CMSField.PRDCR_CD));
		dec.setCity(super.getCMSFieldValue(metadataMap, CMSField.MAIL_CITY_NM_MV));
		dec.setState(super.getCMSFieldValue(metadataMap, CMSField.RSK_ST_CD_MV));
		dec.setZip(super.getCMSFieldValue(metadataMap, CMSField.MAIL_ZIP_CD_MV));
		dec.setTransactionType(super.getCMSFieldValue(metadataMap, CMSField.TRAN_TYPE_TX_MV));
		dec.setLob(super.getCMSFieldValue(metadataMap, CMSField.LOB_CD_MV));
		dec.setEffectiveDate(super.getCMSFieldValue(metadataMap, CMSConstants.CMSField.EFF_DT_MV));
		dec.setPremium(super.getCMSFieldValue(metadataMap, CMSConstants.CMSField.PRM_AMT));
		dec.setContentId(domainResponse.getItemID());
		return dec;	
	}
	

}

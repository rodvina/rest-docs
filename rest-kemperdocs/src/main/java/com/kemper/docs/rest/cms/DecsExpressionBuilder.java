package com.kemper.docs.rest.cms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.util.CMSConstants.CMSField;
import com.kemper.docs.rest.util.DateUtil;
import com.ksg.cms.client.model.Expression;

public class DecsExpressionBuilder extends ExpressionBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(DecsExpressionBuilder.class);

	static Expression buildCommonExpression(DecRequest decRequest,
			Expression expression) {
		if (decRequest != null) {
			LOGGER.info("in search for Document type" + decRequest.getDocumentType());
			if (decRequest.getDocumentType() != null) {
				expression = buildExpressionForEq(expression, CMSField.DOC_TYPE_TX,
						decRequest.getDocumentType());
			}
			LOGGER.info("in search for Posting Date");
			expression = buildExpressionForPostingDate(decRequest, expression);

			LOGGER.info("in search for Producer Codes" + decRequest.getProducerCodeList());
			if ((decRequest.getProducerCodeList() != null)
					&& (!decRequest.getProducerCodeList().isEmpty())) {
				if (decRequest.getProducerCodeList().size() == 1) {
					String producerCode = (String) decRequest.getProducerCodeList().get(0);
					if (producerCode.length() <= 3) {
						expression = buildExpressionForLike(expression, CMSField.PRDCR_CD, producerCode);
					} else {
						expression = buildExpressionForIn(expression, CMSField.PRDCR_CD,
								(String[]) decRequest.getProducerCodeList()
										.toArray(new String[decRequest.getProducerCodeList().size()]));
					}
				} else {
					expression = buildExpressionForIn(expression, CMSField.PRDCR_CD,
							(String[]) decRequest.getProducerCodeList()
									.toArray(new String[decRequest.getProducerCodeList().size()]));
				}
			}
		}
		return expression;
	}

	static Expression buildExpressionForPostingDate(DecRequest searchRequest,
			Expression expression) {
		if (searchRequest != null) {
			LOGGER.info("from Date=" + searchRequest.getFromDate() + "to Date" + searchRequest.getToDate());
			if ((searchRequest.getFromDate() != null) && (searchRequest.getToDate() != null)) {
				expression = buildExpressionForBetween(expression, CMSField.POST_DT, DateUtil.toDate(searchRequest.getFromDate()),
						DateUtil.toDate(searchRequest.getToDate()));
			}
		}
		return expression;
	}

	public static Expression buildExpression(DecRequest decRequest) {
		LOGGER.info("Inside KemperdecsAndNoticesExpressionBuilder buildExpression ");
		Expression expression = null;
		if (decRequest != null) {
			expression = buildCommonExpression(decRequest, expression);

			LOGGER.info("Last Name" + decRequest.getLastName());
			if (decRequest.getLastName() != null) {
				expression = buildExpressionForStartsWith(expression, CMSField.INS_LST_NM_MV,
						decRequest.getLastName().toUpperCase());
			}
//			LOGGER.info("Policy numbers" + decRequest.getPolicyNumbers());
//			if ((decRequest.getPolicyNumbers() != null)
//					&& (!decRequest.getPolicyNumbers().isEmpty())) {
//				if (decRequest.getPolicyNumbers().size() == 1) {
//					String policyNo = (String) decRequest.getPolicyNumbers().get(0);
//					LOGGER.info("Policy No" + (String) decRequest.getPolicyNumbers().get(0));
//					if ((policyNo.length() > 1) && (policyNo.length() < 9)) {
//						expression = buildExpressionForStartsWith(expression, "POL_NO_TX",
//								((String) decRequest.getPolicyNumbers().get(0)).toUpperCase());
//					} else {
//						expression = buildExpressionForEq(expression, "POL_NO_TX",
//								((String) decRequest.getPolicyNumbers().get(0)).toUpperCase());
//					}
//				} else {
//					expression = buildExpressionForIn(expression, "POL_NO_TX",
//							(String[]) decRequest.getPolicyNumbers()
//									.toArray(new String[decRequest.getPolicyNumbers().size()]));
//				}
//			}
			String policyNo = decRequest.getPolicyno();
			LOGGER.info("Policy No" + policyNo);
			if (policyNo != null) {
//			if ((policyNo.length() > 1) && (policyNo.length() < 9)) {
				expression = buildExpressionForStartsWith(expression, CMSField.POL_NO_TX_MV,
						policyNo.toUpperCase());
//			} else {
//				expression = buildExpressionForEq(expression, "POL_NO_TX",
//						((String) decRequest.getPolicyNumbers().get(0)).toUpperCase());
//			}
			}
			LOGGER.info("effective date as String  " + decRequest.getEffectiveDate()
					+ "****" + decRequest.getEffectiveDate());
			if (decRequest.getEffectiveDate() != null) {
				expression = buildExpressionForEq(expression, CMSField.EFF_DT_MV,
						String.valueOf(DateUtil.toDate(decRequest.getEffectiveDate()).getTime()));
			}
			LOGGER.info("Transaction Type  " + decRequest.getTransactionType());
			if (decRequest.getTransactionType() != null) {
				expression = buildExpressionForEq(expression, CMSField.TRAN_TYPE_TX_MV,
						decRequest.getTransactionType());
			}
			LOGGER.info("State  " + decRequest.getState());
			if (decRequest.getState() != null) {
				expression = buildExpressionForEq(expression, CMSField.MAIL_ST_NM_MV, decRequest.getState());
			}
			LOGGER.info("Lob value in expression " + decRequest.getLob());
			if (decRequest.getLob() != null) {
				expression = buildExpressionForEq(expression, CMSField.LOB_CD_MV, decRequest.getLob());
			}

			LOGGER.info("effective date as String  " + decRequest.getZipCode());
			if (decRequest.getZipCode() != null) {
				expression = buildExpressionForEq(expression, CMSField.MAIL_ZIP_CD_MV,
						decRequest.getZipCode());
			}
		}
		if ((expression != null) && (LOGGER.isInfoEnabled())) {
			LOGGER.info("Kemper Decs And Notices search expression: " + expression.toString());
		}
		return expression;
	}
}

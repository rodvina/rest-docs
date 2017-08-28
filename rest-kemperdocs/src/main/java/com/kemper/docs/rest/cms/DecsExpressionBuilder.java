package com.kemper.docs.rest.cms;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.kemper.docs.rest.model.DecRequest;
import com.kemper.docs.rest.util.DateUtil;
import com.ksg.cms.client.model.Expression;

public class DecsExpressionBuilder extends ExpressionBuilder {
	private static final Logger logger = Logger.getLogger(DecsExpressionBuilder.class.getName());

	static Expression buildCommonExpression(DecRequest decRequest,
			Expression expression) {
		if (decRequest != null) {
			logger.info("in search for Document type" + decRequest.getDocumentType());
			if (decRequest.getDocumentType() != null) {
				expression = buildExpressionForEq(expression, "DOC_TYPE_TX",
						decRequest.getDocumentType());
			}
			logger.info("in search for Posting Date");
			expression = buildExpressionForPostingDate(decRequest, expression);

			logger.info("in search for Producer Codes" + decRequest.getProducerCodeList());
			if ((decRequest.getProducerCodeList() != null)
					&& (!decRequest.getProducerCodeList().isEmpty())) {
				if (decRequest.getProducerCodeList().size() == 1) {
					String producerCode = (String) decRequest.getProducerCodeList().get(0);
					if (producerCode.length() <= 3) {
						expression = buildExpressionForLike(expression, "PRDCR_CD", producerCode);
					} else {
						expression = buildExpressionForIn(expression, "PRDCR_CD",
								(String[]) decRequest.getProducerCodeList()
										.toArray(new String[decRequest.getProducerCodeList().size()]));
					}
				} else {
					expression = buildExpressionForIn(expression, "PRDCR_CD",
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
			logger.info("from Date=" + searchRequest.getFromDate() + "to Date" + searchRequest.getToDate());
			if ((searchRequest.getFromDate() != null) && (searchRequest.getToDate() != null)) {
				expression = buildExpressionForBetween(expression, "POST_DT", DateUtil.toDate(searchRequest.getFromDate()),
						DateUtil.toDate(searchRequest.getToDate()));
			}
		}
		return expression;
	}

	public static Expression buildExpression(DecRequest decRequest) {
		logger.info("Inside KemperdecsAndNoticesExpressionBuilder buildExpression ");
		Expression expression = null;
		if (decRequest != null) {
			expression = buildCommonExpression(decRequest, expression);

			logger.info("Last Name" + decRequest.getLastName());
			if (decRequest.getLastName() != null) {
				expression = buildExpressionForStartsWith(expression, "INS_LST_NM",
						decRequest.getLastName().toUpperCase());
			}
//			logger.info("Policy numbers" + decRequest.getPolicyNumbers());
//			if ((decRequest.getPolicyNumbers() != null)
//					&& (!decRequest.getPolicyNumbers().isEmpty())) {
//				if (decRequest.getPolicyNumbers().size() == 1) {
//					String policyNo = (String) decRequest.getPolicyNumbers().get(0);
//					logger.info("Policy No" + (String) decRequest.getPolicyNumbers().get(0));
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
			logger.info("Policy No" + policyNo);
			if (policyNo != null) {
//			if ((policyNo.length() > 1) && (policyNo.length() < 9)) {
				expression = buildExpressionForStartsWith(expression, "POL_NO_TX",
						policyNo.toUpperCase());
//			} else {
//				expression = buildExpressionForEq(expression, "POL_NO_TX",
//						((String) decRequest.getPolicyNumbers().get(0)).toUpperCase());
//			}
			}
			logger.info("effective date as String  " + decRequest.getEffectiveDate()
					+ "****" + decRequest.getEffectiveDate());
			if (decRequest.getEffectiveDate() != null) {
				expression = buildExpressionForEq(expression, "EFF_DT",
						String.valueOf(DateUtil.toDate(decRequest.getEffectiveDate()).getTime()));
			}
			logger.info("Transaction Type  " + decRequest.getTransactionType());
			if (decRequest.getTransactionType() != null) {
				expression = buildExpressionForEq(expression, "TRAN_TYPE_TX",
						decRequest.getTransactionType());
			}
			logger.info("State  " + decRequest.getState());
			if (decRequest.getState() != null) {
				expression = buildExpressionForEq(expression, "MAIL_ST_NM", decRequest.getState());
			}
			logger.info("Lob value in expression " + decRequest.getLob());
			if (decRequest.getLob() != null) {
				expression = buildExpressionForEq(expression, "LOB_CD", "DFIRE");
			}

			logger.info("effective date as String  " + decRequest.getZipCode());
			if (decRequest.getZipCode() != null) {
				expression = buildExpressionForEq(expression, "MAIL_ZIP_CD",
						decRequest.getZipCode());
			}
		}
		if ((expression != null) && (logger.isLoggable(Level.INFO))) {
			logger.info("Kemper Decs And Notices search expression: " + expression.toString());
		}
		return expression;
	}
}

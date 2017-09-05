package com.kemper.docs.rest.cms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kemper.docs.rest.util.CMSConstants.CMSField;
import com.ksg.cms.client.model.Expression;

public class ExpressionBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionBuilder.class);

	public static Expression buildExpressionForBetween(Expression expression, CMSField attribute, Date fromDate,
			Date toDate) {
		if (expression == null) {
			expression = Expression.between(attribute.toString(), fromDate, toDate);
		} else {
			expression = expression.and(Expression.between(attribute.toString(), fromDate, toDate));
		}
		return expression;
	}

	public static Expression buildExpressionForEq(Expression expression, CMSField attribute, String value) {
		if (expression == null) {
			expression = Expression.eq(attribute.toString(), value);
		} else {
			expression = expression.and(Expression.eq(attribute.toString(), value));
		}
		return expression;
	}

	public static Expression buildExpressionForIn(Expression expression, CMSField attribute, String[] values) {
		if (expression == null) {
			expression = Expression.in(attribute.toString(), values);
		} else {
			expression = expression.and(Expression.in(attribute.toString(), values));
		}
		return expression;
	}

	public static Expression buildExpressionForGtEq(Expression expression, CMSField attribute, String value) {
		if (expression == null) {
			expression = Expression.gtEq(attribute.toString(), value);
		} else {
			expression = expression.and(Expression.gtEq(attribute.toString(), value));
		}
		return expression;
	}

	public static Expression buildExpressionForLike(Expression expression, CMSField attribute, String value) {
		if (expression == null) {
			expression = Expression.like(attribute.toString(), value);
		} else {
			expression = expression.and(Expression.like(attribute.toString(), value));
		}
		return expression;
	}

	public static Expression buildExpressionForStartsWith(Expression expression, CMSField attribute, String value) {
		if (expression == null) {
			expression = Expression.startsWith(attribute.toString(), value);
		} else {
			expression = expression.and(Expression.startsWith(attribute.toString(), value));
		}
		return expression;
	}

	public static Expression buildExpressionForLtEq(Expression expression, CMSField attribute, String value) {
		if (expression == null) {
			expression = Expression.ltEq(attribute.toString(), value);
		} else {
			expression = expression.and(Expression.ltEq(attribute.toString(), value));
		}
		return expression;
	}

	public static Expression buildExpressionForAnd(Expression expression, Expression newExpression) {
		if (expression != null) {
			expression = expression.and(newExpression);
		} else {
			expression = newExpression;
		}
		return expression;
	}
}

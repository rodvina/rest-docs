package com.kemper.docs.rest.cms;

import com.ksg.cms.client.model.Expression;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressionBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionBuilder.class);

	public static Expression buildExpressionForBetween(Expression expression, String attribute, Date fromDate,
			Date toDate) {
		if (expression == null) {
			expression = Expression.between(attribute, fromDate, toDate);
		} else {
			expression = expression.and(Expression.between(attribute, fromDate, toDate));
		}
		return expression;
	}

	public static Expression buildExpressionForEq(Expression expression, String attribute, String value) {
		if (expression == null) {
			expression = Expression.eq(attribute, value);
		} else {
			expression = expression.and(Expression.eq(attribute, value));
		}
		return expression;
	}

	public static Expression buildExpressionForIn(Expression expression, String attribute, String[] values) {
		if (expression == null) {
			expression = Expression.in(attribute, values);
		} else {
			expression = expression.and(Expression.in(attribute, values));
		}
		return expression;
	}

	public static Expression buildExpressionForGtEq(Expression expression, String attribute, String value) {
		if (expression == null) {
			expression = Expression.gtEq(attribute, value);
		} else {
			expression = expression.and(Expression.gtEq(attribute, value));
		}
		return expression;
	}

	public static Expression buildExpressionForLike(Expression expression, String attribute, String value) {
		if (expression == null) {
			expression = Expression.like(attribute, value);
		} else {
			expression = expression.and(Expression.like(attribute, value));
		}
		return expression;
	}

	public static Expression buildExpressionForStartsWith(Expression expression, String attribute, String value) {
		if (expression == null) {
			expression = Expression.startsWith(attribute, value);
		} else {
			expression = expression.and(Expression.startsWith(attribute, value));
		}
		return expression;
	}

	public static Expression buildExpressionForLtEq(Expression expression, String attribute, String value) {
		if (expression == null) {
			expression = Expression.ltEq(attribute, value);
		} else {
			expression = expression.and(Expression.ltEq(attribute, value));
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

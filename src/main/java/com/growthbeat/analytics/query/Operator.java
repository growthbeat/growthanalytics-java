package com.growthbeat.analytics.query;

import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.Sets;

public enum Operator {
	equal, greater, less, greater_equal, less_equal, begin_with, like, exist, in;

	private static final Set<Operator> NUMBER_COMPARABLE_OPERATORS = Sets.immutableEnumSet(equal, greater, less, greater_equal, less_equal);

	public boolean evaluate(String string, String targetString) {

		if (NUMBER_COMPARABLE_OPERATORS.contains(this) && NumberUtils.isNumber(targetString)) {
			if (!NumberUtils.isNumber(string))
				return false;
			return evaluate(NumberUtils.toDouble(string), NumberUtils.toDouble(targetString));
		}

		switch (this) {
		case equal:
			return string.equals(targetString);
		case greater:
			return (string.compareTo(targetString) > 0);
		case less:
			return (string.compareTo(targetString) < 0);
		case greater_equal:
			return (string.compareTo(targetString) >= 0);
		case less_equal:
			return (string.compareTo(targetString) <= 0);
		case begin_with:
			return string.startsWith(targetString);
		case like:
			return string.contains(targetString);
		case exist:
			return (string != null);
		case in:
			return evaluateInQuery(string, targetString);
		default:
			throw new IllegalStateException();
		}

	}

	public boolean evaluate(double number, double targetNumber) {

		switch (this) {
		case equal:
			return (number == targetNumber);
		case greater:
			return (number > targetNumber);
		case less:
			return (number < targetNumber);
		case greater_equal:
			return (number >= targetNumber);
		case less_equal:
			return (number <= targetNumber);
		case begin_with:
		case like:
		case exist:
		case in:
		default:
			throw new IllegalStateException();
		}

	}

	private boolean evaluateInQuery(String value, String inValues) {
		String escapedValue = escapeComma(value);

		for (String splited : convertEscapedComma(inValues).split(",")) {
			if (splited.equals(escapedValue))
				return true;
		}

		return false;

	}

	private String escapeComma(String src) {
		return src.replace(",", "{{esc_com}}");
	}

	private String convertEscapedComma(String src) {
		return src.replace("\\\\", "{{esc_dbs}}").replace("\\,", "{{esc_com}}")
				.replace("{{esc_dbs}}", "\\");
	}

}

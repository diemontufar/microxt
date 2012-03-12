package mobile.web.webxt.client.util;

import java.math.BigDecimal;
import java.util.Date;

import mobile.common.tools.CommonConverter;
import mobile.common.tools.Format;

public class WebConverter extends CommonConverter {

	public static String dateToString(Date date) {
		return DatesManager.dateToString(date, Format.DATE);
	}

	public static String completeValue(Object value) {
		String completeValue = null;

		if (value instanceof String) {
			completeValue = value.toString();
		} else if (value instanceof Integer) {
			completeValue = "((Integer))" + value.toString();
		} else if (value instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) value;
			bd = bd.setScale(2);
			completeValue = "((BigDecimal))" + bd.toString();
		} else if (value instanceof Boolean) {
			completeValue = "((Boolean))" + value.toString();
		} else if (value instanceof Long) {
			completeValue = "((Long))" + value.toString();
		} else if (value instanceof Date) {
			completeValue = "((Date))" + DatesManager.dateToString((Date) value, Format.DATE);
			// completeValue = "((Timestamp))" +
			// DatesManager.dateToString((Date)value, Format.TIMESTAMP);
		} else {
			completeValue = value.toString();
		}

		return completeValue;
	}

	public static Object convertToType(String value) {
		final String INTEGER = "\\(\\(Integer\\)\\)";
		final String BIG_DECIMAL = "\\(\\(BigDecimal\\)\\)";
		final String BOOLEAN = "\\(\\(Boolean\\)\\)";
		final String LONG = "\\(\\(Long\\)\\)";
		final String DATE = "\\(\\(Date\\)\\)";
		final String TIMESTAMP = "\\(\\(Timestamp\\)\\)";

		Object cValue = null;

		// System.out.println("::Valor a convertir" + value);

		if (value == null) {
			return cValue;
		}

		if (value.matches("^(" + INTEGER + "|" + BIG_DECIMAL + "|" + BOOLEAN + "|" + LONG + "|" + DATE + "|"
				+ TIMESTAMP + ").*")) {
			if (value.matches("^(" + INTEGER + ").*")) {
				value = value.replaceAll("(" + INTEGER + ")", "");
				cValue = Integer.parseInt(value);
			} else if (value.matches("^(" + BIG_DECIMAL + ").*")) {
				value = value.replaceAll("(" + BIG_DECIMAL + ")", "");
				cValue = new BigDecimal(value);
			} else if (value.matches("^(" + BOOLEAN + ").*")) {
				value = value.replaceAll("(" + BOOLEAN + ")", "");
				cValue = WebConverter.parseBoolean(value);
			} else if (value.matches("^(" + LONG + ").*")) {
				value = value.replaceAll("(" + LONG + ")", "");
				cValue = Long.parseLong(value);
			} else if (value.matches("^(" + DATE + ").*")) {
				value = value.replaceAll("(" + DATE + ")", "");
				cValue = DatesManager.stringToDate(value, Format.DATE);
			} else if (value.matches("^(" + TIMESTAMP + ").*")) {
				value = value.replaceAll("(" + TIMESTAMP + ")", "");
				cValue = DatesManager.stringToDate(value, Format.TIMESTAMP);
			}
		} else {
			cValue = value;
		}
		return cValue;
	}

	public static String completeValue(String value, Object class1) {
		String cvalue = null;
		if (class1 == Integer.class) {
			cvalue = "((Integer))" + value;
		} else if (class1 == Long.class) {
			cvalue = "((Long))" + value;
		} else if (class1 == BigDecimal.class) {
			cvalue = "((BigDecimal))" + value;
		} else if (class1 == Boolean.class) {
			cvalue = "((Boolean))" + value;
		} else if (class1 == Date.class) {
			cvalue = "((Date))" + value;
		}
		return cvalue;
	}

}

package mobile.web.webxt.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public abstract class DateFormat {
	/**
	 * Constant for full style pattern.
	 */
	public static final int FULL = 0;

	/**
	 * Constant for long style pattern.
	 */
	public static final int LONG = 1;

	/**
	 * Constant for medium style pattern.
	 */
	public static final int MEDIUM = 2;

	/**
	 * Constant for short style pattern.
	 */
	public static final int SHORT = 3;

	/**
	 * Constant for default style pattern. Its value is MEDIUM.
	 */
	public static final int DEFAULT = MEDIUM;

	@SuppressWarnings("deprecation")
	public final static DateFormat getDateInstance() {
		return new SimpleDateFormat(DateTimeFormat.getMediumDateFormat());
	}

	@SuppressWarnings("deprecation")
	public final static DateFormat getDateInstance(int style) {
		switch (style) {
		case FULL:
			return new SimpleDateFormat(DateTimeFormat.getFullDateFormat());
		case LONG:
			return new SimpleDateFormat(DateTimeFormat.getLongDateFormat());
		case SHORT:
			return new SimpleDateFormat(DateTimeFormat.getShortDateFormat());
		default:
			return getDateInstance();
		}
	}

	@SuppressWarnings("deprecation")
	public final static DateFormat getTimeInstance() {
		return new SimpleDateFormat(DateTimeFormat.getMediumTimeFormat());
	}

	@SuppressWarnings("deprecation")
	public final static DateFormat getTimeInstance(int style) {
		switch (style) {
		case FULL:
			return new SimpleDateFormat(DateTimeFormat.getFullTimeFormat());
		case LONG:
			return new SimpleDateFormat(DateTimeFormat.getLongTimeFormat());
		case SHORT:
			return new SimpleDateFormat(DateTimeFormat.getShortTimeFormat());
		default:
			return getTimeInstance();
		}
	}

	@SuppressWarnings("deprecation")
	public final static DateFormat getDateTimeInstance() {
		return new SimpleDateFormat(DateTimeFormat.getMediumDateTimeFormat());
	}

	@SuppressWarnings("deprecation")
	public final static DateFormat getDateTimeInstance(int dateStyle,
			int timeStyle) {
		if (dateStyle != timeStyle) {
			throw new IllegalArgumentException(
					"Unsupported combinaison of dateStyle & timeStyle : "
							+ dateStyle + "-" + timeStyle);
		}
		switch (dateStyle) {
		case FULL:
			return new SimpleDateFormat(DateTimeFormat.getFullDateTimeFormat());
		case LONG:
			return new SimpleDateFormat(DateTimeFormat.getLongDateTimeFormat());
		case SHORT:
			return new SimpleDateFormat(DateTimeFormat.getShortDateTimeFormat());
		default:
			return getDateTimeInstance();
		}
	}

	public final static DateFormat getInstance() {
		return getDateTimeInstance(SHORT, SHORT);
	}

	public abstract String format(Date date);

	public abstract Date parse(String source);
}

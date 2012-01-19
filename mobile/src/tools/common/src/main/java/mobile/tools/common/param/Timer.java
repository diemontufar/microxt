package mobile.tools.common.param;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import mobile.tools.common.Log;
import mobile.tools.common.convertion.FormatDates;

public class Timer {

	private static Timer INSTANCE;
	private final static String strExpiredTime = "9999-12-31 00:00:00.000";
	private Timestamp expiredTime;

	private Timer() {
		try {
			Log.getInstance().info("Create instace of Timer class");
			expiredTime = new Timestamp(FormatDates.getTimeFormat()
					.parse(strExpiredTime).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private synchronized static Timer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Timer();
		}
		return INSTANCE;
	}

	public static Timestamp getExpiredTime() {
		return getInstance().expiredTime;
	}

	public static Timestamp getCurrentTime() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}

	public static Date getCurrentDate() {
		java.util.Date date = new java.util.Date();
		return new Date(date.getTime());
	}
}

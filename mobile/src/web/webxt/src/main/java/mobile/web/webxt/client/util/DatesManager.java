package mobile.web.webxt.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DatesManager {

	public static String format_date = "yyyy-MM-dd";
	public static String format_time = "yyyy-MM-dd HH:mm:ss";
	public static String format_timestamp = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String format_expired_time = "9999-12-31 00:00:00";
	public static String format_hour = "HH:mm:ss";
	public static String format_minute = "mm:ss.SSS";
	public static String format_currency = "#,###,###.00";
	public static String format_file = "yyyyMMdd-HHmmss";

	public Date getCurrentDate() throws Exception {
		java.util.Date date = new java.util.Date();
		return new Date(date.getTime());
	}

	public static int calculateAge(String dob) {
		
		Date birthdayDay=stringToDate(dob);
		
		Date today = new Date();
		Integer currentYear = new Integer(DateTimeFormat.getFormat("yyyy")
				.format(today));
		Integer currentMonth = new Integer(DateTimeFormat.getFormat("M")
				.format(today));
		Integer currentDay = new Integer(DateTimeFormat.getFormat("d").format(
				today));

		Integer dobYear = new Integer(DateTimeFormat.getFormat("yyyy").format(
				birthdayDay));
		Integer dobMonth = new Integer(DateTimeFormat.getFormat("M")
				.format(birthdayDay));
		Integer dobDay = new Integer(DateTimeFormat.getFormat("d").format(birthdayDay));

		int age = currentYear - dobYear;

		if ((dobMonth > currentMonth)
				|| (currentMonth == dobMonth && dobDay > currentDay))
			age--;

		return age;
	}

	@SuppressWarnings("static-access")
	public Date addDaysToDate(String dueDate, int days) {

		Date date = stringToDate(dueDate);
		CalendarUtil cu = new CalendarUtil();
		cu.addDaysToDate(date, days);

		return date;
	}

	@SuppressWarnings("static-access")
	public Date addMonthsToDate(String dueDate, int months) {

		Date date = stringToDate(dueDate);
		CalendarUtil cu = new CalendarUtil();
		cu.addMonthsToDate(date, months);

		return date;
	}

	@SuppressWarnings("static-access")
	public int getDaysBetween(String start, String finish) {

		Date dateStart = stringToDate(start);
		Date dateFinish = stringToDate(finish);
		CalendarUtil cu = new CalendarUtil();
		int numDays = cu.getDaysBetween(dateStart, dateFinish);

		return numDays;
	}
	
	public int getMonthsBetween(String start, String finish) {

		int days=getDaysBetween(start, finish);
		int numMonths=days/30;

		return numMonths;
	}
	
	public int getYearsBetween(String start, String finish) {

		int months=getMonthsBetween(start, finish);
		int numYears=months/12;

		return numYears;
	}

	@SuppressWarnings("static-access")
	public boolean isSameDate(String date1, String date2) {

		Date dateStart = stringToDate(date1);
		Date dateFinish = stringToDate(date2);
		CalendarUtil cu = new CalendarUtil();
		boolean isEqual = cu.isSameDate(dateStart, dateFinish);

		return isEqual;
	}

	public static String dateToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat(format_date);
		String convertedDate = sdf.format(date);

		return convertedDate;
	}

	public static Date stringToDate(String dueDate) {

		SimpleDateFormat formato = new SimpleDateFormat(format_date);
		Date date = formato.parse(dueDate);

		return date;
	}

}

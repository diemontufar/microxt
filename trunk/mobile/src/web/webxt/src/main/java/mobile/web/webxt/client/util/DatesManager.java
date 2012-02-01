package mobile.web.webxt.client.util;

import java.util.Date;

import mobile.common.tools.Format;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DatesManager {

	public static String dateToString(Date date, String format) {
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(format);
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static Date stringToDate(String strDate, String format) {
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(format);
		Date date = dateFormat.parse(strDate);
		return date;
	}

	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	public static String getStringCurrentDate(String format) {
		Date actualDate = getCurrentDate();
		return dateToString(actualDate, format);
	}

	public static int calculateAge(String dob) {

		Date birthdayDay = stringToDate(dob, Format.DATE);

		java.util.Date today = new java.util.Date();
		Integer currentYear = new Integer(DateTimeFormat.getFormat("yyyy").format(today));
		Integer currentMonth = new Integer(DateTimeFormat.getFormat("M").format(today));
		Integer currentDay = new Integer(DateTimeFormat.getFormat("d").format(today));

		Integer dobYear = new Integer(DateTimeFormat.getFormat("yyyy").format(birthdayDay));
		Integer dobMonth = new Integer(DateTimeFormat.getFormat("M").format(birthdayDay));
		Integer dobDay = new Integer(DateTimeFormat.getFormat("d").format(birthdayDay));

		int age = currentYear - dobYear;

		if ((dobMonth > currentMonth) || (currentMonth == dobMonth && dobDay > currentDay))
			age--;

		return age;
	}

	@SuppressWarnings("static-access")
	public static Date addDaysToDate(String dueDate, int days) {
		Date date = stringToDate(dueDate, Format.DATE);
		CalendarUtil cu = new CalendarUtil();
		cu.addDaysToDate(date, days);

		return date;
	}

	@SuppressWarnings("static-access")
	public static Date addMonthsToDate(String dueDate, int months) {
		Date date = stringToDate(dueDate, Format.DATE);
		CalendarUtil cu = new CalendarUtil();
		cu.addMonthsToDate(date, months);

		return date;
	}

	@SuppressWarnings("static-access")
	public static int getDaysBetween(String start, String finish) {
		Date dateStart = stringToDate(start, Format.DATE);
		Date dateFinish = stringToDate(finish, Format.DATE);
		CalendarUtil cu = new CalendarUtil();
		int numDays = cu.getDaysBetween(dateStart, dateFinish);
		return numDays;
	}

	public static int getMonthsBetween(String start, String finish) {
		int days = getDaysBetween(start, finish);
		int numMonths = days / 30;
		return numMonths;
	}

	public static int getYearsBetween(String start, String finish) {
		int months = getMonthsBetween(start, finish);
		int numYears = months / 12;
		return numYears;
	}

	@SuppressWarnings("static-access")
	public static boolean isSameDate(String date1, String date2) {
		Date dateStart = stringToDate(date1, Format.DATE);
		Date dateFinish = stringToDate(date2, Format.DATE);
		CalendarUtil cu = new CalendarUtil();
		boolean isEqual = cu.isSameDate(dateStart, dateFinish);
		return isEqual;
	}
	
	public static boolean isBeforeToday(String date){
		Date dateStart = stringToDate(date, Format.DATE);
		java.util.Date today = new java.util.Date();
		
		if (dateStart.before(today)) {
		    return true;
		} else{ 
		    return false;
		}
	}
	
	public static boolean isAfterToday(String date){
		Date dateStart = stringToDate(date, Format.DATE);
		java.util.Date today = new java.util.Date();
		
		if (dateStart.after(today)) {
		    return true;
		} else{ 
		    return false;
		}
	}
	
	public static boolean compare(String date1,String date2){
		Date dateStart = stringToDate(date1, Format.DATE);
		Date dateFinish = stringToDate(date2, Format.DATE);
		
		if (dateStart.equals(dateFinish)) {
		    return true;
		} else{ 
		    return false;
		}
	}
	
	public static boolean isToday(String date){
		java.util.Date today = new java.util.Date();
		String td= dateToString(today, Format.DATE);
		
		if (date.compareTo(td)==0) {
		    return true;
		} else{ 
		    return false;
		}
	}
	
}

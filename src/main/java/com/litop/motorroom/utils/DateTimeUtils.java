package com.litop.motorroom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间操作的工具类
 * @author Administrator
 *
 */
public class DateTimeUtils {
	public static final int JANUARY = 0;
	public static final int FEBRUARY = 1;
	public static final int MARCH = 2;
	public static final int APRIL = 3;
	public static final int MAY = 4;
	public static final int JUNE = 5;
	public static final int JULY = 6;
	public static final int AUGUST = 7;
	public static final int SEPTEMBER = 8;
	public static final int OCTOBER = 9;
	public static final int NOVEMBER = 10;
	public static final int DECEMBER = 11;
	public static final int SUNDAY = 0;
	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;
	public static final String TIMEUNIT_MINUTE = "M";
	public static final String TIMEUNIT_HOUR = "H";
	public static final String TIMEUNIT_DAY = "D";
	public static final String PERIODUNIT_HOUR = "hour";
	public static final String PERIODUNIT_DAY = "day";
	public static final String PERIODUNIT_WEEK = "week";
	public static final String PERIODUNIT_MONTH = "month";
	public static final String PERIODUNIT_YEAR = "year";


	public static String getCurDate() {
		return formatDate(new Date());
	}

	public static String getCurDateTime() {
		return formatDateTime(new Date());
	}

	public static String formatDate(Date date) {
		if (null == date)
			return "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String formatDateTime(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateTime2Minute(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatDateTime(Date date, String format) {
		if (null == date)
			return "";
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static int getHour(Date date) {
		if (null == date)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(11);
	}

	public static int getMinute(Date date) {
		if (null == date)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(12);
	}

	public static Date getDate(String dateTime) {
		if ((null == dateTime) || (dateTime.length() == 0))
			return null;
		DateFormat df = null;
		if (dateTime.length() == "yyyy-MM-dd HH:mm:ss".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (dateTime.length() == "yyyy-MM-dd HH:mm".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if (dateTime.length() == "yyyy-MM-dd".length())
			df = new SimpleDateFormat("yyyy-MM-dd");
		else {
			return null;
		}
		try {
			return df.parse(dateTime);
		} catch (ParseException pe) {
		}
		return null;
	}

	public static Date getDate(Date date, boolean isStart) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (isStart) {
			calendar.set(11, 0);
			calendar.set(12, 0);
			calendar.set(13, 0);
			calendar.set(14, 0);
		} else {
			calendar.set(11, 23);
			calendar.set(12, 59);
			calendar.set(13, 59);
		}
		return calendar.getTime();
	}

	public static String buildDateTime(String date, String hour, String minute) {
		if ((null == date) || (date.length() == 0)) {
			return "";
		}
		if (null == hour) {
			return date;
		}
		String hm = (hour.length() == 1) ? "0" + hour : hour;
		if (null != minute) {
			hm = hm + ":";
			hm = hm + ((minute.length() == 1) ? "0" + minute : minute);
		}

		return date + " " + hm;
	}

	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		return calendar.getTime();
	}

	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, 23);
		calendar.set(12, 59);
		calendar.set(13, 59);
		return calendar.getTime();
	}

	public static String getCurYear() {
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(1));
	}


	public static Date getSimpleDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dt = sdf.format(date);
		ParsePosition pos = new ParsePosition(0);
		Date nowDate = sdf.parse(dt, pos);

		return nowDate;
	}


}

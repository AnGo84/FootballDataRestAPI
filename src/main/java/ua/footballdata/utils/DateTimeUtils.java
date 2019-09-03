package ua.footballdata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	public static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

	/*
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
	 * "yyyy-MM-dd'T'HH:mm:ss'Z'", locale = "en_GB") private Date lastUpdated;
	 */

	public static final String API_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DISPLAY_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
	public static final DateFormat API_FORMATER = new SimpleDateFormat(API_DATETIME_FORMAT);
	public static final DateFormat DISPLAY_FORMATER = new SimpleDateFormat(DISPLAY_DATETIME_FORMAT);

	public static Date getDateFromString(String date) {
		// formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		try {
			return API_FORMATER.parse(date);
		} catch (ParseException e) {
			logger.error("Can't convert '" + date + "' to date format '" + API_DATETIME_FORMAT + "'", e);
			return null;
		}
	}

	public static String getDateForDisplay(String stringDate) {
		try {
			Date date = API_FORMATER.parse(stringDate);
			return DISPLAY_FORMATER.format(date);
		} catch (ParseException e) {
			logger.error("Can't convert '" + stringDate + "' to format '" + DISPLAY_DATETIME_FORMAT + "'", e);
			return "";
		}
	}

}

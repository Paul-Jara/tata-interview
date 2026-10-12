package com.tata.accountservice.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of Singleton
 * @author Paul
 *
 */
public class DateUtils {
	
	private static DateUtils dateUtils;
	
	public static DateUtils getInstance() {
		dateUtils = dateUtils == null ? new DateUtils() : dateUtils;
		return dateUtils;
	}

	public String getFormatDate(final LocalDate date, final String format) {
		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return date.format(dateTimeFormatter);
	}
}

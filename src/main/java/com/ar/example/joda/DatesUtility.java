package com.ar.example.joda;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DatesUtility {
	
	public static int daysBtnDates(LocalDate start, String end, String endDateFormat){
		DateTimeFormatter formatter = DateTimeFormat.forPattern(endDateFormat);
		DateTime dt = formatter.parseDateTime(end);//pass parameter
		return Days.daysBetween(start, dt.toLocalDate()).getDays();
	}
	
	public static LocalDate tomorrowsDate(){
		DateTime now = new DateTime();
		LocalDate today = now.toLocalDate();
		LocalDate tomorrow = today.plusDays(1);
		return tomorrow;
	}
	
}

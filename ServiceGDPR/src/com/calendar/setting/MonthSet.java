package com.calendar.setting;

import java.time.LocalDate;
import java.time.YearMonth;

public class MonthSet {

	public static String setMonth(YearMonth yearMonth) {
		String monthToString = String.valueOf(yearMonth.getMonth());
		if (yearMonth.getMonth().toString().equals("JANUARY")) {
			monthToString = "Január";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("FEBRUARY")) {
			monthToString = "Február";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("MARCH")) {
			monthToString = "Március";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("APRIL")) {
			monthToString = "Április";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("MAY")) {
			monthToString = "Május";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("JUNE")) {
			monthToString = "Június";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("JULY")) {
			monthToString = "Július";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("AUGUST")) {
			monthToString = "Augusztus";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("SEPTEMBER")) {
			monthToString = "Szeptember";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("OCTOBER")) {
			monthToString = "Október";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("NOVEMBER")) {
			monthToString = "November";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("DECEMBER")) {
			monthToString = "December";
			return monthToString;
		}

		return monthToString;

	}

	public static void setDateNow(CalendarPane p) {
		if (p.getNumber() == 7 || p.getNumber() == 14 || p.getNumber() == 21 || p.getNumber() == 28
				|| p.getNumber() == 35) {
			p.setStyle(" -fx-background-color: #c5e5c8;");
			if (p.getDate().equals(LocalDate.now())) {
				p.setStyle(" -fx-background-color: #c2d1da;");
			}
		} else if (p.getNumber() == 6 || p.getNumber() == 13 || p.getNumber() == 20 || p.getNumber() == 27
				|| p.getNumber() == 34) {
			p.setStyle(" -fx-background-color: #e7f5e3;");
			if (p.getDate().equals(LocalDate.now())) {
				p.setStyle(" -fx-background-color: #c2d1da;");
			}
		} else {
			p.setStyle(" -fx-background-color: #fafaf7;");
			if (p.getDate().equals(LocalDate.now())) {
				p.setStyle(" -fx-background-color: #c2d1da;");
			}
		}
	}
}

package com.service.device.calendar;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CalendarControler implements Initializable {

	@FXML
	private AnchorPane controlAnchorPane;
	@FXML
	private GridPane setGridPane, setWeekGridPane;
	@FXML
	private Label calendarTitle;
	@FXML
	private Button previousMonth, nextMonth;
	private YearMonth currentYearMonth;
	private ArrayList<CalendarPane> allCalendarDays = new ArrayList<>(35);
	LocalDate calendarDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentYearMonth = YearMonth.now();
		FullCalendarView(currentYearMonth);
	}

	public void FullCalendarView(YearMonth yearMonth) {
		this.currentYearMonth = yearMonth;
		setGridPane.setHgap(1);
		setGridPane.setVgap(1);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				CalendarPane p = new CalendarPane();
				setGridPane.add(p, j, i);
				allCalendarDays.add(p);
			}
		}
		populateCalendar(yearMonth);
	}

	@SuppressWarnings("static-access")
	private void populateCalendar(YearMonth yearMonth) {
		calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		int z = 0;
		for (CalendarPane ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
				ap.getChildren().clear();
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setNumber(z += 1);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			MonthSet.setDateNow(ap);
		}
		;
		calendarTitle.setText(String.valueOf(yearMonth.getYear() + " " + MonthSet.setMonth(yearMonth)));
	}

	@FXML
	private void previousMonth() {
		currentYearMonth = currentYearMonth.minusMonths(1);
		populateCalendar(currentYearMonth);
	}

	@FXML
	private void nextMonth() {
		currentYearMonth = currentYearMonth.plusMonths(1);
		populateCalendar(currentYearMonth);
	}

	public ArrayList<CalendarPane> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<CalendarPane> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}
}

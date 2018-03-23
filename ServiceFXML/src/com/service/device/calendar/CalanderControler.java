package com.service.device.calendar;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CalanderControler implements Initializable {

	@FXML
	private AnchorPane controlAnchorPane;
	@FXML
	private GridPane setGridPane, setWeekGridPane;
	@FXML
	private Label calendarTitle;
	private YearMonth currentYearMonth;
	private ArrayList<CalanderPane> allCalendarDays = new ArrayList<>(35);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentYearMonth = YearMonth.now();
		FullCalendarView(currentYearMonth);

	}

	public void FullCalendarView(YearMonth yearMonth) {
		this.currentYearMonth = yearMonth;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				CalanderPane p = new CalanderPane();
				setGridPane.add(p, j, i);
				 allCalendarDays.add(p);
			}
		}
		populateCalendar(yearMonth);
	}

	@SuppressWarnings("static-access")
	public void populateCalendar(YearMonth yearMonth) {
		LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		for (CalanderPane ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			Text t = new Text();

			ap.setTopAnchor(t, 16.0);
			ap.setLeftAnchor(t, 5.0);
			ap.getChildren().add(t);
		}
		calendarTitle.setText(String.valueOf(yearMonth.getYear() + " " + yearMonth.getMonth()));
	}

	private void previousMonth() {
		currentYearMonth = currentYearMonth.minusMonths(1);
		populateCalendar(currentYearMonth);
	}

	private void nextMonth() {
		currentYearMonth = currentYearMonth.plusMonths(1);
		populateCalendar(currentYearMonth);
	}

	public ArrayList<CalanderPane> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<CalanderPane> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}
}

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

public class CalanderControler implements Initializable {

	@FXML
	private AnchorPane controlAnchorPane;
	@FXML
	private GridPane setGridPane, setWeekGridPane;
	@FXML
	private Label calendarTitle;
	@FXML
	private Button previousMonth, nextMonth;
	private YearMonth currentYearMonth;
	private ArrayList<CalanderPane> allCalendarDays = new ArrayList<>(35);
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
				CalanderPane p = new CalanderPane();
				setGridPane.add(p, j, i);
				allCalendarDays.add(p);
				if (j == 6) {
					p.setStyle(" -fx-background-color: #eecccc;");
				} else if (j == 5) {
					p.setStyle(" -fx-background-color: #e7f5e3;");
				} else {
					p.setStyle(" -fx-background-color: #fafaf7;");
				}

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
		for (CalanderPane ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
				ap.getChildren().clear();
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			name(ap);
			
		}
		calendarTitle.setText(String.valueOf(yearMonth.getYear() + " " + yearMonth.getMonth()));
	}
	
	private void name(CalanderPane p) {
		Label n = new Label("sd");
		p.getChildren().add(n);
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

	public ArrayList<CalanderPane> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<CalanderPane> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}
}

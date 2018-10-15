package com.calendar.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.calendar.database.CalendarDataBase;
import com.calendar.setting.CalendarPane;
import com.calendar.setting.MonthSet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	CalendarDataBase dDb = new CalendarDataBase();

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
		for (CalendarPane p : allCalendarDays) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			txt.setStyle("-fx-font: 18 arial");
			p.setDate(calendarDate);
			p.setNumber(z += 1);
			p.setTopAnchor(txt, 5.0);
			p.setLeftAnchor(txt, 5.0);
			p.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			MonthSet.setDateNow(p);
			dfd(p.getDate(), p);
		}
		;
		calendarTitle.setText(String.valueOf(yearMonth.getYear() + " " + MonthSet.setMonth(yearMonth)));
	}

	@SuppressWarnings("static-access")
	private void dfd(LocalDate localDate, CalendarPane p) {
		String d = String.valueOf(localDate);
		VBox vb = new VBox();
		p.setTopAnchor(vb, 25.0);
		p.setLeftAnchor(vb, 5.0);
		p.getChildren().add(vb);
		int k = 0;
		for (int i = 0; i < dDb.calendarList.size(); i++) {
			if (dDb.calendarList.get(i).equals(d)) {
				k = k + 1;
				String g = dDb.calendarList.get(i + 1);
				Label l = new Label(g);
				l.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
				l.setStyle(" -fx-text-fill: tomato;");
				if (k == 4) {
					l.setText("...");
					vb.getChildren().add(l);
					return;
				}
				vb.getChildren().add(l);

			}
		}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentYearMonth = YearMonth.now();
		FullCalendarView(currentYearMonth);
	}
}
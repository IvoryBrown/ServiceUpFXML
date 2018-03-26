package com.service.device.calendar;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalendarPane extends AnchorPane {
	private LocalDate date;
	private Integer number;

	public CalendarPane(Node... children) {
		super(children);
		this.setOnMouseClicked(e -> baaj());
	}

	public CalendarPane(LocalDate date) {
		this.date = date;
		System.out.println(this.date);
	}

	private void baaj() {
		String date = String.valueOf(this.date);
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlmean/NewDeviceCalendar.fxml"));
			Stage stage = new Stage();
			stage.setWidth(1300);
			stage.setHeight(650);
			stage.setTitle(date);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}

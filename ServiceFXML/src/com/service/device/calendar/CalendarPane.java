package com.service.device.calendar;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CalendarPane extends AnchorPane {
	private LocalDate date;
	private Integer number;

	public CalendarPane(Node... children) {
		super(children);
		this.setOnMouseClicked(e ->	baaj());
	}

	private void baaj() {

		 System.out.println("This pane's date is: " + date+" :"+number);

		

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

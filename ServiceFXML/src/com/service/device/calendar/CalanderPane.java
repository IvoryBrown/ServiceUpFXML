package com.service.device.calendar;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CalanderPane extends AnchorPane {
	private LocalDate date;
	

	public CalanderPane(Node... children) {
		super(children);
		baaj();
	}
	
	private void baaj() {
		this.setOnMouseClicked(e -> System.out.println("This pane's date is: " + date));
		
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}

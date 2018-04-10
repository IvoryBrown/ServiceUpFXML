package com.service.device.calendar;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalendarPane extends AnchorPane {
	private LocalDate date;
	private Integer number;
	public static String sDate;

	public CalendarPane(Node... children) {
		super(children);
		this.setOnMouseClicked(e -> setCalendarController());

	}

	private void setCalendarController() {
		sDate = String.valueOf(getDate());
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlsetting/ProgressBar.fxml"));
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			stage.setWidth(400);
			stage.setHeight(50);
			stage.setScene(scene);
			stage.getIcons().add(
					new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
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

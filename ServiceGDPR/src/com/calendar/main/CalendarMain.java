package com.calendar.main;

import com.calendar.controller.CalanderTableController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CalendarMain {
	public void startCalendar() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/calendar/view/Calendar.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Naptár");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1200, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void startTable() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/device/view/DeviceTable.fxml"));
			loader.setController(new CalanderTableController());
			Parent root = (Parent) loader.load();
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setTitle("Naptár");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

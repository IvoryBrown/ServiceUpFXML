package com.calendar.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CalendarMain {
	public void start() {
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
}

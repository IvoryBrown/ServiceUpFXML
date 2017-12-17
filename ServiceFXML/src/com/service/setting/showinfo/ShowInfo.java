package com.service.setting.showinfo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowInfo {
	public static void showInfo(String errormesseng, String messeng) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText(messeng);
		alert.setContentText(errormesseng);
		alert.showAndWait();
	}
	
}

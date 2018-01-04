package com.service.setting.showinfo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowInfo {
	public static void showInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();

	}

	public static void errorInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
	}
	public static void errorInfoMessengeSQL(String info, String messengs) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
	}

	
}

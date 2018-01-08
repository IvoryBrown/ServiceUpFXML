package com.service.setting.showinfo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowInfo {
	public static void showInfoMessenge(final String info, final String messengs) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();

	}

	public static void errorInfoMessenge(final String info, final String messengs) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
	}

	public static void errorInfoMessengeException(final String info, final String messengs, final String sqlMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(sqlMessage);
		alert.setContentText(messengs);
		alert.showAndWait();
	}
}

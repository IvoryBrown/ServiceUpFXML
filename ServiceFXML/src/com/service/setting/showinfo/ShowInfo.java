package com.service.setting.showinfo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;

public class ShowInfo {
	public void showInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets()
				.add(getClass().getResource("/com/service/setting/desing/desing.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
	}

	public static void errorInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
	}

	public static void errorInfoMessengeException(String info, String messengs, String sqlMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(messengs);
		alert.setContentText(sqlMessage);
		alert.showAndWait();
	}

}

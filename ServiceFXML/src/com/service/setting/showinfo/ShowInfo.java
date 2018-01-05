package com.service.setting.showinfo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowInfo {
	public void showInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
		
	}

	public  void errorInfoMessenge(String info, String messengs) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(info);
		alert.setHeaderText(null);
		alert.setContentText(messengs);
		alert.showAndWait();
		alert.getDialogPane().getStylesheets().add("/com/service/setting/desing/desing.css");
	}

	public static void errorInfoMessengeException(String info, String messengs, String sqlMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(info);
		alert.setHeaderText(sqlMessage);
		alert.setContentText(messengs);
		alert.showAndWait();
	}

}

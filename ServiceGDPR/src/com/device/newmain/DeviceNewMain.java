package com.device.newmain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeviceNewMain {
	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/device/newview/DeviceNew.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Új eszköz");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 700));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

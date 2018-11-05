package com.device.information.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeviceInformationMain {
	
	public void start() {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/device/information/view/DeviceInformationFXML.fxml"));
			Stage stage = new Stage();
			stage.setWidth(1300);
			stage.setHeight(650);
			stage.setTitle("Információ");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

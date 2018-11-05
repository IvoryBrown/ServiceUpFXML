package com.device.all.main;

import com.device.all.controller.AllDeviceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeviceAllMain {
	
	public void start() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/device/view/DeviceTable.fxml"));
			loader.setController(new AllDeviceController());
			Parent root = (Parent) loader.load();
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setTitle("Összes");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

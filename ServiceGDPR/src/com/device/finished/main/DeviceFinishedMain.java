package com.device.finished.main;

import com.device.finished.controller.FinishedDeviceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeviceFinishedMain {
	public void start() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/device/view/DeviceTable.fxml"));
			loader.setController(new FinishedDeviceController());
			Parent root = (Parent) loader.load();
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setTitle("Befejezett");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

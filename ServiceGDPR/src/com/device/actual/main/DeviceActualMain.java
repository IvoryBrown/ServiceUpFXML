package com.device.actual.main;

import com.device.actual.controller.ActualDeviceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeviceActualMain {

	public void start() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/device/view/DeviceTable.fxml"));
			loader.setController(new ActualDeviceController());
			Parent root = (Parent) loader.load();
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

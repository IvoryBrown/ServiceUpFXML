package com.device.actual.main;

import com.device.actual.controller.ActualDeviceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeviceActualMain {
	public static Stage primaryStageDeviceActualMain;


	public void start() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/device/view/DeviceTable.fxml"));
			loader.setController(new ActualDeviceController());
			Parent root = (Parent) loader.load();
			primaryStageDeviceActualMain = primaryStage;
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setTitle("Aktuális");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
